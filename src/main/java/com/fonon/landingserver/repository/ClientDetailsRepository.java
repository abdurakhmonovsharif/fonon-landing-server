package com.fonon.landingserver.repository;

import com.fonon.landingserver.domain.ClientDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientDetailsRepository extends JpaRepository<ClientDetails, Long> {
}
