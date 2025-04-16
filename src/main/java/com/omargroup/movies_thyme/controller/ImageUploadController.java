package com.omargroup.movies_thyme.controller;

import com.omargroup.movies_thyme.model.ImageMetaData;
import com.omargroup.movies_thyme.repository.ImageMetadataRepository;
import com.omargroup.movies_thyme.service.S3Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api/upload")
public class ImageUploadController {

    private final S3Service s3Service;
    private final ImageMetadataRepository repository;


    public ImageUploadController(S3Service s3Service, ImageMetadataRepository repository) {
        this.s3Service = s3Service;
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            String url = s3Service.uploadFile(file);

            ImageMetaData image = new ImageMetaData();
            image.setImageUrl(url);
            repository.save(image);

            return ResponseEntity.ok(Map.of("url", url));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    }
