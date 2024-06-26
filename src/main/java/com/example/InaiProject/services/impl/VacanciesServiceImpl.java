package com.example.InaiProject.services.impl;

import com.example.InaiProject.model.SearchParametersVacancy;
import com.example.InaiProject.model.Vacancies;
import com.example.InaiProject.model.Vacancy;
import com.example.InaiProject.services.VacanciesService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
public class VacanciesServiceImpl implements VacanciesService {

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String hhApi = "https://api.hh.ru/vacancies";

    public Vacancies getAllVacancies() {
        return restTemplate.getForEntity(hhApi, Vacancies.class).getBody();
    }

    public Vacancies getAllVacancies(SearchParametersVacancy params) {
        String url = hhApi + "/?per_page=20";
        if (Strings.isNotBlank(params.getName()))  url += "&text=" + params.getName() + "&search_field=name";
        if (Strings.isNotBlank(params.getSpecializationId()))  url += "&specialization=" + params.getSpecializationId();
        if (Strings.isNotBlank(params.getEmploymentId()))  url += "&employment=" + params.getEmploymentId();
        if (Strings.isNotBlank(params.getExperienceId()))  url += "&experience=" + params.getExperienceId();
        if (Strings.isNotBlank(params.getScheduleId()))  url += "&schedule=" + params.getScheduleId();
        if (Objects.nonNull(params.getSalary()))  url += "&salary=" + params.getSalary();
        if (Objects.nonNull(params.getPage()))  url += "&page=" + params.getPage();
        return restTemplate.getForEntity(url, Vacancies.class).getBody();
    }

    public Vacancy getVacancyById(String id) {
        return restTemplate.getForEntity(hhApi + "/" + id, Vacancy.class).getBody();
    }


}
