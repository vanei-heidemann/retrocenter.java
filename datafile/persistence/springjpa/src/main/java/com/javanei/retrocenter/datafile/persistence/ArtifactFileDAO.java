package com.javanei.retrocenter.datafile.persistence;

import com.javanei.retrocenter.datafile.entity.DatafileArtifactFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtifactFileDAO extends JpaRepository<DatafileArtifactFileEntity, Long> {
}
