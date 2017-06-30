package com.javanei.retrocenter.datafile.persistence;

import com.javanei.retrocenter.datafile.entity.ArtifactEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameDAO extends JpaRepository<ArtifactEntity, Long> {
}
