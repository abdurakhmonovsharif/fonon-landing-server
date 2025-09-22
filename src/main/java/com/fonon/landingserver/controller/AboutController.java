package com.fonon.landingserver.controller;

import com.fonon.landingserver.domain.About;
import com.fonon.landingserver.service.AboutCrudService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/about-sections")
public class AboutController extends BaseCrudController<About> {

    public AboutController(AboutCrudService service) {
        super(service);
    }
}
