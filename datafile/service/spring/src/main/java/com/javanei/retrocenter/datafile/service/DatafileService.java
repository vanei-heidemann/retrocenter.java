package com.javanei.retrocenter.datafile.service;

import com.javanei.retrocenter.datafile.Datafile;
import com.javanei.retrocenter.datafile.Game;
import com.javanei.retrocenter.datafile.GameFile;
import com.javanei.retrocenter.datafile.Release;
import com.javanei.retrocenter.datafile.entity.DatafileEntity;
import com.javanei.retrocenter.datafile.entity.GameEntity;
import com.javanei.retrocenter.datafile.entity.GameFileEntity;
import com.javanei.retrocenter.datafile.entity.ReleaseEntity;
import com.javanei.retrocenter.datafile.persistence.DatafileDAO;
import com.javanei.retrocenter.datafile.persistence.GameDAO;
import com.javanei.retrocenter.datafile.persistence.GameFileDAO;
import com.javanei.retrocenter.datafile.persistence.ReleaseDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DatafileService {
    @Autowired
    private DatafileDAO datafileDAO;
    @Autowired
    private GameDAO gameDAO;
    @Autowired
    private GameFileDAO gameFileDAO;
    @Autowired
    private ReleaseDAO releaseDAO;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Datafile create(Datafile datafile) {
        DatafileEntity entity = new DatafileEntity(datafile.getName(), datafile.getCategory(), datafile.getVersion(),
                datafile.getDescription(), datafile.getAuthor(), datafile.getDate(), datafile.getEmail(),
                datafile.getHomepage(), datafile.getUrl(), datafile.getComment());
        entity = datafileDAO.saveAndFlush(entity);

        for (Game game : datafile.getGames()) {
            GameEntity gameEntity = new GameEntity(game.getName(), game.getIsbios(), game.getDescription(),
                    game.getYear(), game.getManufacturer(), game.getCloneof(), game.getRomof(), game.getSampleof(),
                    game.getComment());
            gameEntity.setDatafile(entity);
            gameEntity = gameDAO.saveAndFlush(gameEntity);

            for (GameFile gameFile : game.getFiles()) {
                GameFileEntity gameFileEntity = new GameFileEntity(gameFile.getType(), gameFile.getName(),
                        gameFile.getSize(), gameFile.getCrc(), gameFile.getSha1(), gameFile.getMd5(),
                        gameFile.getStatus(), gameFile.getDate(), gameFile.getMerge(), gameFile.getRegion());
                gameFileEntity.setGame(gameEntity);
                gameFileDAO.saveAndFlush(gameFileEntity);
            }

            for (Release release : game.getReleases()) {
                ReleaseEntity releaseEntity = new ReleaseEntity(release.getName(), release.getRegion(),
                        release.getLanguage(), release.getDate(), release.getDefault());
                releaseEntity.setGame(gameEntity);
                releaseDAO.saveAndFlush(releaseEntity);
            }
        }

        return datafile;
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
            Datafile datafile = new Datafile(entity.getName(), entity.getCategory(), entity.getVersion(),
                    entity.getDescription(), entity.getAuthor(), entity.getDate(), entity.getEmail(),
                    entity.getHomepage(), entity.getUrl(), entity.getComment());

            for (GameEntity gameEntity : entity.getGames()) {
                Game g = new Game(gameEntity.getName(), gameEntity.getIsbios(), gameEntity.getDescription(),
                        gameEntity.getYear(), gameEntity.getManufacturer(), gameEntity.getCloneof(),
                        gameEntity.getRomof(), gameEntity.getSampleof(), gameEntity.getComment());
                datafile.addGame(g);

                for (GameFileEntity gameFileEntity : gameEntity.getFiles()) {
                    GameFile gf = new GameFile(gameFileEntity.getType(), gameFileEntity.getName(),
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
        return null;
    }
}
