package com.example.pathfinder.config;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class CloudinaryConfiguration {

    private final CloudinaryConfigurationProperties properties;

    public CloudinaryConfiguration(CloudinaryConfigurationProperties cloudinaryConfigurationProperties) {
        this.properties = cloudinaryConfigurationProperties;
    }

    @Bean
    public Cloudinary cloudinary() {

        return new Cloudinary(Map.of(
                "cloud_name", properties.getCloudName(),
                "api_key", properties.getApiKey(),
                "api_secret", properties.getApiSecret()
        ));
    }
}
