package com.javanei.retrocenter.mame.persistence;

import com.javanei.retrocenter.mame.entity.MameSoundEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MameSoundDAO extends JpaRepository<MameSoundEntity, Long> {
}
