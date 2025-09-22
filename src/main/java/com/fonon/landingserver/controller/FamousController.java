package com.fonon.landingserver.controller;

import com.fonon.landingserver.domain.Famous;
import com.fonon.landingserver.service.FamousCrudService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/famous")
public class FamousController extends BaseCrudController<Famous> {

    public FamousController(FamousCrudService service) {
        super(service);
    }
}
