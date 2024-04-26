package com.example.InaiProject.services;

import com.example.InaiProject.model.SearchParametersVacancy;
import com.example.InaiProject.model.Vacancies;
import com.example.InaiProject.model.Vacancy;

public interface VacanciesService {

    Vacancies getAllVacancies();

    Vacancies getAllVacancies(SearchParametersVacancy params);
    Vacancy getVacancyById(String id);
}
