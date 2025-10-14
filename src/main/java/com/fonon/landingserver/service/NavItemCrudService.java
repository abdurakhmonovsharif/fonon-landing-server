package com.fonon.landingserver.service;

import com.fonon.landingserver.domain.NavItem;
import com.fonon.landingserver.repository.NavItemRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class NavItemCrudService extends CrudService<NavItem> {

    private final NavItemRepository repository;

    public NavItemCrudService(NavItemRepository repository) {
        super(repository, "NavItem");
        this.repository = repository;
    }

    @Override
    public List<NavItem> findAll() {
        return repository.findAllByParentIsNullOrderByIdAsc();
    }
}
