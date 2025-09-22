package com.fonon.landingserver.controller;

import com.fonon.landingserver.domain.UserEmail;
import com.fonon.landingserver.service.UserEmailCrudService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user-emails")
public class UserEmailController extends BaseCrudController<UserEmail> {

    public UserEmailController(UserEmailCrudService service) {
        super(service);
    }
}
