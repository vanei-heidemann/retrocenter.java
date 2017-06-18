package com.javanei.retrocenter.clrmamepro.persistence;

import com.javanei.retrocenter.clrmamepro.entity.CMProGameEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CMProGameDAO extends JpaRepository<CMProGameEntity, Long> {
}
