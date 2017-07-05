package com.javanei.retrocenter.clrmamepro.persistence;

import com.javanei.retrocenter.clrmamepro.entity.CMProResourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface CMProResourceDAO extends JpaRepository<CMProResourceEntity, Long> {
    CMProResourceEntity findByDatafileAndName(@Param("datafileName") String datafileName,
                                              @Param("category") String category,
                                              @Param("version") String version,
                                              @Param("name") String name);
}
