package com.javanei.retrocenter.gamedb.launchbox.service;

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
import com.javanei.retrocenter.gamedb.launchbox.entity.LBoxDatafilePlatformEntity;
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
import com.javanei.retrocenter.gamedb.launchbox.persistence.LBoxDatafileGameDAO;
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

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
    private LBoxDatafileGameDAO datafileGameDAO;
    @Autowired
    private LBoxService lboxService;

    @Transactional(propagation = Propagation.REQUIRED)
    public List<LBoxDatafileDTO> findDatafiles() {
        List<LBoxDatafileEntity> l = datafileDAO.findAll();
        List<LBoxDatafileDTO> result = new LinkedList<>();
        for (LBoxDatafileEntity e : l) {
            result.add(new LBoxDatafileDTO(e.getId(), e.getVersion()));
        }
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public LBoxDatafileDTO save(LBoxDatafile datafile) throws Exception {
        LOG.info("save(" + datafile.getVersion() + ")");

        LBoxDatafileEntity entity = new LBoxDatafileEntity(datafile.getVersion());
        LBoxDatafileEntity exist = datafileDAO.findByVersion(datafile.getVersion());
        Map<Long, LBoxDatafilePlatformEntity> platforms = new HashMap<>();
        Map<Long, LBoxDatafileGenreEntity> genres = new HashMap<>();
        Map<Long, LBoxDatafileRegionEntity> regions = new HashMap<>();
        Map<String, LBoxDatafileGameEntity> games = new HashMap<>();
        Map<String, LBoxDatafileGameEntity> newGames = new HashMap<>();
        if (exist != null) {
            entity.setId(exist.getId());
            for (LBoxDatafileRegionEntity e : exist.getRegions()) {
                regions.put(e.getRegion().getId(), e);
            }
            for (LBoxDatafileGenreEntity e : exist.getGenres()) {
                genres.put(e.getGenre().getId(), e);
            }
            for (LBoxDatafilePlatformEntity e : exist.getPlatforms()) {
                platforms.put(e.getPlatform().getId(), e);
            }
            for (LBoxDatafileGameEntity e : exist.getGames()) {
                games.put(e.getGame().getDatabaseID(), e);
            }
        }

        LOG.info("save(" + datafile.getVersion() + ") - Saving genres (" + datafile.getGenres().size() + ")");
        for (LBoxGenre genre : datafile.getGenres()) {
            LBoxGenreEntity ge = lboxService.saveGenre(genre);
            LBoxDatafileGenreEntity dge = genres.get(ge.getId());
            if (dge == null) {
                dge = new LBoxDatafileGenreEntity(entity, ge);
            }
            entity.getGenres().add(dge);
        }

        LOG.info("save(" + datafile.getVersion() + ") - Saving regions (" + datafile.getRegions().size() + ")");
        for (LBoxRegion region : datafile.getRegions()) {
            LBoxRegionEntity re = lboxService.saveRegion(region);
            LBoxDatafileRegionEntity dre = regions.get(re.getId());
            if (dre == null) {
                dre = new LBoxDatafileRegionEntity(entity, re);
            }
            entity.getRegions().add(dre);
        }

        LOG.info("save(" + datafile.getVersion() + ") - Saving platforms (" + datafile.getPlatforms().size() + ")");
        for (LBoxPlatform platform : datafile.getPlatforms()) {
            LBoxPlatformEntity pe = lboxService.savePlatform(platform);
            LBoxDatafilePlatformEntity dpe = platforms.get(pe.getId());
            if (dpe == null) {
                dpe = new LBoxDatafilePlatformEntity(entity, pe);
            }
            entity.getPlatforms().add(dpe);
        }

        LOG.info("save(" + datafile.getVersion() + ") - saving games (" + datafile.getGames().size() + ")");
        for (LBoxGame game : datafile.getGames()) {
            LBoxGameEntity ge = lboxService.saveGame(game);
            LBoxDatafileGameEntity dge = games.get(ge.getDatabaseID());
            if (dge == null) {
                dge = new LBoxDatafileGameEntity(entity, ge);
            }
            entity.getGames().add(dge);
            if (entity.getGames().size() % 100 == 0) {
                LOG.info("save(" + datafile.getVersion() + ") - saved games: "
                        + entity.getGames().size() + " of " + datafile.getGames().size()
                        + " : " + ge.getName() + " (" + ge.getDatabaseID() + ")");
            }
            newGames.put(ge.getDatabaseID(), dge);
        }

        LOG.info("save(" + datafile.getVersion() + ") - Saving datafile");
        entity = lboxService.saveDatafile(entity);
        LOG.info("save(" + datafile.getVersion() + ") - Saving datafile game");
        int count = 0;
        for (LBoxDatafileGameEntity e : newGames.values()) {
            e = lboxService.createDatafileGame(e);
            count++;
            if (count % 1000 == 0) {
                LOG.info("save(" + datafile.getVersion() + ") - saved datafile games: "
                        + count + " of " + newGames.size()
                        + " : " + e.getGame().getName() + " (" + e.getGame().getDatabaseID() + ")");
            }
        }
        LOG.info("save(" + datafile.getVersion() + ") - Deleting games (" + games.size() + ")");
        for (LBoxDatafileGameEntity e : games.values()) {
            if (newGames.get(e.getGame().getDatabaseID()) == null) {
                datafileGameDAO.delete(e.getId());
            }
        }
        LOG.info("save(" + datafile.getVersion() + ")=" + entity.getId());

        return new LBoxDatafileDTO(entity.getId(), entity.getVersion());
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public LBoxDatafileEntity saveDatafile(LBoxDatafileEntity entity) {
        entity = datafileDAO.saveAndFlush(entity);
        return entity;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public LBoxDatafileGameEntity createDatafileGame(LBoxDatafileGameEntity entity) {
        if (entity.getId() == null) {
            entity.setDatafile(datafileDAO.findOne(entity.getDatafile().getId()));
            entity.setGame(gameDAO.findOne(entity.getGame().getDatabaseID()));
            entity = datafileGameDAO.saveAndFlush(entity);
        }
        return entity;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public LBoxRegionEntity saveRegion(LBoxRegion region) {
        LOG.debug("saveRegion(region.name=" + region.getName() + ")");
        LBoxRegionEntity entity = regionDAO.findByName(region.getName());
        if (entity != null) {
            return entity;
        }
        LOG.info("saveRegion - new - (region.name=" + region.getName() + ")");
        entity = new LBoxRegionEntity(region.getName());
        return regionDAO.saveAndFlush(entity);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public LBoxGenreEntity saveGenre(LBoxGenre genre) {
        LOG.debug("saveGenre(genre.name=" + genre.getName() + ")");
        LBoxGenreEntity entity = genreDAO.findByName(genre.getName());
        if (entity != null) {
            return entity;
        }
        LOG.info("saveGenre - new - (genre.name=" + genre.getName() + ")");
        entity = new LBoxGenreEntity(genre.getName());
        return genreDAO.saveAndFlush(entity);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public LBoxCompanyEntity saveCompany(LBoxCompany company) {
        LOG.debug("saveCompany(company.name=" + company.getName() + ")");
        LBoxCompanyEntity entity = companyDAO.findByName(company.getName());
        if (entity != null) {
            return entity;
        }
        LOG.info("saveCompany - new - (company.name=" + company.getName() + ")");
        entity = new LBoxCompanyEntity(company.getName());
        return companyDAO.saveAndFlush(entity);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public LBoxPlatformEntity savePlatform(LBoxPlatform platform) {
        LOG.debug("savePlatform(platform.name=" + platform.getName() + ")");
        LBoxPlatformEntity entity = platformDAO.findByName(platform.getName());
        if (entity == null) {
            LOG.info("savePlatform - new - (platform.name=" + platform.getName() + ")");
            entity = new LBoxPlatformEntity();
        }
        entity.setName(platform.getName());
        entity.setReleaseDate(platform.getReleaseDate());
        entity.setAlternateNames(platform.getAlternateNames());
        if (platform.getManufacturer() != null) {
            entity.setManufacturer(lboxService.saveCompany(platform.getManufacturer()));
        }
        if (platform.getDeveloper() != null) {
            entity.setDeveloper(lboxService.saveCompany(platform.getDeveloper()));
        }
        return platformDAO.saveAndFlush(entity);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public LBoxGameEntity saveGame(LBoxGame game) {
        LOG.debug("saveGame(game.databaseID=" + game.getDatabaseID() + ", game.name=" + game.getName() + ")");
        Map<Long, LBoxGameGenreEntity> genres = new HashMap<>();
        Map<Long, LBoxGameRegionEntity> regions = new HashMap<>();
        Map<Integer, LBoxGameImageEntity> images = new HashMap<>();

        LBoxGameEntity entity = new LBoxGameEntity(game.getDatabaseID());
        LBoxGameEntity exist = gameDAO.findOne(game.getDatabaseID());
        if (exist != null) {
            for (LBoxGameGenreEntity e : exist.getGenres()) {
                genres.put(e.getGenre().getId(), e);
            }
            for (LBoxGameRegionEntity e : exist.getRegions()) {
                regions.put(e.getRegion().getId(), e);
            }
            for (LBoxGameImageEntity e : exist.getImages()) {
                images.put(e.hashCode(), e);
            }
        } else {
            LOG.info("saveGame - new - (game.databaseID=" + game.getDatabaseID() + ", game.name=" + game.getName() + ")");
        }

        entity.setFileNames(game.getFileNames());
        entity.setName(game.getName());
        entity.setReleaseDate(game.getReleaseDate());
        entity.setReleaseYear(game.getReleaseYear());
        entity.setPlatform(lboxService.savePlatform(game.getPlatform()));
        if (game.getDeveloper() != null) {
            entity.setDeveloper(lboxService.saveCompany(game.getDeveloper()));
        }
        if (game.getPublisher() != null) {
            entity.setPublisher(lboxService.saveCompany(game.getPublisher()));
        }
        if (!game.getGenres().isEmpty()) {
            for (LBoxGenre genre : game.getGenres()) {
                LBoxGenreEntity e = lboxService.saveGenre(genre);
                LBoxGameGenreEntity ee = genres.get(e.getId());
                if (ee == null) {
                    ee = new LBoxGameGenreEntity(entity, e);
                }
                entity.getGenres().add(ee);
            }
        }
        if (!game.getRegions().isEmpty()) {
            for (LBoxRegion region : game.getRegions()) {
                LBoxRegionEntity e = lboxService.saveRegion(region);
                LBoxGameRegionEntity ee = regions.get(e.getId());
                if (ee == null) {
                    ee = new LBoxGameRegionEntity(entity, e);
                }
                entity.getRegions().add(ee);
            }
        }
        if (!game.getImages().isEmpty()) {
            for (LBoxGameImage image : game.getImages()) {
                LBoxGameImageEntity e = new LBoxGameImageEntity(entity, image.getFileName(), image.getType(),
                        image.getCrc32());
                if (image.getRegion() != null) {
                    e.setRegion(lboxService.saveRegion(new LBoxRegion(image.getRegion())));
                }
                LBoxGameImageEntity ee = images.get(e.hashCode());
                entity.getImages().add(ee != null ? ee : e);
            }
        }
        entity = gameDAO.saveAndFlush(entity);
        return entity;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<LBoxGame> findGames(Map<String, Object> params) {
        LOG.info("findGames(" + params + ")");
        List<LBoxGame> result = new LinkedList<>();
        List<LBoxGameEntity> l;
        if (params.get("name") != null) {
            l = gameDAO.findByNameLike("%" + params.get("name") + "%");
        } else {
            l = gameDAO.findAll();
        }
        for (LBoxGameEntity e : l) {
            result.add(entityToGameVO(e));
        }
        LOG.info("findGames(): " + result.size());
        return result;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<LBoxGame> findGamesByVersion(String version, Map<String, Object> params) {
        LOG.info("findGamesByVersion(version=" + version + ", params=" + params + ")");
        List<LBoxGame> result = new LinkedList<>();

        List<LBoxDatafileGameEntity> l;
        if (params.get("name") != null) {
            l = datafileGameDAO.findByDatafile_VersionAndGame_NameLike(version, "%" + params.get("name") + "%");
        } else {
            l = datafileGameDAO.findByDatafile_Version(version);
        }
        for (LBoxDatafileGameEntity game : l) {
            result.add(entityToGameVO(game.getGame()));
        }
        LOG.info("findGamesByVersion(version=" + version + "): " + result.size());
        return result;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<LBoxRegionDTO> findRegionsByVersion(String version) {
        LOG.info("findRegionsByVersion(version=" + version + ")");
        List<LBoxRegionDTO> result = new LinkedList<>();
        LBoxDatafileEntity entity = datafileDAO.findByVersion(version);
        if (entity != null) {
            for (LBoxDatafileRegionEntity region : entity.getRegions()) {
                result.add(entityToRegionDTO(region.getRegion()));
            }
        }
        LOG.info("findRegionsByVersion(version=" + version + "): " + result.size());
        return result;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<LBoxGenreDTO> findGenresByVersion(String version) {
        LOG.info("findGenresByVersion(version=" + version + ")");
        List<LBoxGenreDTO> result = new LinkedList<>();
        LBoxDatafileEntity entity = datafileDAO.findByVersion(version);
        if (entity != null) {
            for (LBoxDatafileGenreEntity genre : entity.getGenres()) {
                result.add(entityToGenreDTO(genre.getGenre()));
            }
        }
        LOG.info("findGenresByVersion(version=" + version + "): " + result.size());
        return result;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<LBoxPlatformDTO> findPlatformsByVersion(String version) {
        LOG.info("findPlatforms(findPlatformsByVersion(version=" + version + ")");
        List<LBoxPlatformDTO> result = new LinkedList<>();
        LBoxDatafileEntity entity = datafileDAO.findByVersion(version);
        if (entity != null) {
            for (LBoxDatafilePlatformEntity p : entity.getPlatforms()) {
                result.add(entityToPlatformDTO(p.getPlatform()));
            }
        }
        LOG.info("findPlatforms(findPlatformsByVersion(version=" + version + "): " + result.size());
        return result;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<LBoxPlatformDTO> findPlatforms() {
        LOG.info("findPlatforms()");
        List<LBoxPlatformDTO> result = new LinkedList<>();
        List<LBoxPlatformEntity> l = platformDAO.findAll();
        for (LBoxPlatformEntity e : l) {
            result.add(entityToPlatformDTO(e));
        }
        LOG.info("findPlatforms(): " + result.size());
        return result;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<LBoxGenreDTO> findGenres() {
        LOG.info("findGenres()");
        List<LBoxGenreDTO> result = new LinkedList<>();
        List<LBoxGenreEntity> l = genreDAO.findAll();
        for (LBoxGenreEntity e : l) {
            result.add(entityToGenreDTO(e));
        }
        LOG.info("findGenres(): " + result.size());
        return result;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<LBoxRegionDTO> findRegions() {
        LOG.info("findRegions()");
        List<LBoxRegionDTO> result = new LinkedList<>();
        List<LBoxRegionEntity> l = regionDAO.findAll();
        for (LBoxRegionEntity e : l) {
            result.add(entityToRegionDTO(e));
        }
        LOG.info("findRegions(): " + result.size());
        return result;
    }

    private LBoxPlatformDTO entityToPlatformDTO(LBoxPlatformEntity entity) {
        if (entity != null) {
            LBoxPlatformDTO p = new LBoxPlatformDTO(entity.getName(), entity.getId());
            p.setManufacturer(entityToCompanyDTO(entity.getManufacturer()));
            p.setDeveloper(entityToCompanyDTO(entity.getDeveloper()));
            p.setAlternateNames(entity.getAlternateNames());
            return p;
        }
        return null;
    }

    private LBoxCompanyDTO entityToCompanyDTO(LBoxCompanyEntity entity) {
        if (entity != null) {
            return new LBoxCompanyDTO(entity.getName(), entity.getId());
        }
        return null;
    }

    private LBoxGenreDTO entityToGenreDTO(LBoxGenreEntity entity) {
        if (entity != null) {
            return new LBoxGenreDTO(entity.getName(), entity.getId());
        }
        return null;
    }

    private LBoxRegionDTO entityToRegionDTO(LBoxRegionEntity entity) {
        if (entity != null) {
            return new LBoxRegionDTO(entity.getName(), entity.getId());
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
        g.setPublisher(entityToCompanyDTO(ge.getPublisher()));
        g.setDeveloper(entityToCompanyDTO(ge.getDeveloper()));
        g.setPlatform(new LBoxPlatformDTO(ge.getPlatform().getName(), ge.getPlatform().getId()));
        //g.setPlatform(entityToPlatformDTO(ge.getPlatform()));
        g.setFileNames(ge.getFileNames());
        for (LBoxGameImageEntity gi : ge.getImages()) {
            g.addImage(entityToGameImageVO(gi));
        }
        for (LBoxGameRegionEntity re : ge.getRegions()) {
            g.addRegion(entityToRegionDTO(re.getRegion()));
        }
        for (LBoxGameGenreEntity genre : ge.getGenres()) {
            g.addGenre(entityToGenreDTO(genre.getGenre()));
        }
        return g;
    }
}
