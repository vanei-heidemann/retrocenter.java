package com.javanei.retrocenter.logiqx.service;

import com.javanei.retrocenter.logiqx.persistence.LogiqxArchiveDAO;
import com.javanei.retrocenter.logiqx.persistence.LogiqxBiossetDAO;
import com.javanei.retrocenter.logiqx.persistence.LogiqxDatafileDAO;
import com.javanei.retrocenter.logiqx.persistence.LogiqxDiskDAO;
import com.javanei.retrocenter.logiqx.persistence.LogiqxGameDAO;
import com.javanei.retrocenter.logiqx.persistence.LogiqxReleaseDAO;
import com.javanei.retrocenter.logiqx.persistence.LogiqxRomDAO;
import com.javanei.retrocenter.logiqx.persistence.LogiqxSampleDAO;
import org.mockito.AdditionalAnswers;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;

public class LogiqxServiceConfiguration {
    @Bean
    public LogiqxService logiqxService() {
        return new LogiqxService();
    }

    @Bean
    public LogiqxDatafileDAO datafileDAO() {
        LogiqxDatafileDAO dao = Mockito.mock(LogiqxDatafileDAO.class);
        Mockito.when(dao.saveAndFlush(Mockito.any())).then(AdditionalAnswers.returnsFirstArg());
        return dao;
    }

    @Bean
    public LogiqxArchiveDAO archiveDAO() {
        LogiqxArchiveDAO dao = Mockito.mock(LogiqxArchiveDAO.class);
        Mockito.when(dao.saveAndFlush(Mockito.any())).then(AdditionalAnswers.returnsFirstArg());
        return dao;
    }

    @Bean
    public LogiqxBiossetDAO biossetDAO() {
        LogiqxBiossetDAO dao = Mockito.mock(LogiqxBiossetDAO.class);
        Mockito.when(dao.saveAndFlush(Mockito.any())).then(AdditionalAnswers.returnsFirstArg());
        return dao;
    }

    @Bean
    public LogiqxDiskDAO diskDAO() {
        LogiqxDiskDAO dao = Mockito.mock(LogiqxDiskDAO.class);
        Mockito.when(dao.saveAndFlush(Mockito.any())).then(AdditionalAnswers.returnsFirstArg());
        return dao;
    }

    @Bean
    public LogiqxGameDAO gameDAO() {
        LogiqxGameDAO dao = Mockito.mock(LogiqxGameDAO.class);
        Mockito.when(dao.saveAndFlush(Mockito.any())).then(AdditionalAnswers.returnsFirstArg());
        return dao;
    }

    @Bean
    public LogiqxReleaseDAO releaseDAO() {
        LogiqxReleaseDAO dao = Mockito.mock(LogiqxReleaseDAO.class);
        Mockito.when(dao.saveAndFlush(Mockito.any())).then(AdditionalAnswers.returnsFirstArg());
        return dao;
    }

    @Bean
    public LogiqxRomDAO romDAO() {
        LogiqxRomDAO dao = Mockito.mock(LogiqxRomDAO.class);
        Mockito.when(dao.saveAndFlush(Mockito.any())).then(AdditionalAnswers.returnsFirstArg());
        return dao;
    }

    @Bean
    public LogiqxSampleDAO sampleDAO() {
        LogiqxSampleDAO dao = Mockito.mock(LogiqxSampleDAO.class);
        Mockito.when(dao.saveAndFlush(Mockito.any())).then(AdditionalAnswers.returnsFirstArg());
        return dao;
    }
}
