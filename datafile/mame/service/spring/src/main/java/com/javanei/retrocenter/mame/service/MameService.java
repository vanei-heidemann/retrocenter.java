package com.javanei.retrocenter.mame.service;

import com.javanei.retrocenter.mame.Mame;
import com.javanei.retrocenter.mame.MameMachine;
import com.javanei.retrocenter.mame.entity.MameEntity;
import com.javanei.retrocenter.mame.entity.MameMachineEntity;
import com.javanei.retrocenter.mame.persistence.MameDAO;
import com.javanei.retrocenter.mame.persistence.MameMachineDAO;
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
public class MameService {
    private static final Logger LOG = LoggerFactory.getLogger(MameService.class);

    @Autowired
    private MameDAO mameDAO;
    @Autowired
    private MameMachineDAO machineDAO;
    @Autowired
    private MameService mameService;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Mame create(String build, String debug, String mameconfig) {
        LOG.info("create(build=" + build + ", debug=" + debug + ", mameconfig=" + mameconfig + ")");
        MameEntity entity = new MameEntity();
        entity.setBuild(build);
        entity.setDebug(debug);
        entity.setMameconfig(mameconfig);
        entity = mameDAO.save(entity);
        return entity.toVO();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Mame create(Mame mame) {
        LOG.info("create - mame(build=" + mame.getBuild() + ", debug=" + mame.getDebug() + ", mameconfig=" + mame.getMameconfig() + ")");
        MameEntity entity = mameService.create(new MameEntity(mame));
        int cont = 0;
        for (MameMachine machine : mame.getMachines()) {
            MameMachineEntity machineEntity = new MameMachineEntity(machine);
            machineEntity.setMame(entity);
            machineEntity = mameService.createMachine(machineEntity);
            entity.getMachines().add(machineEntity);
            cont++;
            if (cont % 100 == 0) {
                LOG.info("Save machine " + cont + " of " + mame.getMachines().size() + ": " + machine.getName());
            }
        }

        return entity.toVO();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public MameEntity create(MameEntity entity) {
        MameEntity oldMame = mameDAO.findByBuild(entity.getBuild());
        if (oldMame == null) {
            entity = mameDAO.saveAndFlush(entity);
        } else {
            entity.setId(oldMame.getId());
        }
        return entity;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public MameMachineEntity createMachine(MameMachineEntity machineEntity) {
        MameMachineEntity old = machineDAO.findByBuildAndName(machineEntity.getMame().getBuild(),
                machineEntity.getName());
        if (old == null) {
            machineEntity = machineDAO.saveAndFlush(machineEntity);
        } else {
            machineEntity.setId(old.getId());
        }

        return machineEntity;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public MameDTO findByBuild(String build) {
        LOG.info("findByBuild(" + build + ")");
        MameEntity entity = mameDAO.findByBuild(build);
        return entity != null ? entityToVO(entity, false) : null;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public MameDTO findByBuildFull(String build) {
        LOG.info("findByBuildFull(" + build + ")");
        MameEntity entity = mameDAO.findByBuildFull(build);
        return entity != null ? entityToVO(entity, true) : null;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public MameDTO findByIdFull(Long id) {
        LOG.info("findByIdFull(" + id + ")");
        MameEntity entity = mameDAO.findOne(id);
        return entity != null ? entityToVO(entity, true) : null;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<MameDTO> findAll() {
        List<MameEntity> l = mameDAO.findAll();
        List<MameDTO> result = new LinkedList<>();
        for (MameEntity entity : l) {
            result.add(entityToVO(entity, false));
        }
        return result;
    }

    private MameDTO entityToVO(MameEntity entity, boolean full) {
        MameDTO mame = new MameDTO(entity.getBuild(), entity.getDebug(), entity.getMameconfig(), entity.getId());
        if (full) {
            int count = 0;
            for (MameMachineEntity machineEntity : entity.getMachines()) {
                MameMachine machine = machineEntity.toVO();
                mame.addMachine(machine);
                count++;
                System.out.println("--> " + count + " = " + machine.getName());
            }
        }
        return mame;
    }
}
