package com.javanei.retrocenter.goodtools.service;

import com.javanei.retrocenter.common.PaginatedResult;
import com.javanei.retrocenter.goodtools.GoodDatafile;
import com.javanei.retrocenter.goodtools.GoodDatafileRom;
import com.javanei.retrocenter.goodtools.entity.GoodDatafileEntity;
import com.javanei.retrocenter.goodtools.entity.GoodDatafileRomEntity;
import com.javanei.retrocenter.goodtools.persistence.GoodDatafileDAO;
import com.javanei.retrocenter.goodtools.persistence.GoodDatafileRomDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Service
@Transactional
public class GoodToolsService {
    private static final Logger LOG = LoggerFactory.getLogger(GoodToolsService.class);

    @Autowired
    private GoodDatafileDAO datafileDAO;
    @Autowired
    private GoodDatafileRomDAO romDAO;
    @Autowired
    private GoodToolsService goodToolsService;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public GoodDatafileEntity create(GoodDatafileEntity entity) {
        LOG.debug("create(name=" + entity.getName() + ", version=" + entity.getVersion() + ")");
        GoodDatafileEntity old = datafileDAO.findByUnique(entity.getName(), entity.getVersion());
        if (old == null) {
            entity = datafileDAO.saveAndFlush(entity);
        } else {
            LOG.debug("Datafile already exist");
            entity.setId(old.getId());
        }
        return entity;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public GoodDatafileRomEntity createRom(GoodDatafileRomEntity entity) {
        LOG.debug("create(name=" + entity.getName() + ")");
        GoodDatafileRomEntity old = romDAO.findByDatafileAndName(entity.getDatafile().getName(),
                entity.getDatafile().getVersion(), entity.getName());
        if (old == null) {
            entity = romDAO.saveAndFlush(entity);
        } else {
            entity.setId(old.getId());
        }
        return entity;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public GoodDatafileDTO create(GoodDatafile datafile) {
        LOG.info("create(name=" + datafile.getName()
                + ", version=" + datafile.getVersion()
                + ", roms=" + datafile.getRoms().size()
                + ")");
        GoodDatafileEntity entity = new GoodDatafileEntity(datafile.getName(), datafile.getVersion(),
                datafile.getAuthor(), datafile.getDate(), datafile.getComment());
        entity = goodToolsService.create(entity);

        int cont = 0;
        for (GoodDatafileRom game : datafile.getRoms()) {
            GoodDatafileRomEntity gameEntity = new GoodDatafileRomEntity(game.getName(), game.getSize(), game.getCrc(),
                    game.getSha1(), game.getMd5());
            gameEntity.setDatafile(entity);

            goodToolsService.createRom(gameEntity);
            entity.getRoms().add(gameEntity);

            cont++;
            if (cont % 100 == 0) {
                LOG.info("Saved game " + cont + " of " + datafile.getRoms().size() + ": " + game.getName());
            }
        }

        return entityToVo(entity, true);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public GoodDatafileDTO findByUnique(String name, String catalog, String version) {
        GoodDatafileEntity entity = datafileDAO.findByUnique(name, version);
        if (entity != null) {
            return this.entityToVo(entity, true);
        }
        return null;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public GoodDatafileDTO findByUniqueFull(String name, String catalog, String version) {
        GoodDatafileEntity entity = datafileDAO.findByUnique(name, version);
        if (entity != null) {
            return this.entityToVo(entity, true);
        }
        return null;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public GoodDatafileDTO findByIdFull(Long id) {
        GoodDatafileEntity entity = datafileDAO.findOne(id);
        if (entity != null) {
            return this.entityToVo(entity, true);
        }
        return null;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<GoodDatafileDTO> findAll() {
        List<GoodDatafileEntity> l = datafileDAO.findAll();
        List<GoodDatafileDTO> result = new LinkedList<>();
        for (GoodDatafileEntity entity : l) {
            result.add(entityToVo(entity, false));
        }
        return result;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public PaginatedResult<GoodDatafileDTO> find(String name, int page, int pageSize) {
        PageRequest pageable = new PageRequest(page, pageSize, new Sort(Sort.Direction.ASC, "name"));
        Page<GoodDatafileEntity> p;
        if (name != null) {
            p = datafileDAO.findByNameLike("%" + name + "%", pageable);
        } else {
            p = datafileDAO.findAll(pageable);
        }
        PaginatedResult<GoodDatafileDTO> result = new PaginatedResult<>(p.hasNext());
        for (GoodDatafileEntity entity : p.getContent()) {
            result.add(entityToVo(entity, false));
        }
        return result;
    }

    private GoodDatafileDTO entityToVo(GoodDatafileEntity entity, boolean full) {
        GoodDatafileDTO datafile = new GoodDatafileDTO(entity.getName(), entity.getVersion(), entity.getAuthor(),
                entity.getDate(), entity.getComment(), entity.getId());

        if (full) {
            for (GoodDatafileRomEntity rom : entity.getRoms()) {
                datafile.addRom(new GoodDatafileRom(rom.getName(), rom.getSize(), rom.getCrc(), rom.getSha1(), rom.getMd5()));
            }
        }
        return datafile;
    }
}
