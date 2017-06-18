package com.javanei.retrocenter.mame.persistence;

import com.javanei.retrocenter.mame.entity.MameDisplayEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MameDisplayDAO extends JpaRepository<MameDisplayEntity, Long> {
}
