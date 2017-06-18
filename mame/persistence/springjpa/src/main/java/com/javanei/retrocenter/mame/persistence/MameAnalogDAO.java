package com.javanei.retrocenter.mame.persistence;

import com.javanei.retrocenter.mame.entity.MameAnalogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MameAnalogDAO extends JpaRepository<MameAnalogEntity, Long> {
}
