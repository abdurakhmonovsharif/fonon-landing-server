package com.fonon.landingserver.service;

import com.fonon.landingserver.domain.NavItem;
import com.fonon.landingserver.repository.NavItemRepository;
import org.springframework.stereotype.Service;

@Service
public class NavItemCrudService extends CrudService<NavItem> {

    public NavItemCrudService(NavItemRepository repository) {
        super(repository, "NavItem");
    }
}
