package com.fonon.landingserver.controller;

import com.fonon.landingserver.domain.About;
import com.fonon.landingserver.service.AboutCrudService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/about-sections")
public class AboutController extends BaseCrudController<About> {

    private final AboutCrudService service;

    public AboutController(AboutCrudService service) {
        super(service);
        this.service = service;
    }

    @GetMapping("/slug/{slug}")
    public About getBySlug(@PathVariable String slug) {
        return service.findBySlug(slug);
    }
}
