package com.javanei.retrocenter.mame.service;

import com.javanei.retrocenter.mame.Mame;
import com.javanei.retrocenter.mame.MameMachine;
import com.javanei.retrocenter.mame.entity.MameAdjusterEntity;
import com.javanei.retrocenter.mame.entity.MameBiossetEntity;
import com.javanei.retrocenter.mame.entity.MameChipEntity;
import com.javanei.retrocenter.mame.entity.MameConfigurationEntity;
import com.javanei.retrocenter.mame.entity.MameDeviceEntity;
import com.javanei.retrocenter.mame.entity.MameDevicerefEntity;
import com.javanei.retrocenter.mame.entity.MameDipswitchEntity;
import com.javanei.retrocenter.mame.entity.MameDiskEntity;
import com.javanei.retrocenter.mame.entity.MameDisplayEntity;
import com.javanei.retrocenter.mame.entity.MameEntity;
import com.javanei.retrocenter.mame.entity.MameMachineEntity;
import com.javanei.retrocenter.mame.entity.MamePortEntity;
import com.javanei.retrocenter.mame.entity.MameRamoptionEntity;
import com.javanei.retrocenter.mame.entity.MameRomEntity;
import com.javanei.retrocenter.mame.entity.MameSampleEntity;
import com.javanei.retrocenter.mame.entity.MameSlotEntity;
import com.javanei.retrocenter.mame.entity.MameSoftwarelistEntity;
import com.javanei.retrocenter.mame.persistence.MameDAO;
import com.javanei.retrocenter.mame.persistence.MameMachineDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
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
        return entityToVO(entity, false, false);
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

        return mame;
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
        return entity != null ? entityToVO(entity, false, false) : null;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public MameDTO findByBuildFull(String build) {
        LOG.info("findByBuildFull(" + build + ")");
        MameEntity entity = mameDAO.findByBuildFull(build);
        return entity != null ? entityToVO(entity, true, true) : null;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public MameDTO findByIdFull(Long id, boolean fullMachines) {
        LOG.info("findByIdFull(" + id + ", " + fullMachines + ")");
        MameEntity entity = mameDAO.findOne(id);
        if (entity != null) {
            LOG.info("findByIdFull(" + id + ", " + fullMachines + "): " + entity.getBuild());
            return entityToVO(entity, true, fullMachines);
        }
        return null;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public MameDTO findById(Long id) {
        LOG.info("findById(" + id + ")");
        MameEntity entity = mameDAO.findOne(id);
        return entity != null ? entityToVO(entity, false, false) : null;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<MameDTO> findAll() {
        LOG.info("findAll()");
        List<MameEntity> l = mameDAO.findAll();
        List<MameDTO> result = new LinkedList<>();
        for (MameEntity entity : l) {
            result.add(entityToVO(entity, false, false));
        }
        return result;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<MameMachine> findMachinesByMameId(Long mameId, int page, int pageSize, boolean full) {
        LOG.info("findMachinesByMameId(" + mameId + ", " + page + ", " + pageSize + ", " + full + ")");
        MameEntity mame = mameDAO.findOne(mameId);
        if (mame != null) {
            PageRequest paging = new PageRequest(page, pageSize, new Sort(Sort.Direction.ASC, "id"));
            Page<MameMachineEntity> l = machineDAO.findByMame_Id(mameId, paging);
            List<MameMachine> result = new LinkedList<>();
            for (MameMachineEntity entity : l.getContent()) {
                result.add(entityToVO(entity, full));
            }
            return result;
        }
        return Collections.emptyList();
    }

    private MameDTO entityToVO(MameEntity entity, boolean full, boolean fullMachines) {
        MameDTO mame = new MameDTO(entity.getBuild(), entity.getDebug(), entity.getMameconfig(), entity.getId());
        if (full) {
            int count = 0;
            for (MameMachineEntity me : entity.getMachines()) {
                mame.addMachine(entityToVO(me, fullMachines));
                count++;
                if (count % 1000 == 0) {
                    LOG.info("Readed machines: " + count + (" : " + me.getId() + " : " + me.getName()));
                }
            }
        }
        return mame;
    }

    private MameMachine entityToVO(MameMachineEntity entity, boolean full) {
        if (entity != null) {
            MameMachine machine = new MameMachine(entity.getName(), entity.getSourcefile(), entity.getIsbios(),
                    entity.getIsdevice(), entity.getIsmechanical(), entity.getRunnable(), entity.getCloneof(),
                    entity.getRomof(), entity.getSampleof(), entity.getDescription(), entity.getYear(),
                    entity.getManufacturer());
            if (full) {
                if (entity.getSound() != null) {
                    machine.setSound(entity.getSound().toVO());
                }
                if (entity.getInput() != null) {
                    machine.setInput(entity.getInput().toVO());
                }
                if (entity.getDriver() != null) {
                    machine.setDriver(entity.getDriver().toVO());
                }
                for (MameBiossetEntity biossetEntity : entity.getBiossets()) {
                    machine.addBiosset(biossetEntity.toVO());
                }
                for (MameRomEntity romEntity : entity.getRoms()) {
                    machine.addRom(romEntity.toVO());
                }
                for (MameDiskEntity diskEntity : entity.getDisks()) {
                    machine.addDisk(diskEntity.toVO());
                }
                for (MameDevicerefEntity devicerefEntity : entity.getDevicerefs()) {
                    machine.addDeviceref(devicerefEntity.toVO());
                }
                for (MameSampleEntity sampleEntity : entity.getSamples()) {
                    machine.addSample(sampleEntity.toVO());
                }
                for (MameChipEntity chipEntity : entity.getChips()) {
                    machine.addChip(chipEntity.toVO());
                }
                for (MameDisplayEntity displayEntity : entity.getDisplays()) {
                    machine.addDisplay(displayEntity.toVO());
                }
                for (MameDipswitchEntity dipswitchEntity : entity.getDipswitches()) {
                    machine.addDipswitch(dipswitchEntity.toVO());
                }
                for (MameConfigurationEntity configurationEntity : entity.getConfigurations()) {
                    machine.addConfiguration(configurationEntity.toVO());
                }
                for (MamePortEntity port : entity.getPorts()) {
                    machine.addPort(port.toVO());
                }
                for (MameAdjusterEntity adjusterEntity : entity.getAdjusters()) {
                    machine.addAdjuster(adjusterEntity.toVO());
                }
                for (MameDeviceEntity deviceEntity : entity.getDevices()) {
                    machine.addDevice(deviceEntity.toVO());
                }
                for (MameSlotEntity slot : entity.getSlots()) {
                    machine.addSlot(slot.toVO());
                }
                for (MameSoftwarelistEntity sl : entity.getSoftwarelists()) {
                    machine.addSoftwarelist(sl.toVO());
                }
                for (MameRamoptionEntity ro : entity.getRamoptions()) {
                    machine.addRamoption(ro.toVO());
                }
            }
            return machine;
        }
        return null;
    }
}
