package com.javanei.retrocenter.mame.persistence;

import com.javanei.retrocenter.mame.entity.MameBiossetEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MameBiossetDAO extends JpaRepository<MameBiossetEntity, Long> {
}
