package com.javanei.retrocenter.mame.persistence;

import com.javanei.retrocenter.mame.entity.MameRomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MameRomDAO extends JpaRepository<MameRomEntity, Long> {
}
