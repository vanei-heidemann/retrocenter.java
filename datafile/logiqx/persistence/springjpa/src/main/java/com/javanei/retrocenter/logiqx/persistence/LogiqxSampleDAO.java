package com.javanei.retrocenter.logiqx.persistence;

import com.javanei.retrocenter.logiqx.entity.LogiqxSampleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogiqxSampleDAO extends JpaRepository<LogiqxSampleEntity, Long> {
}
