package com.example.InaiProject.repository;

import com.example.InaiProject.model.Image;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    Optional<Image> findByName(String name);

    @Transactional
    void deleteByName(String name);
}
