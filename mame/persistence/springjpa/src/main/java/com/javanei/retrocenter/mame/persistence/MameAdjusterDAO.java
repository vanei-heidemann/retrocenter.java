package com.javanei.retrocenter.mame.persistence;

import com.javanei.retrocenter.mame.entity.MameAdjusterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MameAdjusterDAO extends JpaRepository<MameAdjusterEntity, Long> {
}
