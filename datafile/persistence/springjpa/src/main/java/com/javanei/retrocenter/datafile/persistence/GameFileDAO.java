package com.javanei.retrocenter.datafile.persistence;

import com.javanei.retrocenter.datafile.entity.GameFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameFileDAO extends JpaRepository<GameFileEntity, Long> {
}
