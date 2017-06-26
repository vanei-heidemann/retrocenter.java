package com.javanei.retrocenter.logiqx.persistence;

import com.javanei.retrocenter.logiqx.entity.LogiqxReleaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogiqxReleaseDAO extends JpaRepository<LogiqxReleaseEntity, Long> {
}
