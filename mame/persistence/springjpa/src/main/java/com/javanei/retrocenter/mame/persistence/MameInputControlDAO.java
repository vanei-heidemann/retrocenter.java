package com.javanei.retrocenter.mame.persistence;

import com.javanei.retrocenter.mame.entity.MameInputControlEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MameInputControlDAO extends JpaRepository<MameInputControlEntity, Long> {
}
