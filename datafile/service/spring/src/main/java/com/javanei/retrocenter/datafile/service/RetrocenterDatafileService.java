package com.javanei.retrocenter.datafile.service;

import com.javanei.retrocenter.datafile.Datafile;
import com.javanei.retrocenter.datafile.DatafileArtifact;
import com.javanei.retrocenter.datafile.DatafileArtifactFile;
import com.javanei.retrocenter.datafile.Release;
import com.javanei.retrocenter.datafile.entity.DatafileArtifactEntity;
import com.javanei.retrocenter.datafile.entity.DatafileArtifactFileEntity;
import com.javanei.retrocenter.datafile.entity.DatafileEntity;
import com.javanei.retrocenter.datafile.entity.DatafileReleaseEntity;
import com.javanei.retrocenter.datafile.persistence.DatafileArtifactDAO;
import com.javanei.retrocenter.datafile.persistence.DatafileArtifactFileDAO;
import com.javanei.retrocenter.datafile.persistence.DatafileDAO;
import com.javanei.retrocenter.datafile.persistence.DatafileReleaseDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RetrocenterDatafileService {
    private static final Logger LOG = LoggerFactory.getLogger(RetrocenterDatafileService.class);

    @Autowired
    private DatafileDAO datafileDAO;
    @Autowired
    private DatafileArtifactDAO artifactDAO;
    @Autowired
    private DatafileArtifactFileDAO artifactFileDAO;
    @Autowired
    private DatafileReleaseDAO releaseDAO;
    @Autowired
    private RetrocenterDatafileService retrocenterDatafileService;

    @Transactional(propagation = Propagation.REQUIRED)
    public DatafileEntity create(Datafile datafile) {
        LOG.info("create(" + datafile.getName() + ", " + datafile.getCatalog() + ", " + datafile.getVersion() + ")");
        DatafileEntity entity = new DatafileEntity(datafile.getName(), datafile.getCatalog(), datafile.getVersion(),
                datafile.getDescription(), datafile.getAuthor(), datafile.getDate(), datafile.getEmail(),
                datafile.getHomepage(), datafile.getUrl(), datafile.getComment());
        entity = retrocenterDatafileService.create(entity);

        int cont = 0;
        for (DatafileArtifact game : datafile.getArtifacts()) {
            DatafileArtifactEntity gameEntity = new DatafileArtifactEntity(game.getName(), game.getDescription(),
                    game.getYear(), game.getComment(), game.getFields());
            gameEntity.setDatafile(entity);
            for (DatafileArtifactFile gameFile : game.getFiles()) {
                DatafileArtifactFileEntity gameFileEntity = new DatafileArtifactFileEntity(gameFile.getType(), gameFile.getName(),
                        gameFile.getSize(), gameFile.getCrc(), gameFile.getSha1(), gameFile.getMd5(),
                        gameFile.getDate(), gameFile.getFields());
                gameEntity.getFiles().add(gameFileEntity);
            }
            for (Release release : game.getReleases()) {
                DatafileReleaseEntity releaseEntity = new DatafileReleaseEntity(release.getName(), release.getRegion(),
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

        return entity;
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
    public DatafileArtifactEntity createArtifact(DatafileArtifactEntity gameEntity) {
        LOG.debug("createArtifact(" + gameEntity.getName() + ")");
        DatafileArtifactEntity old = artifactDAO.findByDatafileAndName(gameEntity.getDatafile().getName(),
                gameEntity.getDatafile().getCatalog(), gameEntity.getDatafile().getVersion(), gameEntity.getName());
        if (old != null) {
            LOG.debug("Artifact already exist");
            gameEntity.setId(old.getId());
            return gameEntity;
        }
        gameEntity = artifactDAO.saveAndFlush(gameEntity);
        for (DatafileArtifactFileEntity gameFileEntity : gameEntity.getFiles()) {
            gameFileEntity.setArtifact(gameEntity);
            artifactFileDAO.saveAndFlush(gameFileEntity);
        }

        for (DatafileReleaseEntity releaseEntity : gameEntity.getReleases()) {
            releaseEntity.setArtifact(gameEntity);
            releaseDAO.saveAndFlush(releaseEntity);
        }

        return gameEntity;
    }
}
