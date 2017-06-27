package com.javanei.retrocenter.logiqx.persistence;

import com.javanei.retrocenter.logiqx.entity.LogiqxRomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogiqxRomDAO extends JpaRepository<LogiqxRomEntity, Long> {
}
