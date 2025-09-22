package com.fonon.landingserver.service;

import com.fonon.landingserver.domain.Vacancy;
import com.fonon.landingserver.repository.VacancyRepository;
import org.springframework.stereotype.Service;

@Service
public class VacancyCrudService extends CrudService<Vacancy> {

    public VacancyCrudService(VacancyRepository repository) {
        super(repository, "Vacancy");
    }
}
