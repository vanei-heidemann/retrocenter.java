package com.javanei.retrocenter.clrmamepro.persistence;

import com.javanei.retrocenter.clrmamepro.entity.CMProGameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface CMProGameDAO extends JpaRepository<CMProGameEntity, Long> {
    CMProGameEntity findByDatafileAndName(@Param("datafileName") String datafileName,
                                          @Param("catalog") String catalog,
                                          @Param("version") String version,
                                          @Param("name") String name);
}
