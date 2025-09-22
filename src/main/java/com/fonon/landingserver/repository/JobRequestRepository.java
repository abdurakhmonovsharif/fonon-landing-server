package com.fonon.landingserver.repository;

import com.fonon.landingserver.domain.JobRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRequestRepository extends JpaRepository<JobRequest, Long> {
}
