package com.javanei.retrocenter.logiqx.persistence;

import com.javanei.retrocenter.logiqx.entity.LogiqxArchiveEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogiqxArchiveDAO extends JpaRepository<LogiqxArchiveEntity, Long> {
}
