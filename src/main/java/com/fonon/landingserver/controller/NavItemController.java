package com.fonon.landingserver.controller;

import com.fonon.landingserver.domain.NavItem;
import com.fonon.landingserver.service.NavItemCrudService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/nav-items")
public class NavItemController extends BaseCrudController<NavItem> {

    public NavItemController(NavItemCrudService service) {
        super(service);
    }
}
    