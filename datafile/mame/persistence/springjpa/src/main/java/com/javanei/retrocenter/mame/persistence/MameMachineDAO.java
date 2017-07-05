package com.javanei.retrocenter.mame.persistence;

import com.javanei.retrocenter.mame.entity.MameMachineEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface MameMachineDAO extends JpaRepository<MameMachineEntity, Long> {
    List<MameMachineEntity> findByBuild(@Param("build") String build);

    MameMachineEntity findByBuildAndName(@Param("build") String build, @Param("name") String name);
}
