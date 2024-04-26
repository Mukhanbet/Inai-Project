package com.example.InaiProject.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchParametersVacancy {

    private String name;
    private String specializationId;
    private String employmentId;
    private String experienceId;
    private String scheduleId;
    private Integer salary;
    private Integer page;
}
