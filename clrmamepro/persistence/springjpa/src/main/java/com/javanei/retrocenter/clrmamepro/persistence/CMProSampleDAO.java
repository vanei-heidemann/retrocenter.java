package com.javanei.retrocenter.clrmamepro.persistence;

import com.javanei.retrocenter.clrmamepro.entity.CMProSampleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CMProSampleDAO extends JpaRepository<CMProSampleEntity, Long> {
}
