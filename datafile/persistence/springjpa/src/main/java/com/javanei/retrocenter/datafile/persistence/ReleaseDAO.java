package com.javanei.retrocenter.datafile.persistence;

import com.javanei.retrocenter.datafile.entity.ReleaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReleaseDAO extends JpaRepository<ReleaseEntity, Long> {
}
