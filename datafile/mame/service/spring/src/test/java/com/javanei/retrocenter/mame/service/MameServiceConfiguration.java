package com.javanei.retrocenter.mame.service;

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
import org.mockito.AdditionalAnswers;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;

public class MameServiceConfiguration {
    @Bean
    public MameService mameService() {
        return new MameService();
    }

    @Bean
    public MameDAO mameDAO() {
        MameDAO mameDAO = Mockito.mock(MameDAO.class);
        Mockito.when(mameDAO.saveAndFlush(Mockito.any())).then(AdditionalAnswers.returnsFirstArg());
        return mameDAO;
    }

    @Bean
    public MameMachineDAO machineDAO() {
        MameMachineDAO mameDAO = Mockito.mock(MameMachineDAO.class);
        Mockito.when(mameDAO.saveAndFlush(Mockito.any())).then(AdditionalAnswers.returnsFirstArg());
        return mameDAO;
    }

    @Bean
    public MameSoundDAO soundDAO() {
        MameSoundDAO mameDAO = Mockito.mock(MameSoundDAO.class);
        Mockito.when(mameDAO.saveAndFlush(Mockito.any())).then(AdditionalAnswers.returnsFirstArg());
        return mameDAO;
    }

    @Bean
    public MameInputDAO inputDAO() {
        MameInputDAO mameDAO = Mockito.mock(MameInputDAO.class);
        Mockito.when(mameDAO.saveAndFlush(Mockito.any())).then(AdditionalAnswers.returnsFirstArg());
        return mameDAO;
    }

    @Bean
    public MameDriverDAO driverDAO() {
        MameDriverDAO mameDAO = Mockito.mock(MameDriverDAO.class);
        Mockito.when(mameDAO.saveAndFlush(Mockito.any())).then(AdditionalAnswers.returnsFirstArg());
        return mameDAO;
    }

    @Bean
    public MameInputControlDAO inputControlDAO() {
        MameInputControlDAO mameDAO = Mockito.mock(MameInputControlDAO.class);
        Mockito.when(mameDAO.saveAndFlush(Mockito.any())).then(AdditionalAnswers.returnsFirstArg());
        return mameDAO;
    }

    @Bean
    public MameBiossetDAO biossetDAO() {
        MameBiossetDAO mameDAO = Mockito.mock(MameBiossetDAO.class);
        Mockito.when(mameDAO.saveAndFlush(Mockito.any())).then(AdditionalAnswers.returnsFirstArg());
        return mameDAO;
    }

    @Bean
    public MameRomDAO romDAO() {
        MameRomDAO mameDAO = Mockito.mock(MameRomDAO.class);
        Mockito.when(mameDAO.saveAndFlush(Mockito.any())).then(AdditionalAnswers.returnsFirstArg());
        return mameDAO;
    }

    @Bean
    public MameDiskDAO diskDAO() {
        MameDiskDAO mameDAO = Mockito.mock(MameDiskDAO.class);
        Mockito.when(mameDAO.saveAndFlush(Mockito.any())).then(AdditionalAnswers.returnsFirstArg());
        return mameDAO;
    }

    @Bean
    public MameDevicerefDAO devicerefDAO() {
        MameDevicerefDAO mameDAO = Mockito.mock(MameDevicerefDAO.class);
        Mockito.when(mameDAO.saveAndFlush(Mockito.any())).then(AdditionalAnswers.returnsFirstArg());
        return mameDAO;
    }

    @Bean
    public MameSampleDAO sampleDAO() {
        MameSampleDAO mameDAO = Mockito.mock(MameSampleDAO.class);
        Mockito.when(mameDAO.saveAndFlush(Mockito.any())).then(AdditionalAnswers.returnsFirstArg());
        return mameDAO;
    }

    @Bean
    public MameChipDAO chipDAO() {
        MameChipDAO mameDAO = Mockito.mock(MameChipDAO.class);
        Mockito.when(mameDAO.saveAndFlush(Mockito.any())).then(AdditionalAnswers.returnsFirstArg());
        return mameDAO;
    }

    @Bean
    public MameDisplayDAO displayDAO() {
        MameDisplayDAO mameDAO = Mockito.mock(MameDisplayDAO.class);
        Mockito.when(mameDAO.saveAndFlush(Mockito.any())).then(AdditionalAnswers.returnsFirstArg());
        return mameDAO;
    }

    @Bean
    public MameDipswitchDAO dipswitchDAO() {
        MameDipswitchDAO mameDAO = Mockito.mock(MameDipswitchDAO.class);
        Mockito.when(mameDAO.saveAndFlush(Mockito.any())).then(AdditionalAnswers.returnsFirstArg());
        return mameDAO;
    }

    @Bean
    public MameDipvalueDAO dipvalueDAO() {
        MameDipvalueDAO mameDAO = Mockito.mock(MameDipvalueDAO.class);
        Mockito.when(mameDAO.saveAndFlush(Mockito.any())).then(AdditionalAnswers.returnsFirstArg());
        return mameDAO;
    }

    @Bean
    public MameConfigurationDAO configurationDAO() {
        MameConfigurationDAO mameDAO = Mockito.mock(MameConfigurationDAO.class);
        Mockito.when(mameDAO.saveAndFlush(Mockito.any())).then(AdditionalAnswers.returnsFirstArg());
        return mameDAO;
    }

    @Bean
    public MameConfsettingDAO confsettingDAO() {
        MameConfsettingDAO mameDAO = Mockito.mock(MameConfsettingDAO.class);
        Mockito.when(mameDAO.saveAndFlush(Mockito.any())).then(AdditionalAnswers.returnsFirstArg());
        return mameDAO;
    }

    @Bean
    public MamePortDAO portDAO() {
        MamePortDAO mameDAO = Mockito.mock(MamePortDAO.class);
        Mockito.when(mameDAO.saveAndFlush(Mockito.any())).then(AdditionalAnswers.returnsFirstArg());
        return mameDAO;
    }

    @Bean
    public MameAnalogDAO analogDAO() {
        MameAnalogDAO mameDAO = Mockito.mock(MameAnalogDAO.class);
        Mockito.when(mameDAO.saveAndFlush(Mockito.any())).then(AdditionalAnswers.returnsFirstArg());
        return mameDAO;
    }

    @Bean
    public MameAdjusterDAO adjusterDAO() {
        MameAdjusterDAO mameDAO = Mockito.mock(MameAdjusterDAO.class);
        Mockito.when(mameDAO.saveAndFlush(Mockito.any())).then(AdditionalAnswers.returnsFirstArg());
        return mameDAO;
    }

    @Bean
    public MameDeviceDAO deviceDAO() {
        MameDeviceDAO mameDAO = Mockito.mock(MameDeviceDAO.class);
        Mockito.when(mameDAO.saveAndFlush(Mockito.any())).then(AdditionalAnswers.returnsFirstArg());
        return mameDAO;
    }

    @Bean
    public MameDeviceInstanceDAO instanceDAO() {
        MameDeviceInstanceDAO mameDAO = Mockito.mock(MameDeviceInstanceDAO.class);
        Mockito.when(mameDAO.saveAndFlush(Mockito.any())).then(AdditionalAnswers.returnsFirstArg());
        return mameDAO;
    }

    @Bean
    public MameDeviceExtensionDAO extensionDAO() {
        MameDeviceExtensionDAO mameDAO = Mockito.mock(MameDeviceExtensionDAO.class);
        Mockito.when(mameDAO.saveAndFlush(Mockito.any())).then(AdditionalAnswers.returnsFirstArg());
        return mameDAO;
    }

    @Bean
    public MameSlotDAO slotDAO() {
        MameSlotDAO mameDAO = Mockito.mock(MameSlotDAO.class);
        Mockito.when(mameDAO.saveAndFlush(Mockito.any())).then(AdditionalAnswers.returnsFirstArg());
        return mameDAO;
    }

    @Bean
    public MameSlotoptionDAO slotoptionDAO() {
        MameSlotoptionDAO mameDAO = Mockito.mock(MameSlotoptionDAO.class);
        Mockito.when(mameDAO.saveAndFlush(Mockito.any())).then(AdditionalAnswers.returnsFirstArg());
        return mameDAO;
    }

    @Bean
    public MameSoftwarelistDAO softwarelistDAO() {
        MameSoftwarelistDAO mameDAO = Mockito.mock(MameSoftwarelistDAO.class);
        Mockito.when(mameDAO.saveAndFlush(Mockito.any())).then(AdditionalAnswers.returnsFirstArg());
        return mameDAO;
    }

    @Bean
    public MameRamoptionDAO ramoptionDAO() {
        MameRamoptionDAO mameDAO = Mockito.mock(MameRamoptionDAO.class);
        Mockito.when(mameDAO.saveAndFlush(Mockito.any())).then(AdditionalAnswers.returnsFirstArg());
        return mameDAO;
    }
}
