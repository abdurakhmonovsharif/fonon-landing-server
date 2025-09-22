package com.fonon.landingserver.service;

import com.fonon.landingserver.domain.News;
import com.fonon.landingserver.repository.NewsRepository;
import org.springframework.stereotype.Service;

@Service
public class NewsCrudService extends CrudService<News> {

    public NewsCrudService(NewsRepository repository) {
        super(repository, "News");
    }
}
