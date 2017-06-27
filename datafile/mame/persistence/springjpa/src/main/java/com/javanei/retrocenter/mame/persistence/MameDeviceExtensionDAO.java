package com.javanei.retrocenter.mame.persistence;

import com.javanei.retrocenter.mame.entity.MameDeviceExtensionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MameDeviceExtensionDAO extends JpaRepository<MameDeviceExtensionEntity, Long> {
}
