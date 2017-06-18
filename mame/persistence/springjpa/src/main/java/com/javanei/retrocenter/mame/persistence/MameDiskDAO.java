package com.javanei.retrocenter.mame.persistence;

import com.javanei.retrocenter.mame.entity.MameDiskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MameDiskDAO extends JpaRepository<MameDiskEntity, Long> {
}
