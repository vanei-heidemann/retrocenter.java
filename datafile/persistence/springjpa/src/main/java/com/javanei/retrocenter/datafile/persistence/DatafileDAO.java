package com.javanei.retrocenter.datafile.persistence;

import com.javanei.retrocenter.datafile.entity.DatafileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface DatafileDAO extends JpaRepository<DatafileEntity, Long> {
    DatafileEntity findByUniqueFull(@Param("name") String name, @Param("category") String category, @Param("version") String version);

    DatafileEntity findByUnique(@Param("name") String name, @Param("category") String category, @Param("version") String version);
}
