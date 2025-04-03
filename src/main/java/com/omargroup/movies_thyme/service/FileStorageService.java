package com.omargroup.movies_thyme.service;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;
import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;

//@Service
public class FileStorageService {
    private final S3Client s3Client;
    private final String bucketName;
    private final String cdnDomain;

    public FileStorageService(S3Client s3Client,
                              @Value("${aws.s3.bucket-name}") String bucketName,
                              @Value("${aws.cloudfront.domain}") String cdnDomain) {
        this.s3Client = s3Client;
        this.bucketName = bucketName;
        this.cdnDomain = cdnDomain;
    }

    public String uploadProfilePicture(MultipartFile file, String userId) throws IOException {
        // Validate file
        validateImageFile(file);

        // Process image
        BufferedImage originalImage = ImageIO.read(file.getInputStream());
        BufferedImage resizedImage = Scalr.resize(originalImage, 300);

        // Convert to optimized format
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(resizedImage, "webp", outputStream);

        // Generate unique filename
        String fileExtension = "webp";
        String filename = "profile-pictures/" + userId + "/" + UUID.randomUUID() + "." + fileExtension;

        // Upload to S3
        s3Client.putObject(PutObjectRequest.builder()
                        .bucket(bucketName)
                        .key(filename)
                        .contentType("image/webp")
                        .acl(ObjectCannedACL.PUBLIC_READ)
                        .build(),
                RequestBody.fromBytes(outputStream.toByteArray()));

        return "https://" + cdnDomain + "/" + filename;
    }

    private void validateImageFile(MultipartFile file) throws IOException {
        // Check MIME type
        Set<String> allowedTypes = Set.of("image/jpeg", "image/png", "image/webp");
        if (!allowedTypes.contains(file.getContentType())) {
            throw new IOException("Invalid file type");
        }

        // Check file size (e.g., 5MB max)
        if (file.getSize() > 5_242_880) {
            throw new IOException("File too large");
        }

        // Verify it's actually an image
        try {
            BufferedImage image = ImageIO.read(file.getInputStream());
            if (image == null) {
                throw new IOException("Invalid image file");
            }
        } catch (Exception e) {
            throw new IOException("Failed to process image");
        }
    }
}