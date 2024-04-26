package com.example.InaiProject.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Vacancy {
    private String name;
    private Schedule schedule;
    private Salary salary;
}
