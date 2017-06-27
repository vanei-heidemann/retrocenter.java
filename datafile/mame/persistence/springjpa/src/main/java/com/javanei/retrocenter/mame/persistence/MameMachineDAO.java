package com.javanei.retrocenter.mame.persistence;

import com.javanei.retrocenter.mame.entity.MameMachineEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MameMachineDAO extends JpaRepository<MameMachineEntity, Long> {
    List<MameMachineEntity> findByBuild(String build);

    MameMachineEntity findByBuildAndName(String build, String name);
}
