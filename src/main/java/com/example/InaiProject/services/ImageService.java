package com.example.InaiProject.services;

import com.amazonaws.services.s3.model.S3Object;
import com.example.InaiProject.dto.image.ImageResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {

    List<ImageResponse> getAllImagesInfo();
    S3Object getFile(String keyName);
    String uploadFile(MultipartFile file);
    byte[] downloadFile(String fileName);
    String deleteFile(String fileName);
}
