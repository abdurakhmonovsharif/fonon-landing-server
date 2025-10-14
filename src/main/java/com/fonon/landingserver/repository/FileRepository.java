package com.fonon.landingserver.repository;

import com.fonon.landingserver.domain.FileEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<FileEntity, Long> {

    Optional<FileEntity> findByRelativePath(String relativePath);

    long deleteByRelativePath(String relativePath);
}
