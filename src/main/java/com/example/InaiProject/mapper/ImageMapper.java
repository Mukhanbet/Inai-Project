package com.example.InaiProject.mapper;


import com.example.InaiProject.dto.image.ImageResponse;
import com.example.InaiProject.model.Image;

import java.util.List;

public interface ImageMapper {
    ImageResponse toDto(Image image);
    List<ImageResponse> toDtoS(List<Image> all);
    Image toDtoImage(Image image, String name);
}
