package com.javanei.retrocenter.gamedb.launchbox.service;

import com.javanei.retrocenter.common.Identified;
import com.javanei.retrocenter.gamedb.launchbox.common.LBoxCompany;
import com.javanei.retrocenter.gamedb.launchbox.common.LBoxDatafile;
import com.javanei.retrocenter.gamedb.launchbox.common.LBoxGame;
import com.javanei.retrocenter.gamedb.launchbox.common.LBoxGameImage;
import com.javanei.retrocenter.gamedb.launchbox.common.LBoxGenre;
import com.javanei.retrocenter.gamedb.launchbox.common.LBoxPlatform;
import com.javanei.retrocenter.gamedb.launchbox.common.LBoxRegion;
import com.javanei.retrocenter.gamedb.launchbox.entity.LBoxCompanyEntity;
import com.javanei.retrocenter.gamedb.launchbox.entity.LBoxDatafileEntity;
import com.javanei.retrocenter.gamedb.launchbox.entity.LBoxDatafileGameEntity;
import com.javanei.retrocenter.gamedb.launchbox.entity.LBoxDatafileGenreEntity;
import com.javanei.retrocenter.gamedb.launchbox.entity.LBoxDatafileRegionEntity;
import com.javanei.retrocenter.gamedb.launchbox.entity.LBoxGameEntity;
import com.javanei.retrocenter.gamedb.launchbox.entity.LBoxGameGenreEntity;
import com.javanei.retrocenter.gamedb.launchbox.entity.LBoxGameImageEntity;
import com.javanei.retrocenter.gamedb.launchbox.entity.LBoxGameRegionEntity;
import com.javanei.retrocenter.gamedb.launchbox.entity.LBoxGenreEntity;
import com.javanei.retrocenter.gamedb.launchbox.entity.LBoxPlatformEntity;
import com.javanei.retrocenter.gamedb.launchbox.entity.LBoxRegionEntity;
import com.javanei.retrocenter.gamedb.launchbox.persistence.LBoxCompanyDAO;
import com.javanei.retrocenter.gamedb.launchbox.persistence.LBoxDatafileDAO;
import com.javanei.retrocenter.gamedb.launchbox.persistence.LBoxGameDAO;
import com.javanei.retrocenter.gamedb.launchbox.persistence.LBoxGenreDAO;
import com.javanei.retrocenter.gamedb.launchbox.persistence.LBoxPlatformDAO;
import com.javanei.retrocenter.gamedb.launchbox.persistence.LBoxRegionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Service
@Transactional
public class LBoxService {
    private static final Logger LOG = LoggerFactory.getLogger(LBoxService.class);

    @Autowired
    private LBoxPlatformDAO platformDAO;
    @Autowired
    private LBoxCompanyDAO companyDAO;
    @Autowired
    private LBoxRegionDAO regionDAO;
    @Autowired
    private LBoxGenreDAO genreDAO;
    @Autowired
    private LBoxGameDAO gameDAO;
    @Autowired
    private LBoxDatafileDAO datafileDAO;
    @Autowired
    private LBoxService lboxService;

    @Transactional(propagation = Propagation.REQUIRED)
    public LBoxDatafile save(LBoxDatafile datafile) throws Exception {
        LOG.info("create(" + datafile.getVersion() + ")");

        for (LBoxGenre genre : datafile.getGenres()) {
            lboxService.saveGenre(genre);
        }

        for (LBoxRegion region : datafile.getRegions()) {
            lboxService.saveRegion(region);
        }

        for (LBoxPlatform platform : datafile.getPlatforms()) {
            lboxService.savePlatform(platform);
        }

        for (LBoxGame game : datafile.getGames()) {
            lboxService.saveGame(game);
        }
        return datafile;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public LBoxRegionEntity saveRegion(LBoxRegion region) {
        LBoxRegionEntity entity = regionDAO.findByName(region.getName());
        if (entity != null) {
            return entity;
        }
        entity = new LBoxRegionEntity(region.getName());
        return regionDAO.saveAndFlush(entity);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public LBoxGenreEntity saveGenre(LBoxGenre genre) {
        LBoxGenreEntity entity = genreDAO.findByName(genre.getName());
        if (entity != null) {
            return entity;
        }
        entity = new LBoxGenreEntity(genre.getName());
        return genreDAO.saveAndFlush(entity);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public LBoxCompanyEntity saveCompany(LBoxCompany company) {
        LBoxCompanyEntity entity = companyDAO.findByName(company.getName());
        if (entity != null) {
            return entity;
        }
        entity = new LBoxCompanyEntity(company.getName());
        return companyDAO.saveAndFlush(entity);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public LBoxPlatformEntity savePlatform(LBoxPlatform platform) {
        LBoxPlatformEntity entity = platformDAO.findByName(platform.getName());
        if (entity == null) {
            entity = new LBoxPlatformEntity();
        }
        entity.setName(platform.getName());
        entity.setReleaseDate(platform.getReleaseDate());
        entity.setAlternateNames(platform.getAlternateNames());
        if (platform.getManufacturer() != null) {
            entity.setManufacturer(saveCompany(platform.getManufacturer()));
        }
        if (platform.getDeveloper() != null) {
            entity.setDeveloper(saveCompany(platform.getDeveloper()));
        }
        return platformDAO.saveAndFlush(entity);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public LBoxGameEntity saveGame(LBoxGame game) {
        LBoxGameEntity entity = gameDAO.findOne(game.getDatabaseID());
        if (entity == null) {
            entity = new LBoxGameEntity(game.getDatabaseID());
        }
        entity.setFileNames(game.getFileNames());
        entity.setName(game.getName());
        entity.setReleaseDate(game.getReleaseDate());
        entity.setReleaseYear(game.getReleaseYear());
        entity.setPlatform(savePlatform(game.getPlatform()));
        if (game.getDeveloper() != null) {
            entity.setDeveloper(saveCompany(game.getDeveloper()));
        }
        if (game.getPublisher() != null) {
            entity.setPublisher(saveCompany(game.getPublisher()));
        }
        if (!game.getGenres().isEmpty()) {
            for (LBoxGenre genre : game.getGenres()) {
                entity.getGenres().add(new LBoxGameGenreEntity(entity, lboxService.saveGenre(genre)));
            }
        }
        if (!game.getRegions().isEmpty()) {
            for (LBoxRegion region : game.getRegions()) {
                entity.getRegions().add(new LBoxGameRegionEntity(entity, lboxService.saveRegion(region)));
            }
        }
        if (!game.getImages().isEmpty()) {
            for (LBoxGameImage image : game.getImages()) {
                LBoxGameImageEntity e = new LBoxGameImageEntity(entity, image.getFileName(), image.getType(),
                        image.getCrc32());
                if (image.getRegion() != null) {
                    e.setRegion(lboxService.saveRegion(new LBoxRegion(image.getRegion())));
                }
                entity.getImages().add(e);
            }
        }
        return gameDAO.saveAndFlush(entity);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<LBoxGame> findGamesByVersion(String version) {
        List<LBoxGame> result = new LinkedList<>();

        LBoxDatafileEntity entity = datafileDAO.findByVersion(version);
        if (entity != null) {
            for (LBoxDatafileGameEntity game : entity.getGames()) {
                result.add(entityToGameVO(game.getGame()));
            }
        }
        return result;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Identified<LBoxRegion>> findRegionsByVersion(String version) {
        List<Identified<LBoxRegion>> result = new LinkedList<>();
        LBoxDatafileEntity entity = datafileDAO.findByVersion(version);
        if (entity != null) {
            for (LBoxDatafileRegionEntity region : entity.getRegions()) {
                result.add(new Identified<>(region.getRegion().getId(), entityToRegionVO(region.getRegion())));
            }
        }
        return result;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Identified<LBoxGenre>> findGenresByVersion(String version) {
        List<Identified<LBoxGenre>> result = new LinkedList<>();
        LBoxDatafileEntity entity = datafileDAO.findByVersion(version);
        if (entity != null) {
            for (LBoxDatafileGenreEntity genre : entity.getGenres()) {
                result.add(new Identified<>(genre.getGenre().getId(), entityToGenreVO(genre.getGenre())));
            }
        }
        return result;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Identified<LBoxPlatform>> findPlatforms() {
        List<Identified<LBoxPlatform>> result = new LinkedList<>();
        List<LBoxPlatformEntity> l = platformDAO.findAll();
        for (LBoxPlatformEntity e : l) {
            result.add(new Identified<>(e.getId(), entityToPlatformVO(e)));
        }
        return result;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Identified<LBoxGenre>> findGenres() {
        List<Identified<LBoxGenre>> result = new LinkedList<>();
        List<LBoxGenreEntity> l = genreDAO.findAll();
        for (LBoxGenreEntity e : l) {
            result.add(new Identified<>(e.getId(), entityToGenreVO(e)));
        }
        return result;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Identified<LBoxRegion>> findRegions() {
        List<Identified<LBoxRegion>> result = new LinkedList<>();
        List<LBoxRegionEntity> l = regionDAO.findAll();
        for (LBoxRegionEntity e : l) {
            result.add(new Identified<>(e.getId(), entityToRegionVO(e)));
        }
        return result;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    private List<LBoxGame> findGames() {
        List<LBoxGame> result = new LinkedList<>();
        List<LBoxGameEntity> l = gameDAO.findAll();
        for (LBoxGameEntity e : l) {
            result.add(entityToGameVO(e));
        }
        return result;
    }

    private LBoxPlatform entityToPlatformVO(LBoxPlatformEntity entity) {
        if (entity != null) {
            LBoxPlatform p = new LBoxPlatform(entity.getName());
            p.setManufacturer(entityToCompanyVO(entity.getManufacturer()));
            p.setDeveloper(entityToCompanyVO(entity.getDeveloper()));
            p.setAlternateNames(entity.getAlternateNames());
            return p;
        }
        return null;
    }

    private LBoxCompany entityToCompanyVO(LBoxCompanyEntity entity) {
        if (entity != null) {
            return new LBoxCompany(entity.getName());
        }
        return null;
    }

    private LBoxGenre entityToGenreVO(LBoxGenreEntity entity) {
        if (entity != null) {
            return new LBoxGenre(entity.getName());
        }
        return null;
    }

    private LBoxRegion entityToRegionVO(LBoxRegionEntity entity) {
        if (entity != null) {
            return new LBoxRegion(entity.getName());
        }
        return null;
    }

    private LBoxGameImage entityToGameImageVO(LBoxGameImageEntity entity) {
        if (entity != null) {
            return new LBoxGameImage(entity.getFileName(), entity.getType(),
                    entity.getRegion() != null ? entity.getRegion().getName() : null,
                    entity.getCrc32());
        }
        return null;
    }

    private LBoxGame entityToGameVO(LBoxGameEntity ge) {
        LBoxGame g = new LBoxGame(ge.getDatabaseID(), ge.getName());
        g.setReleaseYear(ge.getReleaseYear());
        g.setReleaseDate(ge.getReleaseDate());
        g.setPublisher(entityToCompanyVO(ge.getPublisher()));
        g.setDeveloper(entityToCompanyVO(ge.getDeveloper()));
        g.setPlatform(entityToPlatformVO(ge.getPlatform()));
        g.setFileNames(ge.getFileNames());
        for (LBoxGameImageEntity gi : ge.getImages()) {
            g.addImage(entityToGameImageVO(gi));
        }
        for (LBoxGameRegionEntity re : ge.getRegions()) {
            g.addRegion(entityToRegionVO(re.getRegion()));
        }
        for (LBoxGameGenreEntity genre : ge.getGenres()) {
            g.addGenre(entityToGenreVO(genre.getGenre()));
        }
        return g;
    }
}
