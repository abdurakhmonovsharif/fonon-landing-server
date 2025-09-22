package com.fonon.landingserver.service;

import com.fonon.landingserver.domain.Journal;
import com.fonon.landingserver.repository.JournalRepository;
import org.springframework.stereotype.Service;

@Service
public class JournalCrudService extends CrudService<Journal> {

    public JournalCrudService(JournalRepository repository) {
        super(repository, "Journal");
    }
}
