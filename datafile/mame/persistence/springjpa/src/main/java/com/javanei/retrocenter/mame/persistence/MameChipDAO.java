package com.javanei.retrocenter.mame.persistence;

import com.javanei.retrocenter.mame.entity.MameChipEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MameChipDAO extends JpaRepository<MameChipEntity, Long> {
}
