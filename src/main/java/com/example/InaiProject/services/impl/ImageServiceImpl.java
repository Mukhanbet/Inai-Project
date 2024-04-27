package com.example.InaiProject.services.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import com.example.InaiProject.dto.image.ImageResponse;
import com.example.InaiProject.exception.CustomException;
import com.example.InaiProject.mapper.ImageMapper;
import com.example.InaiProject.model.Image;
import com.example.InaiProject.repository.ImageRepository;
import com.example.InaiProject.services.ImageService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
@Log4j2
public class ImageServiceImpl implements ImageService {

    @Value("${application.bucket.name}")
    private String bucketName;

//    @Value("${location.path}")
//    private String PATH;

    private final ImageRepository imageRepository;
    private final ImageMapper imageMapper;
    private final AmazonS3 s3Client;

    public ImageServiceImpl(ImageRepository imageRepository, ImageMapper imageMapper, AmazonS3 s3Client) {
        this.imageRepository = imageRepository;
        this.imageMapper = imageMapper;
        this.s3Client = s3Client;
    }

    @Override
    public List<ImageResponse> getAllImagesInfo() {
        return imageMapper.toDtoS(imageRepository.findAll());
    }

    public S3Object getFile(String keyName) {
        return s3Client.getObject(bucketName, keyName);
    }

    @Override
    public String uploadFile(MultipartFile file) {
        File fileObj = convertMultiPartFileToFile(file);
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        s3Client.putObject(new PutObjectRequest(bucketName, fileName, fileObj));
        fileObj.delete();
        Image image = new Image();
        imageRepository.save(imageMapper.toDtoImage(image, fileName));
        return "File uploaded " + fileName;
    }

    @Override
    public byte[] downloadFile(String fileName) {
        S3Object s3Object = s3Client.getObject(bucketName, fileName);
        S3ObjectInputStream inputStream = s3Object.getObjectContent();
        try {
            byte[] content = IOUtils.toByteArray(inputStream);
            return content;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String deleteFile(String fileName) {
        if(!imageRepository.findByName(fileName).isPresent()) {
            throw new CustomException("File with name=\"" + fileName + "\" not found", HttpStatus.NOT_FOUND);
        }
        s3Client.deleteObject(bucketName, fileName);
        imageRepository.deleteByName(fileName);
        return fileName + " removed";
    }

    private File convertMultiPartFileToFile(MultipartFile file) {
        File convertedFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            throw new CustomException("Error converting multiPartFile to file", HttpStatus.BAD_GATEWAY);
        }
        return convertedFile;
    }
}
