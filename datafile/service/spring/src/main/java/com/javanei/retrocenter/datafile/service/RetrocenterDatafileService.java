package com.javanei.retrocenter.datafile.service;

import com.javanei.retrocenter.datafile.Artifact;
import com.javanei.retrocenter.datafile.ArtifactFile;
import com.javanei.retrocenter.datafile.Datafile;
import com.javanei.retrocenter.datafile.Release;
import com.javanei.retrocenter.datafile.entity.ArtifactEntity;
import com.javanei.retrocenter.datafile.entity.ArtifactFileEntity;
import com.javanei.retrocenter.datafile.entity.DatafileEntity;
import com.javanei.retrocenter.datafile.entity.ReleaseEntity;
import com.javanei.retrocenter.datafile.persistence.ArtifactDAO;
import com.javanei.retrocenter.datafile.persistence.ArtifactFileDAO;
import com.javanei.retrocenter.datafile.persistence.DatafileDAO;
import com.javanei.retrocenter.datafile.persistence.ReleaseDAO;
import java.util.ArrayList;
import java.util.List;
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
    private ArtifactDAO artifactDAO;
    @Autowired
    private ArtifactFileDAO artifactFileDAO;
    @Autowired
    private ReleaseDAO releaseDAO;

    private static Datafile toVO(DatafileEntity entity) {
        Datafile datafile = new Datafile(entity.getName(), entity.getCategory(), entity.getVersion(),
                entity.getDescription(), entity.getAuthor(), entity.getDate(), entity.getEmail(),
                entity.getHomepage(), entity.getUrl(), entity.getComment());

        for (ArtifactEntity gameEntity : entity.getArtifacts()) {
            Artifact g = new Artifact(gameEntity.getName(), gameEntity.getIsbios(), gameEntity.getDescription(),
                    gameEntity.getYear(), gameEntity.getManufacturer(), gameEntity.getCloneof(),
                    gameEntity.getRomof(), gameEntity.getSampleof(), gameEntity.getComment());
            datafile.addArtifact(g);

            for (ArtifactFileEntity gameFileEntity : gameEntity.getFiles()) {
                ArtifactFile gf = new ArtifactFile(gameFileEntity.getType(), gameFileEntity.getName(),
                        gameFileEntity.getSize(), gameFileEntity.getCrc(), gameFileEntity.getSha1(),
                        gameFileEntity.getMd5(), gameFileEntity.getStatus(), gameFileEntity.getDate(),
                        gameFileEntity.getMerge(), gameFileEntity.getRegion());
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

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Datafile create(Datafile datafile) {
        DatafileEntity entity = new DatafileEntity(datafile.getName(), datafile.getCategory(), datafile.getVersion(),
                datafile.getDescription(), datafile.getAuthor(), datafile.getDate(), datafile.getEmail(),
                datafile.getHomepage(), datafile.getUrl(), datafile.getComment());
        entity = datafileDAO.saveAndFlush(entity);

        for (Artifact game : datafile.getArtifacts()) {
            ArtifactEntity gameEntity = new ArtifactEntity(game.getName(), game.getIsbios(), game.getDescription(),
                    game.getYear(), game.getManufacturer(), game.getCloneof(), game.getRomof(), game.getSampleof(),
                    game.getComment());
            gameEntity.setDatafile(entity);
            gameEntity = artifactDAO.saveAndFlush(gameEntity);
            entity.getArtifacts().add(gameEntity);

            for (ArtifactFile gameFile : game.getFiles()) {
                ArtifactFileEntity gameFileEntity = new ArtifactFileEntity(gameFile.getType(), gameFile.getName(),
                        gameFile.getSize(), gameFile.getCrc(), gameFile.getSha1(), gameFile.getMd5(),
                        gameFile.getStatus(), gameFile.getDate(), gameFile.getMerge(), gameFile.getRegion());
                gameFileEntity.setArtifact(gameEntity);
                artifactFileDAO.saveAndFlush(gameFileEntity);
                gameEntity.getFiles().add(gameFileEntity);
            }

            for (Release release : game.getReleases()) {
                ReleaseEntity releaseEntity = new ReleaseEntity(release.getName(), release.getRegion(),
                        release.getLanguage(), release.getDate(), release.getDefault());
                releaseEntity.setArtifact(gameEntity);
                releaseDAO.saveAndFlush(releaseEntity);
                gameEntity.getReleases().add(releaseEntity);
            }
        }

        return toVO(entity);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public Datafile findByUnique(String name, String category, String version) {
        DatafileEntity entity = datafileDAO.findByUnique(name, category, version);
        if (entity != null) {
            return new Datafile(entity.getName(), entity.getCategory(), entity.getVersion(), entity.getDescription(),
                    entity.getAuthor(), entity.getDate(), entity.getEmail(), entity.getHomepage(), entity.getUrl(),
                    entity.getComment());
        }
        return null;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public Datafile findByUniqueFull(String name, String category, String version) {
        DatafileEntity entity = datafileDAO.findByUnique(name, category, version);
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
            r.add(new Datafile(entity.getName(), entity.getCategory(), entity.getVersion(), entity.getDescription(),
                    entity.getAuthor(), entity.getDate(), entity.getEmail(), entity.getHomepage(), entity.getUrl(),
                    entity.getComment()));
        }
        return r;
    }
}
