package com.javanei.retrocenter.mame.persistence;

import com.javanei.retrocenter.mame.entity.MameSampleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MameSampleDAO extends JpaRepository<MameSampleEntity, Long> {
}
