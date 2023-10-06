package com.example.pathfinder.service.impl;

import com.cloudinary.Cloudinary;
import com.example.pathfinder.model.CloudinaryImage;
import com.example.pathfinder.service.CloudinaryService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryServiceImpl implements CloudinaryService {

    private final Cloudinary cloudinary;

    public CloudinaryServiceImpl(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    @Override
    public CloudinaryImage upload(MultipartFile multipartFile) throws IOException {
        File tempFile = File.createTempFile("temp-file", multipartFile.getOriginalFilename());
        multipartFile.transferTo(tempFile);
        try {

            @SuppressWarnings("unchecked")
            Map<String, String> uploadResult = cloudinary.uploader().upload(tempFile, Map.of());

            String url = uploadResult.getOrDefault("url", "");
            String publicId = uploadResult.getOrDefault("public_id", "");

            return new CloudinaryImage()
                    .setUrl(url)
                    .setPublicId(publicId);

        } finally {
            tempFile.delete();
        }
    }

    @Override
    public boolean delete(String publicId) {
        try {
            cloudinary.uploader().destroy(publicId, Map.of());
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}
