package com.omargroup.movies_thyme.controller;

import com.omargroup.movies_thyme.service.FileStorageService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map;

//@RestController
@RequestMapping("/files")
public class FileUploadController {

    private final Path rootLocation = Paths.get("uploads");
    private final FileStorageService fileStorageService;

    public FileUploadController(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "description", required = false) String description) {

        try {
            // Validate file
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("Failed to upload empty file");
            }

            // Create upload directory if it doesn't exist
            if (!Files.exists(rootLocation)) {
                Files.createDirectories(rootLocation);
            }

            // Sanitize filename and save
            String originalFilename = file.getOriginalFilename();
            String filename = originalFilename != null ?
                    originalFilename.replace(" ", "_") :
                    "file_" + System.currentTimeMillis();

            Path destinationFile = rootLocation.resolve(filename)
                    .normalize()
                    .toAbsolutePath();

            // Security check
            if (!destinationFile.getParent().equals(rootLocation.toAbsolutePath())) {
                return ResponseEntity.badRequest().body("Cannot store file outside current directory");
            }

            // Save file
            Files.copy(file.getInputStream(), destinationFile, StandardCopyOption.REPLACE_EXISTING);

            return ResponseEntity.ok("File uploaded successfully: " + filename +
                    (description != null ? "\nDescription: " + description : ""));

        } catch (IOException e) {
            return ResponseEntity.internalServerError()
                    .body("Failed to upload file: " + e.getMessage());
        }
    }

    @PostMapping("/picture")
    public ResponseEntity<?> uploadProfilePicture(
            @RequestParam("file") MultipartFile file,
            @RequestHeader("X-User-ID") String userId) {

        try {
            String fileUrl = fileStorageService.uploadProfilePicture(file, userId);
            return ResponseEntity.ok(Map.of(
                    "status", "success",
                    "url", fileUrl
            ));
        } catch (IOException e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "status", "error",
                    "message", e.getMessage()
            ));
        }
    }
}