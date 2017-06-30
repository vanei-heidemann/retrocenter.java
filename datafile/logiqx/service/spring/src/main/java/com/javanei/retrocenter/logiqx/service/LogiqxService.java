package com.javanei.retrocenter.logiqx.service;

import com.javanei.retrocenter.logiqx.LogiqxArchive;
import com.javanei.retrocenter.logiqx.LogiqxBiosset;
import com.javanei.retrocenter.logiqx.LogiqxDatafile;
import com.javanei.retrocenter.logiqx.LogiqxDisk;
import com.javanei.retrocenter.logiqx.LogiqxGame;
import com.javanei.retrocenter.logiqx.LogiqxRelease;
import com.javanei.retrocenter.logiqx.LogiqxRom;
import com.javanei.retrocenter.logiqx.LogiqxSample;
import com.javanei.retrocenter.logiqx.entity.LogiqxArchiveEntity;
import com.javanei.retrocenter.logiqx.entity.LogiqxBiossetEntity;
import com.javanei.retrocenter.logiqx.entity.LogiqxDatafileEntity;
import com.javanei.retrocenter.logiqx.entity.LogiqxDiskEntity;
import com.javanei.retrocenter.logiqx.entity.LogiqxGameEntity;
import com.javanei.retrocenter.logiqx.entity.LogiqxReleaseEntity;
import com.javanei.retrocenter.logiqx.entity.LogiqxRomEntity;
import com.javanei.retrocenter.logiqx.entity.LogiqxSampleEntity;
import com.javanei.retrocenter.logiqx.persistence.LogiqxArchiveDAO;
import com.javanei.retrocenter.logiqx.persistence.LogiqxBiossetDAO;
import com.javanei.retrocenter.logiqx.persistence.LogiqxDatafileDAO;
import com.javanei.retrocenter.logiqx.persistence.LogiqxDiskDAO;
import com.javanei.retrocenter.logiqx.persistence.LogiqxGameDAO;
import com.javanei.retrocenter.logiqx.persistence.LogiqxReleaseDAO;
import com.javanei.retrocenter.logiqx.persistence.LogiqxRomDAO;
import com.javanei.retrocenter.logiqx.persistence.LogiqxSampleDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LogiqxService {
    @Autowired
    private LogiqxDatafileDAO datafileDAO;
    @Autowired
    private LogiqxArchiveDAO archiveDAO;
    @Autowired
    private LogiqxBiossetDAO biossetDAO;
    @Autowired
    private LogiqxDiskDAO diskDAO;
    @Autowired
    private LogiqxGameDAO gameDAO;
    @Autowired
    private LogiqxReleaseDAO releaseDAO;
    @Autowired
    private LogiqxRomDAO romDAO;
    @Autowired
    private LogiqxSampleDAO sampleDAO;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public LogiqxDatafile create(LogiqxDatafile datafile) {
        LogiqxDatafileEntity entity = new LogiqxDatafileEntity(datafile);
        entity = datafileDAO.saveAndFlush(entity);

        for (LogiqxGame game : datafile.getGames()) {
            LogiqxGameEntity gameEntity = new LogiqxGameEntity(game);
            gameEntity.setDatafile(entity);
            gameEntity = gameDAO.saveAndFlush(gameEntity);
            entity.getGames().add(gameEntity);

            for (LogiqxRelease release : game.getReleases()) {
                LogiqxReleaseEntity releaseEntity = new LogiqxReleaseEntity(release);
                releaseEntity.setGame(gameEntity);
                releaseDAO.saveAndFlush(releaseEntity);
                gameEntity.getReleases().add(releaseEntity);
            }

            for (LogiqxBiosset biosset : game.getBiossets()) {
                LogiqxBiossetEntity biossetEntity = new LogiqxBiossetEntity(biosset);
                biossetEntity.setGame(gameEntity);
                biossetDAO.saveAndFlush(biossetEntity);
                gameEntity.getBiossets().add(biossetEntity);
            }

            for (LogiqxRom rom : game.getRoms()) {
                LogiqxRomEntity romEntity = new LogiqxRomEntity(rom);
                romEntity.setGame(gameEntity);
                romDAO.saveAndFlush(romEntity);
                gameEntity.getRoms().add(romEntity);
            }

            for (LogiqxDisk disk : game.getDisks()) {
                LogiqxDiskEntity diskEntity = new LogiqxDiskEntity(disk);
                diskEntity.setGame(gameEntity);
                diskDAO.saveAndFlush(diskEntity);
                gameEntity.getDisks().add(diskEntity);
            }

            for (LogiqxSample sample : game.getSamples()) {
                LogiqxSampleEntity sampleEntity = new LogiqxSampleEntity(sample);
                sampleEntity.setGame(gameEntity);
                sampleDAO.saveAndFlush(sampleEntity);
                gameEntity.getSamples().add(sampleEntity);
            }

            for (LogiqxArchive archive : game.getArchives()) {
                LogiqxArchiveEntity archiveEntity = new LogiqxArchiveEntity(archive);
                archiveEntity.setGame(gameEntity);
                archiveDAO.saveAndFlush(archiveEntity);
                gameEntity.getArchives().add(archiveEntity);
            }
        }

        return entity.toVO();
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public LogiqxDatafile findByUnique(String name, String category, String version) {
        LogiqxDatafileEntity entity = datafileDAO.findByUnique(name, category, version);
        if (entity != null) {
            return entity.toVO();
        }
        return null;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public LogiqxDatafile findByUniqueFull(String name, String category, String version) {
        LogiqxDatafileEntity entity = datafileDAO.findByUnique(name, category, version);
        if (entity != null) {
            return entity.toVO();
        }
        return null;
    }
}
