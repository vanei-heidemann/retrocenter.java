package com.javanei.retrocenter.datafile.service;

import com.javanei.retrocenter.datafile.Datafile;
import com.javanei.retrocenter.datafile.DatafileArtifact;
import com.javanei.retrocenter.datafile.DatafileArtifactFile;
import com.javanei.retrocenter.datafile.Release;
import com.javanei.retrocenter.datafile.entity.ArtifactEntity;
import com.javanei.retrocenter.datafile.entity.ArtifactFileEntity;
import com.javanei.retrocenter.datafile.entity.DatafileEntity;
import com.javanei.retrocenter.datafile.entity.ReleaseEntity;
import com.javanei.retrocenter.datafile.persistence.ArtifactDAO;
import com.javanei.retrocenter.datafile.persistence.ArtifactFileDAO;
import com.javanei.retrocenter.datafile.persistence.DatafileDAO;
import com.javanei.retrocenter.datafile.persistence.ReleaseDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class RetrocenterDatafileService {
    private static final Logger LOG = LoggerFactory.getLogger(RetrocenterDatafileService.class);

    @Autowired
    private DatafileDAO datafileDAO;
    @Autowired
    private ArtifactDAO artifactDAO;
    @Autowired
    private ArtifactFileDAO artifactFileDAO;
    @Autowired
    private ReleaseDAO releaseDAO;
    @Autowired
    private RetrocenterDatafileService retrocenterDatafileService;

    private static Datafile toVO(DatafileEntity entity) {
        Datafile datafile = new Datafile(entity.getName(), entity.getCatalog(), entity.getVersion(),
                entity.getDescription(), entity.getAuthor(), entity.getDate(), entity.getEmail(),
                entity.getHomepage(), entity.getUrl(), entity.getComment());

        for (ArtifactEntity gameEntity : entity.getArtifacts()) {
            DatafileArtifact g = new DatafileArtifact(gameEntity.getName(), gameEntity.getDescription(),
                    gameEntity.getYear(), gameEntity.getComment(), gameEntity.getFields());
            datafile.addArtifact(g);

            for (ArtifactFileEntity gameFileEntity : gameEntity.getFiles()) {
                DatafileArtifactFile gf = new DatafileArtifactFile(gameFileEntity.getType(), gameFileEntity.getName(),
                        gameFileEntity.getSize(), gameFileEntity.getCrc(), gameFileEntity.getSha1(),
                        gameFileEntity.getMd5(), gameFileEntity.getDate(), gameFileEntity.getFields());
                g.addFile(gf);
            }

            for (ReleaseEntity releaseEntity : gameEntity.getReleases()) {
                Release r = new Release(releaseEntity.getName(), releaseEntity.getRegion(),
                        releaseEntity.getLanguage(), releaseEntity.getDate(), releaseEntity.getDefault());
                g.addRelease(r);
            }
        }

        return datafile;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Datafile create(Datafile datafile) {
        LOG.info("create(" + datafile.getName() + ", " + datafile.getCatalog() + ", " + datafile.getVersion() + ")");
        DatafileEntity entity = new DatafileEntity(datafile.getName(), datafile.getCatalog(), datafile.getVersion(),
                datafile.getDescription(), datafile.getAuthor(), datafile.getDate(), datafile.getEmail(),
                datafile.getHomepage(), datafile.getUrl(), datafile.getComment());
        entity = retrocenterDatafileService.create(entity);

        int cont = 0;
        for (DatafileArtifact game : datafile.getArtifacts()) {
            ArtifactEntity gameEntity = new ArtifactEntity(game.getName(), game.getDescription(),
                    game.getYear(), game.getComment(), game.getFields());
            gameEntity.setDatafile(entity);
            for (DatafileArtifactFile gameFile : game.getFiles()) {
                ArtifactFileEntity gameFileEntity = new ArtifactFileEntity(gameFile.getType(), gameFile.getName(),
                        gameFile.getSize(), gameFile.getCrc(), gameFile.getSha1(), gameFile.getMd5(),
                        gameFile.getDate(), gameFile.getFields());
                gameEntity.getFiles().add(gameFileEntity);
            }
            for (Release release : game.getReleases()) {
                ReleaseEntity releaseEntity = new ReleaseEntity(release.getName(), release.getRegion(),
                        release.getLanguage(), release.getDate(), release.getDefault());
                gameEntity.getReleases().add(releaseEntity);
            }

            gameEntity = retrocenterDatafileService.createArtifact(gameEntity);
            entity.getArtifacts().add(gameEntity);

            cont++;
            if (cont % 100 == 0) {
                LOG.info("created artifact " + cont + " of " + datafile.getArtifacts().size() + ": " + game.getName());
            }
        }

        return toVO(entity);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public DatafileEntity create(DatafileEntity entity) {
        LOG.debug("create(name=" + entity.getName() + ", catalog=" + entity.getCatalog()
                + ", version=" + entity.getVersion() + ")");
        DatafileEntity old = datafileDAO.findByUnique(entity.getName(), entity.getCatalog(), entity.getVersion());
        if (old == null) {
            entity = datafileDAO.saveAndFlush(entity);
        } else {
            LOG.debug("Datafile already exist");
            entity.setId(old.getId());
        }
        return entity;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ArtifactEntity createArtifact(ArtifactEntity gameEntity) {
        LOG.debug("createArtifact(" + gameEntity.getName() + ")");
        ArtifactEntity old = artifactDAO.findByDatafileAndName(gameEntity.getDatafile().getName(),
                gameEntity.getDatafile().getCatalog(), gameEntity.getDatafile().getVersion(), gameEntity.getName());
        if (old != null) {
            LOG.debug("Artifact already exist");
            gameEntity.setId(old.getId());
            return gameEntity;
        }
        gameEntity = artifactDAO.saveAndFlush(gameEntity);
        for (ArtifactFileEntity gameFileEntity : gameEntity.getFiles()) {
            gameFileEntity.setArtifact(gameEntity);
            artifactFileDAO.saveAndFlush(gameFileEntity);
        }

        for (ReleaseEntity releaseEntity : gameEntity.getReleases()) {
            releaseEntity.setArtifact(gameEntity);
            releaseDAO.saveAndFlush(releaseEntity);
        }

        return gameEntity;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public Datafile findByUnique(String name, String catalog, String version) {
        DatafileEntity entity = datafileDAO.findByUnique(name, catalog, version);
        if (entity != null) {
            return new Datafile(entity.getName(), entity.getCatalog(), entity.getVersion(), entity.getDescription(),
                    entity.getAuthor(), entity.getDate(), entity.getEmail(), entity.getHomepage(), entity.getUrl(),
                    entity.getComment());
        }
        return null;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public Datafile findByUniqueFull(String name, String catalog, String version) {
        DatafileEntity entity = datafileDAO.findByUnique(name, catalog, version);
        if (entity != null) {
            return toVO(entity);
        }
        return null;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Datafile> findAll() {
        List<DatafileEntity> l = datafileDAO.findAll();
        List<Datafile> r = new ArrayList<>(l.size());
        for (DatafileEntity entity : l) {
            r.add(new Datafile(entity.getName(), entity.getCatalog(), entity.getVersion(), entity.getDescription(),
                    entity.getAuthor(), entity.getDate(), entity.getEmail(), entity.getHomepage(), entity.getUrl(),
                    entity.getComment()));
        }
        return r;
    }
}
