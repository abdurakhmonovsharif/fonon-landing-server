package com.fonon.landingserver.repository;

import com.fonon.landingserver.domain.About;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AboutRepository extends JpaRepository<About, Long> {
    Optional<About> findBySlug(String slug);
}
