package com.fonon.landingserver.controller;

import com.fonon.landingserver.domain.Identifiable;
import com.fonon.landingserver.service.CrudService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

public abstract class BaseCrudController<T extends Identifiable> {

    private final CrudService<T> service;

    protected BaseCrudController(CrudService<T> service) {
        this.service = service;
    }

    @GetMapping
    public List<T> list() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public T get(@PathVariable Long id) {
        return service.findOne(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public T create(@Valid @RequestBody T payload) {
        return service.create(payload);
    }

    @PutMapping("/{id}")
    public T update(@PathVariable Long id, @Valid @RequestBody T payload) {
        return service.update(id, payload);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.remove(id);
    }
}
