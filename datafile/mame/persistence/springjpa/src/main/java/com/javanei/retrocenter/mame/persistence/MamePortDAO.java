package com.javanei.retrocenter.mame.persistence;

import com.javanei.retrocenter.mame.entity.MamePortEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MamePortDAO extends JpaRepository<MamePortEntity, Long> {
}
