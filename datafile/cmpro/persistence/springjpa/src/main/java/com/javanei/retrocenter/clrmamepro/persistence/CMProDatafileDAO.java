package com.javanei.retrocenter.clrmamepro.persistence;

import com.javanei.retrocenter.clrmamepro.entity.CMProDatafileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface CMProDatafileDAO extends JpaRepository<CMProDatafileEntity, Long> {
    CMProDatafileEntity findByUniqueFull(@Param("name") String name, @Param("category") String category, @Param("version") String version);

    CMProDatafileEntity findByUnique(@Param("name") String name, @Param("category") String category, @Param("version") String version);
}
