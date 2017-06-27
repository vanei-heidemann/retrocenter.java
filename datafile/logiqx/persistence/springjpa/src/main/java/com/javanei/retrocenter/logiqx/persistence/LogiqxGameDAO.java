package com.javanei.retrocenter.logiqx.persistence;

import com.javanei.retrocenter.logiqx.entity.LogiqxGameEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogiqxGameDAO extends JpaRepository<LogiqxGameEntity, Long> {
}
