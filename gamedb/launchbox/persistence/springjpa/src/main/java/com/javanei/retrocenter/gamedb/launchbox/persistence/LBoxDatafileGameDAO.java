package com.javanei.retrocenter.gamedb.launchbox.persistence;

import com.javanei.retrocenter.gamedb.launchbox.entity.LBoxDatafileGameEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LBoxDatafileGameDAO extends JpaRepository<LBoxDatafileGameEntity, Long> {
    List<LBoxDatafileGameEntity> findByDatafile_Id(Long id);

    List<LBoxDatafileGameEntity> findByDatafile_Version(String version);
}
