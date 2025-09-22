package com.fonon.landingserver.service;

import com.fonon.landingserver.domain.Famous;
import com.fonon.landingserver.repository.FamousRepository;
import org.springframework.stereotype.Service;

@Service
public class FamousCrudService extends CrudService<Famous> {

    public FamousCrudService(FamousRepository repository) {
        super(repository, "Famous");
    }
}
