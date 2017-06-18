package com.javanei.retrocenter.mame.persistence;

import com.javanei.retrocenter.mame.entity.MameConfigurationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MameConfigurationDAO extends JpaRepository<MameConfigurationEntity, Long> {
}
