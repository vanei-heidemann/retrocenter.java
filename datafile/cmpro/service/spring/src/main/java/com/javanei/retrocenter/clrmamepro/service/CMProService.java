package com.javanei.retrocenter.clrmamepro.service;

import com.javanei.retrocenter.clrmamepro.CMProDatafile;
import com.javanei.retrocenter.clrmamepro.CMProDisk;
import com.javanei.retrocenter.clrmamepro.CMProGame;
import com.javanei.retrocenter.clrmamepro.CMProHeader;
import com.javanei.retrocenter.clrmamepro.CMProResource;
import com.javanei.retrocenter.clrmamepro.CMProRom;
import com.javanei.retrocenter.clrmamepro.entity.CMProCustomFieldEntity;
import com.javanei.retrocenter.clrmamepro.entity.CMProDatafileEntity;
import com.javanei.retrocenter.clrmamepro.entity.CMProDiskEntity;
import com.javanei.retrocenter.clrmamepro.entity.CMProGameEntity;
import com.javanei.retrocenter.clrmamepro.entity.CMProGameRomEntity;
import com.javanei.retrocenter.clrmamepro.entity.CMProResourceEntity;
import com.javanei.retrocenter.clrmamepro.entity.CMProResourceRomEntity;
import com.javanei.retrocenter.clrmamepro.entity.CMProSampleEntity;
import com.javanei.retrocenter.clrmamepro.entity.CMProSampleofEntity;
import com.javanei.retrocenter.clrmamepro.persistence.CMProCustomFieldDAO;
import com.javanei.retrocenter.clrmamepro.persistence.CMProDatafileDAO;
import com.javanei.retrocenter.clrmamepro.persistence.CMProDiskDAO;
import com.javanei.retrocenter.clrmamepro.persistence.CMProGameDAO;
import com.javanei.retrocenter.clrmamepro.persistence.CMProGameRomDAO;
import com.javanei.retrocenter.clrmamepro.persistence.CMProResourceDAO;
import com.javanei.retrocenter.clrmamepro.persistence.CMProResourceRomDAO;
import com.javanei.retrocenter.clrmamepro.persistence.CMProSampleDAO;
import com.javanei.retrocenter.clrmamepro.persistence.CMProSampleofDAO;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CMProService {
    @Autowired
    private CMProCustomFieldDAO customFieldDAO;
    @Autowired
    private CMProDatafileDAO datafileDAO;
    @Autowired
    private CMProDiskDAO diskDAO;
    @Autowired
    private CMProGameDAO gameDAO;
    @Autowired
    private CMProGameRomDAO gameRomDAO;
    @Autowired
    private CMProResourceDAO resourceDAO;
    @Autowired
    private CMProResourceRomDAO resourceRomDAO;
    @Autowired
    private CMProSampleDAO sampleDAO;
    @Autowired
    private CMProSampleofDAO sampleofDAO;

    private static CMProDatafileEntity datafileToEntity(CMProDatafile datafile) {
        CMProDatafileEntity entity = new CMProDatafileEntity(datafile.getHeader().getName(),
                datafile.getHeader().getCategory(), datafile.getHeader().getVersion(),
                datafile.getHeader().getDescription(), datafile.getHeader().getAuthor(),
                datafile.getHeader().getHomepage(), datafile.getHeader().getUrl(),
                datafile.getHeader().getForcemerging(), datafile.getHeader().getForcezipping());
        for (CMProGame game : datafile.getGames()) {
            entity.getGames().add(gameToEntity(game));
        }
        for (CMProResource resource : datafile.getResources()) {
            entity.getResources().add(resourceToEntity(resource));
        }
        for (String key : datafile.getHeader().getCustomFields().keySet()) {
            entity.getCustomFields().add(new CMProCustomFieldEntity(key, datafile.getHeader().getCustomField(key)));
        }

        return entity;
    }

    private static CMProGameEntity gameToEntity(CMProGame game) {
        CMProGameEntity entity = new CMProGameEntity(game.getName(), game.getDescription(), game.getYear(),
                game.getManufacturer(), game.getCloneof(), game.getRomof(), game.getSerial());

        for (CMProRom rom : game.getRoms()) {
            entity.getRoms().add(gameromToEntity(rom));
        }

        for (CMProDisk disk : game.getDisks()) {
            entity.getDisks().add(diskToEntity(disk));
        }

        for (String sampleof : game.getSampleof()) {
            entity.getSampleof().add(new CMProSampleofEntity(sampleof));
        }

        for (String sample : game.getSamples()) {
            entity.getSamples().add(new CMProSampleEntity(sample));
        }

        return entity;
    }

    private static CMProGameRomEntity gameromToEntity(CMProRom rom) {
        return new CMProGameRomEntity(rom.getName(), rom.getSize(), rom.getCrc(), rom.getSha1(), rom.getMd5(),
                rom.getRegion(), rom.getFlags());
    }

    private static CMProDiskEntity diskToEntity(CMProDisk disk) {
        return new CMProDiskEntity(disk.getName(), disk.getSha1(), disk.getMd5());
    }

    private static CMProResourceEntity resourceToEntity(CMProResource resource) {
        CMProResourceEntity entity = new CMProResourceEntity(resource.getName(), resource.getDescription(),
                resource.getYear(), resource.getManufacturer());
        for (CMProRom rom : resource.getRoms()) {
            entity.getRoms().add(resourceromToEntity(rom));
        }

        return entity;
    }

    private static CMProResourceRomEntity resourceromToEntity(CMProRom rom) {
        return new CMProResourceRomEntity(rom.getName(), rom.getSize(), rom.getCrc(), rom.getSha1(), rom.getMd5(),
                rom.getRegion(), rom.getFlags());
    }

    private static CMProDatafile entityToDatafile(CMProDatafileEntity datafile) {
        CMProDatafile entity = new CMProDatafile(new CMProHeader(datafile.getName(),
                datafile.getDescription(), datafile.getCategory(), datafile.getVersion(),
                datafile.getAuthor(), datafile.getHomepage(), datafile.getUrl(),
                datafile.getForcemerging(), datafile.getForcezipping()));
        for (CMProGameEntity game : datafile.getGames()) {
            entity.addGame(entityToGame(game));
        }
        for (CMProResourceEntity resource : datafile.getResources()) {
            entity.addResource(entityToResource(resource));
        }
        for (CMProCustomFieldEntity customFieldEntity : datafile.getCustomFields()) {
            entity.getHeader().addCustomField(customFieldEntity.getKey(), customFieldEntity.getValue());
        }

        return entity;
    }

    private static CMProGame entityToGame(CMProGameEntity game) {
        CMProGame entity = new CMProGame(game.getName(), game.getDescription(), game.getYear(),
                game.getManufacturer(), game.getCloneof(), game.getRomof(), game.getSerial());

        for (CMProGameRomEntity rom : game.getRoms()) {
            entity.addRom(entityToGamerom(rom));
        }

        for (CMProDiskEntity disk : game.getDisks()) {
            entity.addDisk(entityToDisk(disk));
        }

        for (CMProSampleofEntity sampleof : game.getSampleof()) {
            entity.addSampleOf(sampleof.getSampleof());
        }

        for (CMProSampleEntity sample : game.getSamples()) {
            entity.addSample(sample.getSample());
        }

        return entity;
    }

    private static CMProRom entityToGamerom(CMProGameRomEntity rom) {
        return new CMProRom(rom.getName(), rom.getSize(), rom.getCrc(), rom.getSha1(), rom.getMd5(),
                rom.getRegion(), rom.getFlags());
    }

    private static CMProDisk entityToDisk(CMProDiskEntity disk) {
        return new CMProDisk(disk.getName(), disk.getSha1(), disk.getMd5());
    }

    private static CMProResource entityToResource(CMProResourceEntity resource) {
        CMProResource entity = new CMProResource(resource.getName(), resource.getDescription(),
                resource.getYear(), resource.getManufacturer());
        for (CMProResourceRomEntity rom : resource.getRoms()) {
            entity.getRoms().add(entityToResourceROM(rom));
        }

        return entity;
    }

    private static CMProRom entityToResourceROM(CMProResourceRomEntity rom) {
        return new CMProRom(rom.getName(), rom.getSize(), rom.getCrc(), rom.getSha1(), rom.getMd5(),
                rom.getRegion(), rom.getFlags());
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public CMProDatafile create(CMProDatafile datafile) {
        CMProDatafileEntity entity = datafileToEntity(datafile);
        Set<CMProCustomFieldEntity> customFields = entity.getCustomFields();
        Set<CMProGameEntity> games = entity.getGames();
        Set<CMProResourceEntity> resources = entity.getResources();

        entity = datafileDAO.saveAndFlush(entity);
        for (CMProCustomFieldEntity customFieldEntity : customFields) {
            customFieldEntity.setDatafile(entity);
            customFieldDAO.saveAndFlush(customFieldEntity);
        }

        for (CMProGameEntity gameEntity : games) {
            Set<CMProGameRomEntity> roms = gameEntity.getRoms();
            Set<CMProDiskEntity> disks = gameEntity.getDisks();
            Set<CMProSampleofEntity> samplesof = gameEntity.getSampleof();
            Set<CMProSampleEntity> samples = gameEntity.getSamples();

            gameEntity.setDatafile(entity);
            gameEntity = gameDAO.saveAndFlush(gameEntity);

            for (CMProGameRomEntity rom : roms) {
                rom.setGame(gameEntity);
                gameRomDAO.saveAndFlush(rom);
            }

            for (CMProDiskEntity disk : disks) {
                disk.setGame(gameEntity);
                diskDAO.saveAndFlush(disk);
            }

            for (CMProSampleofEntity sampleof : samplesof) {
                sampleof.setGame(gameEntity);
                sampleofDAO.saveAndFlush(sampleof);
            }

            for (CMProSampleEntity sample : samples) {
                sample.setGame(gameEntity);
                sampleDAO.saveAndFlush(sample);
            }
        }

        for (CMProResourceEntity resourceEntity : resources) {
            Set<CMProResourceRomEntity> roms = resourceEntity.getRoms();

            resourceEntity.setDatafile(entity);
            resourceEntity = resourceDAO.saveAndFlush(resourceEntity);

            for (CMProResourceRomEntity rom : roms) {
                rom.setResource(resourceEntity);
                resourceRomDAO.saveAndFlush(rom);
            }
        }

        return entityToDatafile(entity);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public CMProDatafile findByUnique(String name, String category, String version) {
        CMProDatafileEntity entity = datafileDAO.findByUnique(name, category, version);
        if (entity != null) {
            return new CMProDatafile(new CMProHeader(entity.getName(), entity.getDescription(), entity.getCategory(),
                    entity.getVersion(), entity.getAuthor(), entity.getHomepage(), entity.getUrl(),
                    entity.getForcemerging(), entity.getForcezipping()));
        }
        return null;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public CMProDatafile findByUniqueFull(String name, String category, String version) {
        CMProDatafileEntity entity = datafileDAO.findByUniqueFull(name, category, version);
        if (entity != null) {
            return entityToDatafile(entity);
        }
        return null;
    }
}
