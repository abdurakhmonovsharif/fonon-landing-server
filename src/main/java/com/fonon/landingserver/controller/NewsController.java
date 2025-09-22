package com.fonon.landingserver.controller;

import com.fonon.landingserver.domain.News;
import com.fonon.landingserver.service.NewsCrudService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/news")
public class NewsController extends BaseCrudController<News> {

    public NewsController(NewsCrudService service) {
        super(service);
    }
}
