package com.javanei.retrocenter.mame.persistence;

import com.javanei.retrocenter.mame.entity.MameSoftwarelistEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MameSoftwarelistDAO extends JpaRepository<MameSoftwarelistEntity, Long> {
}
