package com.fonon.landingserver.repository;

import com.fonon.landingserver.domain.NavItemProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NavItemProductRepository extends JpaRepository<NavItemProduct, Long> {
}
