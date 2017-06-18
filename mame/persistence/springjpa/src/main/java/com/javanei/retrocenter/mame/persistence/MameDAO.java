package com.javanei.retrocenter.mame.persistence;

import com.javanei.retrocenter.mame.entity.MameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface MameDAO extends JpaRepository<MameEntity, Long> {
    MameEntity findByBuild(@Param("build") String build);

    MameEntity findByBuildFull(@Param("build") String build);
}
