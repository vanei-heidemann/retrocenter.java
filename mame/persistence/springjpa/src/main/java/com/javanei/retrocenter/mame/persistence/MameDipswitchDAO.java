package com.javanei.retrocenter.mame.persistence;

import com.javanei.retrocenter.mame.entity.MameDipswitchEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MameDipswitchDAO extends JpaRepository<MameDipswitchEntity, Long> {
}
