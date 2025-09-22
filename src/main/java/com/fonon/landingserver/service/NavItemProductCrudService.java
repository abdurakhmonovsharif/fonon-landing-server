package com.fonon.landingserver.service;

import com.fonon.landingserver.domain.NavItemProduct;
import com.fonon.landingserver.repository.NavItemProductRepository;
import org.springframework.stereotype.Service;

@Service
public class NavItemProductCrudService extends CrudService<NavItemProduct> {

    public NavItemProductCrudService(NavItemProductRepository repository) {
        super(repository, "NavItemProduct");
    }
}
