package com.javanei.retrocenter.datafile.persistence;

import com.javanei.retrocenter.datafile.entity.ArtifactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface ArtifactDAO extends JpaRepository<ArtifactEntity, Long> {
    ArtifactEntity findByDatafileAndName(@Param("datafileName") String datafileName, @Param("category") String category,
                                         @Param("version") String version, @Param("name") String name);
}
