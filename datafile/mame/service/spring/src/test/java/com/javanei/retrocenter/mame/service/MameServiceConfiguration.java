package com.javanei.retrocenter.mame.service;

import com.javanei.retrocenter.mame.persistence.MameDAO;
import com.javanei.retrocenter.mame.persistence.MameMachineDAO;
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
}
