package com.javanei.retrocenter.goodtools.persistence;

import com.javanei.retrocenter.goodtools.entity.GoodDatafileEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface GoodDatafileDAO extends JpaRepository<GoodDatafileEntity, Long> {
    GoodDatafileEntity findByUniqueFull(@Param("name") String name, @Param("version") String version);

    GoodDatafileEntity findByUnique(@Param("name") String name, @Param("version") String version);

    Page<GoodDatafileEntity> findByNameLike(@Param("name") String name, Pageable pageable);
}
