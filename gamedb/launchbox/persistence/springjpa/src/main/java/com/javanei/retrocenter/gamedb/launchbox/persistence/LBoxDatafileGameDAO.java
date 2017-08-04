package com.javanei.retrocenter.gamedb.launchbox.persistence;

import com.javanei.retrocenter.gamedb.launchbox.entity.LBoxDatafileGameEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LBoxDatafileGameDAO extends JpaRepository<LBoxDatafileGameEntity, Long> {
    List<LBoxDatafileGameEntity> findByDatafile_Id(Long id);

    //@Query(value = "SELECT o FROM LBoxDatafileGameEntity o WHERE o.datafile.version = :version")
    List<LBoxDatafileGameEntity> findByDatafile_Version(@Param("version") String version);

    List<LBoxDatafileGameEntity> findByDatafile_Version(@Param("version") String version, Pageable pageable);

    //@Query(value = "SELECT o FROM LBoxDatafileGameEntity o WHERE o.datafile.version = :version AND o.game.name LIKE :name")
    List<LBoxDatafileGameEntity> findByDatafile_VersionAndGame_NameLike(
            @Param("version") String version,
            @Param("name") String name);

    List<LBoxDatafileGameEntity> findByDatafile_VersionAndGame_NameLike(
            @Param("version") String version,
            @Param("name") String name,
            Pageable pageable);
}
