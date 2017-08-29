package com.javanei.retrocenter.platform.persistence;

import com.javanei.retrocenter.platform.entity.PlatformArtifactFileImportHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlatformArtifactFileImportHistoryDAO extends JpaRepository<PlatformArtifactFileImportHistoryEntity, Long> {
    List<PlatformArtifactFileImportHistoryEntity> findByPlatform_id(Long platformId);

    PlatformArtifactFileImportHistoryEntity findByPlatform_idAndDescription(Long platformId, String description);
}
