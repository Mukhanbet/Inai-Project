package com.example.InaiProject.model;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "questions")
@Getter
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "TEXT")
    private String text;
}
