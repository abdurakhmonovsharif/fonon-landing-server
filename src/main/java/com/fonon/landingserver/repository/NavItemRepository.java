package com.fonon.landingserver.repository;

import com.fonon.landingserver.domain.NavItem;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NavItemRepository extends JpaRepository<NavItem, Long> {

    List<NavItem> findAllByParentIsNullOrderByIdAsc();
}
