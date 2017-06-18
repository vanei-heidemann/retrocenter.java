package com.javanei.retrocenter.mame.persistence;

import com.javanei.retrocenter.mame.entity.MameDeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MameDeviceDAO extends JpaRepository<MameDeviceEntity, Long> {
}
