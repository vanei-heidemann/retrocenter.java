package com.javanei.retrocenter.mame.persistence;

import com.javanei.retrocenter.mame.entity.MameDeviceInstanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MameDeviceInstanceDAO extends JpaRepository<MameDeviceInstanceEntity, Long> {
}
