package com.javanei.retrocenter.mame.persistence;

import com.javanei.retrocenter.mame.entity.MameDipvalueEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MameDipvalueDAO extends JpaRepository<MameDipvalueEntity, Long> {
}
