package com.fonon.landingserver.controller;

import com.fonon.landingserver.domain.Vacancy;
import com.fonon.landingserver.service.VacancyCrudService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vacancies")
public class VacancyController extends BaseCrudController<Vacancy> {

    public VacancyController(VacancyCrudService service) {
        super(service);
    }
}
