package com.javanei.retrocenter.mame.persistence;

import com.javanei.retrocenter.mame.entity.MameInputEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MameInputDAO extends JpaRepository<MameInputEntity, Long> {
}
