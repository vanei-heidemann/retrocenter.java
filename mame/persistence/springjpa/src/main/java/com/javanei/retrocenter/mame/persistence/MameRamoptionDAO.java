package com.javanei.retrocenter.mame.persistence;

import com.javanei.retrocenter.mame.entity.MameRamoptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MameRamoptionDAO extends JpaRepository<MameRamoptionEntity, Long> {
}
