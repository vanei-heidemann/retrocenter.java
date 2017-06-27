package com.javanei.retrocenter.mame.service;

import com.javanei.retrocenter.mame.Mame;
import com.javanei.retrocenter.mame.MameAdjuster;
import com.javanei.retrocenter.mame.MameAnalog;
import com.javanei.retrocenter.mame.MameBiosset;
import com.javanei.retrocenter.mame.MameChip;
import com.javanei.retrocenter.mame.MameConfiguration;
import com.javanei.retrocenter.mame.MameConfsetting;
import com.javanei.retrocenter.mame.MameDevice;
import com.javanei.retrocenter.mame.MameDeviceExtension;
import com.javanei.retrocenter.mame.MameDeviceInstance;
import com.javanei.retrocenter.mame.MameDeviceref;
import com.javanei.retrocenter.mame.MameDipswitch;
import com.javanei.retrocenter.mame.MameDipvalue;
import com.javanei.retrocenter.mame.MameDisk;
import com.javanei.retrocenter.mame.MameDisplay;
import com.javanei.retrocenter.mame.MameInputControl;
import com.javanei.retrocenter.mame.MameMachine;
import com.javanei.retrocenter.mame.MamePort;
import com.javanei.retrocenter.mame.MameRamoption;
import com.javanei.retrocenter.mame.MameRom;
import com.javanei.retrocenter.mame.MameSample;
import com.javanei.retrocenter.mame.MameSlot;
import com.javanei.retrocenter.mame.MameSlotoption;
import com.javanei.retrocenter.mame.MameSoftwarelist;
import com.javanei.retrocenter.mame.entity.MameAdjusterEntity;
import com.javanei.retrocenter.mame.entity.MameAnalogEntity;
import com.javanei.retrocenter.mame.entity.MameBiossetEntity;
import com.javanei.retrocenter.mame.entity.MameChipEntity;
import com.javanei.retrocenter.mame.entity.MameConfigurationEntity;
import com.javanei.retrocenter.mame.entity.MameConfsettingEntity;
import com.javanei.retrocenter.mame.entity.MameDeviceEntity;
import com.javanei.retrocenter.mame.entity.MameDeviceExtensionEntity;
import com.javanei.retrocenter.mame.entity.MameDeviceInstanceEntity;
import com.javanei.retrocenter.mame.entity.MameDevicerefEntity;
import com.javanei.retrocenter.mame.entity.MameDipswitchEntity;
import com.javanei.retrocenter.mame.entity.MameDipvalueEntity;
import com.javanei.retrocenter.mame.entity.MameDiskEntity;
import com.javanei.retrocenter.mame.entity.MameDisplayEntity;
import com.javanei.retrocenter.mame.entity.MameDriverEntity;
import com.javanei.retrocenter.mame.entity.MameEntity;
import com.javanei.retrocenter.mame.entity.MameInputControlEntity;
import com.javanei.retrocenter.mame.entity.MameInputEntity;
import com.javanei.retrocenter.mame.entity.MameMachineEntity;
import com.javanei.retrocenter.mame.entity.MamePortEntity;
import com.javanei.retrocenter.mame.entity.MameRamoptionEntity;
import com.javanei.retrocenter.mame.entity.MameRomEntity;
import com.javanei.retrocenter.mame.entity.MameSampleEntity;
import com.javanei.retrocenter.mame.entity.MameSlotEntity;
import com.javanei.retrocenter.mame.entity.MameSlotoptionEntity;
import com.javanei.retrocenter.mame.entity.MameSoftwarelistEntity;
import com.javanei.retrocenter.mame.entity.MameSoundEntity;
import com.javanei.retrocenter.mame.persistence.MameAdjusterDAO;
import com.javanei.retrocenter.mame.persistence.MameAnalogDAO;
import com.javanei.retrocenter.mame.persistence.MameBiossetDAO;
import com.javanei.retrocenter.mame.persistence.MameChipDAO;
import com.javanei.retrocenter.mame.persistence.MameConfigurationDAO;
import com.javanei.retrocenter.mame.persistence.MameConfsettingDAO;
import com.javanei.retrocenter.mame.persistence.MameDAO;
import com.javanei.retrocenter.mame.persistence.MameDeviceDAO;
import com.javanei.retrocenter.mame.persistence.MameDeviceExtensionDAO;
import com.javanei.retrocenter.mame.persistence.MameDeviceInstanceDAO;
import com.javanei.retrocenter.mame.persistence.MameDevicerefDAO;
import com.javanei.retrocenter.mame.persistence.MameDipswitchDAO;
import com.javanei.retrocenter.mame.persistence.MameDipvalueDAO;
import com.javanei.retrocenter.mame.persistence.MameDiskDAO;
import com.javanei.retrocenter.mame.persistence.MameDisplayDAO;
import com.javanei.retrocenter.mame.persistence.MameDriverDAO;
import com.javanei.retrocenter.mame.persistence.MameInputControlDAO;
import com.javanei.retrocenter.mame.persistence.MameInputDAO;
import com.javanei.retrocenter.mame.persistence.MameMachineDAO;
import com.javanei.retrocenter.mame.persistence.MamePortDAO;
import com.javanei.retrocenter.mame.persistence.MameRamoptionDAO;
import com.javanei.retrocenter.mame.persistence.MameRomDAO;
import com.javanei.retrocenter.mame.persistence.MameSampleDAO;
import com.javanei.retrocenter.mame.persistence.MameSlotDAO;
import com.javanei.retrocenter.mame.persistence.MameSlotoptionDAO;
import com.javanei.retrocenter.mame.persistence.MameSoftwarelistDAO;
import com.javanei.retrocenter.mame.persistence.MameSoundDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MameService {
    @Autowired
    private MameDAO mameDAO;
    @Autowired
    private MameMachineDAO machineDAO;
    @Autowired
    private MameSoundDAO soundDAO;
    @Autowired
    private MameInputDAO inputDAO;
    @Autowired
    private MameDriverDAO driverDAO;
    @Autowired
    private MameInputControlDAO inputControlDAO;
    @Autowired
    private MameBiossetDAO biossetDAO;
    @Autowired
    private MameRomDAO romDAO;
    @Autowired
    private MameDiskDAO diskDAO;
    @Autowired
    private MameDevicerefDAO devicerefDAO;
    @Autowired
    private MameSampleDAO sampleDAO;
    @Autowired
    private MameChipDAO chipDAO;
    @Autowired
    private MameDisplayDAO displayDAO;
    @Autowired
    private MameDipswitchDAO dipswitchDAO;
    @Autowired
    private MameDipvalueDAO dipvalueDAO;
    @Autowired
    private MameConfigurationDAO configurationDAO;
    @Autowired
    private MameConfsettingDAO confsettingDAO;
    @Autowired
    private MamePortDAO portDAO;
    @Autowired
    private MameAnalogDAO analogDAO;
    @Autowired
    private MameAdjusterDAO adjusterDAO;
    @Autowired
    private MameDeviceDAO deviceDAO;
    @Autowired
    private MameDeviceInstanceDAO instanceDAO;
    @Autowired
    private MameDeviceExtensionDAO extensionDAO;
    @Autowired
    private MameSlotDAO slotDAO;
    @Autowired
    private MameSlotoptionDAO slotoptionDAO;
    @Autowired
    private MameSoftwarelistDAO softwarelistDAO;
    @Autowired
    private MameRamoptionDAO ramoptionDAO;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Mame create(String build, String debug, String mameconfig) {
        MameEntity entity = new MameEntity();
        entity.setBuild(build);
        entity.setDebug(debug);
        entity.setMameconfig(mameconfig);
        entity = mameDAO.save(entity);
        return entity.toVO();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Mame create(Mame mame) {
        MameEntity entity = mameDAO.saveAndFlush(new MameEntity(mame));
        for (MameMachine machine : mame.getMachines()) {
            saveMachine(entity, machine);
        }

        return entity.toVO();
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public Mame findByBuild(String build) {
        MameEntity entity = mameDAO.findByBuild(build);
        return entity != null ? new Mame(entity.getBuild(), entity.getDebug(), entity.getMameconfig()) : null;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public Mame findByBuildFull(String build) {
        MameEntity entity = mameDAO.findByBuildFull(build);
        return entity != null ? entity.toVO() : null;
    }

    private MameMachineEntity saveMachine(MameEntity mame, MameMachine machine) {
        MameMachineEntity machineEntity = new MameMachineEntity(machine);
        machineEntity.setMame(mame);
        machineEntity = machineDAO.saveAndFlush(machineEntity);

        if (machine.getSound() != null) {
            MameSoundEntity soundEntity = new MameSoundEntity(machine.getSound());
            soundEntity.setMachine(machineEntity);
            soundDAO.saveAndFlush(soundEntity);
        }

        if (machine.getInput() != null) {
            MameInputEntity inputEntity = new MameInputEntity(machine.getInput());
            inputEntity.setMachine(machineEntity);
            inputEntity = inputDAO.saveAndFlush(inputEntity);

            for (MameInputControl control : machine.getInput().getControls()) {
                MameInputControlEntity controlEntity = new MameInputControlEntity(control);
                controlEntity.setInput(inputEntity);
                inputControlDAO.saveAndFlush(controlEntity);
            }
        }

        if (machine.getDriver() != null) {
            MameDriverEntity driverEntity = new MameDriverEntity(machine.getDriver());
            driverEntity.setMachine(machineEntity);
            driverDAO.saveAndFlush(driverEntity);
        }

        for (MameBiosset biosset : machine.getBiossets()) {
            MameBiossetEntity biossetEntity = new MameBiossetEntity(biosset);
            biossetEntity.setMachine(machineEntity);
            biossetDAO.saveAndFlush(biossetEntity);
        }

        for (MameRom rom : machine.getRoms()) {
            MameRomEntity romEntity = new MameRomEntity(rom);
            romEntity.setMachine(machineEntity);
            romDAO.saveAndFlush(romEntity);
        }

        for (MameDisk disk : machine.getDisks()) {
            MameDiskEntity diskEntity = new MameDiskEntity(disk);
            diskEntity.setMachine(machineEntity);
            diskDAO.saveAndFlush(diskEntity);
        }

        for (MameDeviceref deviceref : machine.getDevicerefs()) {
            MameDevicerefEntity devicerefEntity = new MameDevicerefEntity(deviceref);
            devicerefEntity.setMachine(machineEntity);
            devicerefDAO.saveAndFlush(devicerefEntity);
        }

        for (MameSample sample : machine.getSamples()) {
            MameSampleEntity sampleEntity = new MameSampleEntity(sample);
            sampleEntity.setMachine(machineEntity);
            sampleDAO.saveAndFlush(sampleEntity);
        }

        for (MameChip chip : machine.getChips()) {
            MameChipEntity chipEntity = new MameChipEntity(chip);
            chipEntity.setMachine(machineEntity);
            chipDAO.saveAndFlush(chipEntity);
        }

        for (MameDisplay display : machine.getDisplays()) {
            MameDisplayEntity displayEntity = new MameDisplayEntity(display);
            displayEntity.setMachine(machineEntity);
            displayDAO.saveAndFlush(displayEntity);
        }

        for (MameDipswitch dipswitch : machine.getDipswitches()) {
            MameDipswitchEntity dipswitchEntity = new MameDipswitchEntity(dipswitch);
            dipswitchEntity.setMachine(machineEntity);
            dipswitchEntity = dipswitchDAO.saveAndFlush(dipswitchEntity);

            for (MameDipvalue dipvalue : dipswitch.getDipvalues()) {
                MameDipvalueEntity dipvalueEntity = new MameDipvalueEntity(dipvalue);
                dipvalueEntity.setDipswitch(dipswitchEntity);
                dipvalueDAO.saveAndFlush(dipvalueEntity);
            }
        }

        for (MameConfiguration configuration : machine.getConfigurations()) {
            MameConfigurationEntity configurationEntity = new MameConfigurationEntity(configuration);
            configurationEntity.setMachine(machineEntity);
            configurationEntity = configurationDAO.saveAndFlush(configurationEntity);

            for (MameConfsetting confsetting : configuration.getConfsettings()) {
                MameConfsettingEntity confsettingEntity = new MameConfsettingEntity(confsetting);
                confsettingEntity.setConfiguration(configurationEntity);
                confsettingDAO.saveAndFlush(confsettingEntity);
            }
        }

        for (MamePort port : machine.getPorts()) {
            MamePortEntity portEntity = new MamePortEntity(port);
            portEntity.setMachine(machineEntity);
            portEntity = portDAO.saveAndFlush(portEntity);

            for (MameAnalog analog : port.getAnalogs()) {
                MameAnalogEntity analogEntity = new MameAnalogEntity(analog);
                analogEntity.setPort(portEntity);
                analogDAO.saveAndFlush(analogEntity);
            }
        }

        for (MameAdjuster adjuster : machine.getAdjusters()) {
            MameAdjusterEntity adjusterEntity = new MameAdjusterEntity(adjuster);
            adjusterEntity.setMachine(machineEntity);
            adjusterDAO.saveAndFlush(adjusterEntity);
        }

        for (MameDevice device : machine.getDevices()) {
            MameDeviceEntity deviceEntity = new MameDeviceEntity(device);
            deviceEntity.setMachine(machineEntity);
            deviceEntity = deviceDAO.saveAndFlush(deviceEntity);

            for (MameDeviceInstance instance : device.getInstances()) {
                MameDeviceInstanceEntity instanceEntity = new MameDeviceInstanceEntity(instance);
                instanceEntity.setDevice(deviceEntity);
                instanceDAO.saveAndFlush(instanceEntity);
            }

            for (MameDeviceExtension extension : device.getExtensions()) {
                MameDeviceExtensionEntity extensionEntity = new MameDeviceExtensionEntity(extension);
                extensionEntity.setDevice(deviceEntity);
                extensionDAO.saveAndFlush(extensionEntity);
            }
        }

        for (MameSlot slot : machine.getSlots()) {
            MameSlotEntity slotEntity = new MameSlotEntity(slot);
            slotEntity.setMachine(machineEntity);
            slotEntity = slotDAO.saveAndFlush(slotEntity);

            for (MameSlotoption slotoption : slot.getSlotoptions()) {
                MameSlotoptionEntity slotoptionEntity = new MameSlotoptionEntity(slotoption);
                slotoptionEntity.setSlot(slotEntity);
                slotoptionDAO.saveAndFlush(slotoptionEntity);
            }
        }

        for (MameSoftwarelist softwarelist : machine.getSoftwarelists()) {
            MameSoftwarelistEntity softwarelistEntity = new MameSoftwarelistEntity(softwarelist);
            softwarelistEntity.setMachine(machineEntity);
            softwarelistDAO.saveAndFlush(softwarelistEntity);
        }

        for (MameRamoption ramoption : machine.getRamoptions()) {
            MameRamoptionEntity ramoptionEntity = new MameRamoptionEntity(ramoption);
            ramoptionEntity.setMachine(machineEntity);
            ramoptionDAO.saveAndFlush(ramoptionEntity);
        }

        return machineEntity;
    }
}
