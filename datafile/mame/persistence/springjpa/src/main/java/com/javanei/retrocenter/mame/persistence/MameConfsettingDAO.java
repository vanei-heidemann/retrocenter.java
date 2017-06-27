package com.javanei.retrocenter.mame.persistence;

import com.javanei.retrocenter.mame.entity.MameConfsettingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MameConfsettingDAO extends JpaRepository<MameConfsettingEntity, Long> {
}
