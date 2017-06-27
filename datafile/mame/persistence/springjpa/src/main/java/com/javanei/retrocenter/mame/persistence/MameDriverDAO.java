package com.javanei.retrocenter.mame.persistence;

import com.javanei.retrocenter.mame.entity.MameDriverEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MameDriverDAO extends JpaRepository<MameDriverEntity, Long> {
}
