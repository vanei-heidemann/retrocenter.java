package com.javanei.retrocenter.clrmamepro.service;

import com.javanei.retrocenter.clrmamepro.persistence.CMProDatafileDAO;
import com.javanei.retrocenter.clrmamepro.persistence.CMProDiskDAO;
import com.javanei.retrocenter.clrmamepro.persistence.CMProGameDAO;
import com.javanei.retrocenter.clrmamepro.persistence.CMProGameRomDAO;
import com.javanei.retrocenter.clrmamepro.persistence.CMProResourceDAO;
import com.javanei.retrocenter.clrmamepro.persistence.CMProResourceRomDAO;
import com.javanei.retrocenter.clrmamepro.persistence.CMProSampleDAO;
import com.javanei.retrocenter.clrmamepro.persistence.CMProSampleofDAO;
import org.mockito.AdditionalAnswers;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;

public class CMProServiceConfiguration {
    @Bean
    public CMProService cmProService() {
        return new CMProService();
    }

    @Bean
    public CMProDatafileDAO datafileDAO() {
        CMProDatafileDAO dao = Mockito.mock(CMProDatafileDAO.class);
        Mockito.when(dao.saveAndFlush(Mockito.any())).then(AdditionalAnswers.returnsFirstArg());
        return dao;
    }

    @Bean
    public CMProDiskDAO diskDAO() {
        CMProDiskDAO dao = Mockito.mock(CMProDiskDAO.class);
        Mockito.when(dao.saveAndFlush(Mockito.any())).then(AdditionalAnswers.returnsFirstArg());
        return dao;
    }

    @Bean
    public CMProGameDAO gameDAO() {
        CMProGameDAO dao = Mockito.mock(CMProGameDAO.class);
        Mockito.when(dao.saveAndFlush(Mockito.any())).then(AdditionalAnswers.returnsFirstArg());
        return dao;
    }

    @Bean
    public CMProGameRomDAO gameRomDAO() {
        CMProGameRomDAO dao = Mockito.mock(CMProGameRomDAO.class);
        Mockito.when(dao.saveAndFlush(Mockito.any())).then(AdditionalAnswers.returnsFirstArg());
        return dao;
    }

    @Bean
    public CMProResourceDAO resourceDAO() {
        CMProResourceDAO dao = Mockito.mock(CMProResourceDAO.class);
        Mockito.when(dao.saveAndFlush(Mockito.any())).then(AdditionalAnswers.returnsFirstArg());
        return dao;
    }

    @Bean
    public CMProResourceRomDAO resourceRomDAO() {
        CMProResourceRomDAO dao = Mockito.mock(CMProResourceRomDAO.class);
        Mockito.when(dao.saveAndFlush(Mockito.any())).then(AdditionalAnswers.returnsFirstArg());
        return dao;
    }

    @Bean
    public CMProSampleDAO sampleDAO() {
        CMProSampleDAO dao = Mockito.mock(CMProSampleDAO.class);
        Mockito.when(dao.saveAndFlush(Mockito.any())).then(AdditionalAnswers.returnsFirstArg());
        return dao;
    }

    @Bean
    public CMProSampleofDAO sampleofDAO() {
        CMProSampleofDAO dao = Mockito.mock(CMProSampleofDAO.class);
        Mockito.when(dao.saveAndFlush(Mockito.any())).then(AdditionalAnswers.returnsFirstArg());
        return dao;
    }
}
