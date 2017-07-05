package com.javanei.retrocenter.logiqx.persistence;

import com.javanei.retrocenter.logiqx.entity.LogiqxGameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface LogiqxGameDAO extends JpaRepository<LogiqxGameEntity, Long> {
    LogiqxGameEntity findByDatafileAndName(@Param("datafileName") String datafileName, @Param("category") String category, @Param("version") String version, @Param("name") String name);
}
