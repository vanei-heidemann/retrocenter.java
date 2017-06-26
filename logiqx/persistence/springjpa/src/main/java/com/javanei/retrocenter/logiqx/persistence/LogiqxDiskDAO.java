package com.javanei.retrocenter.logiqx.persistence;

import com.javanei.retrocenter.logiqx.entity.LogiqxDiskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogiqxDiskDAO extends JpaRepository<LogiqxDiskEntity, Long> {
}
