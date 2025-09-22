package com.fonon.landingserver.service;

import com.fonon.landingserver.domain.About;
import com.fonon.landingserver.repository.AboutRepository;
import org.springframework.stereotype.Service;

@Service
public class AboutCrudService extends CrudService<About> {

    public AboutCrudService(AboutRepository repository) {
        super(repository, "About");
    }
}
