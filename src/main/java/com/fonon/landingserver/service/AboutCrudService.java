package com.fonon.landingserver.service;

import com.fonon.landingserver.domain.About;
import com.fonon.landingserver.exception.ResourceNotFoundException;
import com.fonon.landingserver.repository.AboutRepository;
import org.springframework.stereotype.Service;

@Service
public class AboutCrudService extends CrudService<About> {

    private final AboutRepository repository;

    public AboutCrudService(AboutRepository repository) {
        super(repository, "About");
        this.repository = repository;
    }

    public About findBySlug(String slug) {
        return repository.findBySlug(slug)
                .orElseThrow(() -> new ResourceNotFoundException("About", slug));
    }
}
