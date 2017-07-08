package com.javanei.retrocenter.hyperlist.persistence;

import com.javanei.retrocenter.hyperlist.entity.HyperListGameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface HyperListGameDAO extends JpaRepository<HyperListGameEntity, Long> {
    HyperListGameEntity findByDatafileAndName(@Param("datafileName") String datafileName,
                                              @Param("lastUpdate") String lastUpdate,
                                              @Param("version") String version,
                                              @Param("name") String name,
                                              @Param("index") String index,
                                              @Param("image") String image);
}
