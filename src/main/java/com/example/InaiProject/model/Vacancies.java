package com.example.InaiProject.model;

import lombok.Getter;

import java.util.List;

@Getter
public class Vacancies {
    private List<Vacancy> items;

    public Vacancies() {
    }

    public void setItems(List<Vacancy> items) {
        this.items = items;
    }
}
