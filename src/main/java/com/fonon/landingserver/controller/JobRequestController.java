package com.fonon.landingserver.controller;

import com.fonon.landingserver.domain.JobRequest;
import com.fonon.landingserver.service.JobRequestCrudService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/job-requests")
public class JobRequestController extends BaseCrudController<JobRequest> {

    public JobRequestController(JobRequestCrudService service) {
        super(service);
    }
}
