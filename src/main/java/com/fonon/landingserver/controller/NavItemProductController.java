package com.fonon.landingserver.controller;

import com.fonon.landingserver.domain.NavItemProduct;
import com.fonon.landingserver.service.NavItemProductCrudService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/nav-item-products")
public class NavItemProductController extends BaseCrudController<NavItemProduct> {

    public NavItemProductController(NavItemProductCrudService service) {
        super(service);
    }
}
