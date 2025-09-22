package com.fonon.landingserver.repository;

import com.fonon.landingserver.domain.Famous;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FamousRepository extends JpaRepository<Famous, Long> {
}
