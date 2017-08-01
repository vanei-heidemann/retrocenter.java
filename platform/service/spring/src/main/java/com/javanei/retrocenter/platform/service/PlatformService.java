package com.javanei.retrocenter.platform.service;

import com.javanei.retrocenter.common.Identified;
import com.javanei.retrocenter.platform.common.Platform;
import com.javanei.retrocenter.platform.entity.PlatformAltNameEntity;
import com.javanei.retrocenter.platform.entity.PlatformEntity;
import com.javanei.retrocenter.platform.persistence.PlatformAltNameDAO;
import com.javanei.retrocenter.platform.persistence.PlatformDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class PlatformService {
    private static final Logger LOG = LoggerFactory.getLogger(PlatformService.class);

    @Autowired
    private PlatformDAO platformDAO;
    @Autowired
    private PlatformAltNameDAO platformAltNameDAO;

    @Transactional(propagation = Propagation.REQUIRED)
    public Identified<Platform> createPlatform(Platform platform) {
        PlatformEntity entity = new PlatformEntity(platform.getName(), platform.getShortName(), platform.getStorageFolder());
        if (!platform.getAlternateNames().isEmpty()) {
            Set<PlatformAltNameEntity> alts = new HashSet<>();
            for (String s : platform.getAlternateNames()) {
                PlatformAltNameEntity e = new PlatformAltNameEntity(s);
                e.setPlatform(entity);
                alts.add(e);
            }
            entity.setAlternateNames(alts);
        }
        entity = platformDAO.saveAndFlush(entity);
        return new Identified<Platform>(entity.getId(), platform);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public Identified<Platform> findPlatformByName(String name) {
        PlatformEntity entity = platformDAO.findByName(name);
        if (entity != null) {
            Platform p = new Platform(entity.getName(), entity.getShortName(), entity.getStorageFolder());
            Set<PlatformAltNameEntity> alts = entity.getAlternateNames();
            if (!alts.isEmpty()) {
                for (PlatformAltNameEntity alt : alts) {
                    p.addAlternateName(alt.getAlternateName());
                }
            }
            return new Identified<>(entity.getId(), p);
        }
        return null;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public Identified<Platform> findPlatform(String name) {
        PlatformEntity entity = platformDAO.findByName(name);
        if (entity == null) {
            PlatformAltNameEntity altEntity = platformAltNameDAO.findByAlternateName(name);
            if (altEntity != null) {
                entity = altEntity.getPlatform();
            }
        }
        if (entity != null) {
            Platform p = new Platform(entity.getName(), entity.getShortName(), entity.getStorageFolder());
            Set<PlatformAltNameEntity> alts = entity.getAlternateNames();
            if (!alts.isEmpty()) {
                for (PlatformAltNameEntity alt : alts) {
                    p.addAlternateName(alt.getAlternateName());
                }
            }
            return new Identified<>(entity.getId(), p);
        }
        return null;
    }

    public List<Identified<Platform>> findAllPlatforms() {
        List<PlatformEntity> entities = platformDAO.findAll();
        List<Identified<Platform>> result = new ArrayList<>();
        for (PlatformEntity entity : entities) {
            Platform p = new Platform(entity.getName(), entity.getShortName(), entity.getStorageFolder());
            Set<PlatformAltNameEntity> alts = entity.getAlternateNames();
            if (!alts.isEmpty()) {
                for (PlatformAltNameEntity alt : alts) {
                    p.addAlternateName(alt.getAlternateName());
                }
            }
            result.add(new Identified<>(entity.getId(), p));
        }
        return result;
    }
}
