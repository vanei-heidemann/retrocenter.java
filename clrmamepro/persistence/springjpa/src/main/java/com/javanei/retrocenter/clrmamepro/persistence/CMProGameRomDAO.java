package com.javanei.retrocenter.clrmamepro.persistence;

import com.javanei.retrocenter.clrmamepro.entity.CMProGameRomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CMProGameRomDAO extends JpaRepository<CMProGameRomEntity, Long> {
}
