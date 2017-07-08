package com.javanei.retrocenter.hyperlist.persistence;

import com.javanei.retrocenter.hyperlist.entity.HyperListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface HyperListDAO extends JpaRepository<HyperListEntity, Long> {
    HyperListEntity findByUniqueFull(@Param("name") String name, @Param("lastUpdate") String lastUpdate, @Param("version") String version);

    HyperListEntity findByUnique(@Param("name") String name, @Param("lastUpdate") String lastUpdate, @Param("version") String version);
}
