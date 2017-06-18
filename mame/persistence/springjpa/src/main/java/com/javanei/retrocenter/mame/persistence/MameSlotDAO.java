package com.javanei.retrocenter.mame.persistence;

import com.javanei.retrocenter.mame.entity.MameSlotEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MameSlotDAO extends JpaRepository<MameSlotEntity, Long> {
}
