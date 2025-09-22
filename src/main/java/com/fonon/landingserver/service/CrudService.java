package com.fonon.landingserver.service;

import com.fonon.landingserver.domain.Identifiable;
import com.fonon.landingserver.exception.BadRequestException;
import com.fonon.landingserver.exception.ResourceNotFoundException;
import com.fonon.landingserver.util.BeanCopyUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public class CrudService<T extends Identifiable> {

    private final JpaRepository<T, Long> repository;
    private final String resourceName;

    public CrudService(JpaRepository<T, Long> repository, String resourceName) {
        this.repository = repository;
        this.resourceName = resourceName;
    }

    public List<T> findAll() {
        return repository.findAll();
    }

    public T findOne(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(resourceName, id));
    }

    public T create(T payload) {
        if (payload == null) {
            throw new BadRequestException("Request body is required");
        }
        payload.setId(null);
        return repository.save(payload);
    }

    public T update(Long id, T payload) {
        if (payload == null) {
            throw new BadRequestException("Request body is required");
        }
        T existing = findOne(id);
        BeanUtils.copyProperties(payload, existing, BeanCopyUtils.getNullPropertyNames(payload, "id", "createdAt", "updatedAt"));
        existing.setId(id);
        return repository.save(existing);
    }

    public void remove(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException(resourceName, id);
        }
        repository.deleteById(id);
    }
}
