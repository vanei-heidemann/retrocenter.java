package com.javanei.retrocenter.goodtools.persistence;

import com.javanei.retrocenter.goodtools.entity.GoodDatafileRomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface GoodDatafileRomDAO extends JpaRepository<GoodDatafileRomEntity, Long> {
    GoodDatafileRomEntity findByDatafileAndName(@Param("datafileName") String datafileName, @Param("version") String version, @Param("name") String name);
}
