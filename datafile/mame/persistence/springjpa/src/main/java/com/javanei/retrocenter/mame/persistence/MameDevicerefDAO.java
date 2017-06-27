package com.javanei.retrocenter.mame.persistence;

import com.javanei.retrocenter.mame.entity.MameDevicerefEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MameDevicerefDAO extends JpaRepository<MameDevicerefEntity, Long> {
}
