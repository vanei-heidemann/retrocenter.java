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
import com.javanei.retrocenter.logiqx.persistence.LogiqxDatafileDAO;
import com.javanei.retrocenter.logiqx.persistence.LogiqxGameDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LogiqxService {
    private static final Logger LOG = LoggerFactory.getLogger(LogiqxService.class);

    @Autowired
    private LogiqxDatafileDAO datafileDAO;
    @Autowired
    private LogiqxGameDAO gameDAO;
    @Autowired
    private LogiqxService logiqxService;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public LogiqxDatafileEntity create(LogiqxDatafileEntity entity) {
        LOG.debug("create(name=" + entity.getName() + ", category=" + entity.getCategory() + ", version=" + entity.getVersion() + ")");
        LogiqxDatafileEntity old = datafileDAO.findByUnique(entity.getName(), entity.getCategory(), entity.getVersion());
        if (old == null) {
            entity = datafileDAO.saveAndFlush(entity);
        } else {
            LOG.debug("Datafile already exist");
            entity.setId(old.getId());
        }
        return entity;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public LogiqxGameEntity createGame(LogiqxGameEntity entity) {
        LOG.debug("create(name=" + entity.getName() + ")");
        LogiqxGameEntity old = gameDAO.findByDatafileAndName(entity.getDatafile().getName(),
                entity.getDatafile().getCategory(), entity.getDatafile().getVersion(), entity.getName());
        if (old == null) {
            entity = gameDAO.saveAndFlush(entity);
        } else {
            entity.setId(old.getId());
        }
        return entity;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public LogiqxDatafile create(LogiqxDatafile datafile) {
        LOG.info("create(name=" + datafile.getHeader().getName()
                + ", category=" + datafile.getHeader().getCategory()
                + ", version=" + datafile.getHeader().getVersion()
                + ", games=" + datafile.getGames().size()
                + ")");
        LogiqxDatafileEntity entity = new LogiqxDatafileEntity(datafile);
        entity = logiqxService.create(entity);

        int cont = 0;
        for (LogiqxGame game : datafile.getGames()) {
            LogiqxGameEntity gameEntity = new LogiqxGameEntity(game);
            gameEntity.setDatafile(entity);

            for (LogiqxRelease release : game.getReleases()) {
                LogiqxReleaseEntity releaseEntity = new LogiqxReleaseEntity(release);
                releaseEntity.setGame(gameEntity);
                gameEntity.getReleases().add(releaseEntity);
            }

            for (LogiqxBiosset biosset : game.getBiossets()) {
                LogiqxBiossetEntity biossetEntity = new LogiqxBiossetEntity(biosset);
                biossetEntity.setGame(gameEntity);
                gameEntity.getBiossets().add(biossetEntity);
            }

            for (LogiqxRom rom : game.getRoms()) {
                LogiqxRomEntity romEntity = new LogiqxRomEntity(rom);
                romEntity.setGame(gameEntity);
                gameEntity.getRoms().add(romEntity);
            }

            for (LogiqxDisk disk : game.getDisks()) {
                LogiqxDiskEntity diskEntity = new LogiqxDiskEntity(disk);
                diskEntity.setGame(gameEntity);
                gameEntity.getDisks().add(diskEntity);
            }

            for (LogiqxSample sample : game.getSamples()) {
                LogiqxSampleEntity sampleEntity = new LogiqxSampleEntity(sample);
                sampleEntity.setGame(gameEntity);
                gameEntity.getSamples().add(sampleEntity);
            }

            for (LogiqxArchive archive : game.getArchives()) {
                LogiqxArchiveEntity archiveEntity = new LogiqxArchiveEntity(archive);
                archiveEntity.setGame(gameEntity);
                gameEntity.getArchives().add(archiveEntity);
            }

            logiqxService.createGame(gameEntity);
            entity.getGames().add(gameEntity);

            cont++;
            if (cont % 100 == 0) {
                LOG.info("Saved game " + cont + " of " + datafile.getGames().size() + ": " + game.getName());
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
