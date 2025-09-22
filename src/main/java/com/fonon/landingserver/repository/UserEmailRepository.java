package com.fonon.landingserver.repository;

import com.fonon.landingserver.domain.UserEmail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEmailRepository extends JpaRepository<UserEmail, Long> {
}
