package com.fonon.landingserver.controller;

import com.fonon.landingserver.domain.Journal;
import com.fonon.landingserver.service.JournalCrudService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/journals")
public class JournalController extends BaseCrudController<Journal> {

    public JournalController(JournalCrudService service) {
        super(service);
    }
}
