package com.javanei.retrocenter.datafile.service;

import com.javanei.retrocenter.datafile.entity.DatafileEntity;
import com.javanei.retrocenter.datafile.persistence.ArtifactDAO;
import com.javanei.retrocenter.datafile.persistence.ArtifactFileDAO;
import com.javanei.retrocenter.datafile.persistence.DatafileDAO;
import com.javanei.retrocenter.datafile.persistence.ReleaseDAO;
import org.mockito.AdditionalAnswers;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatafileServiceConfiguration {
    @Bean
    public DatafileService datafileService() {
        return new DatafileService();
    }

    @Bean
    public DatafileDAO datafileDAO() {
        DatafileDAO datafileDAO = Mockito.mock(DatafileDAO.class);
        //Mockito.when(datafileDAO.saveAndFlush(Mockito.any())).then(AdditionalAnswers.returnsFirstArg());
        Mockito.when(datafileDAO.saveAndFlush(Mockito.any())).thenAnswer(new Answer<DatafileEntity>() {
            @Override
            public DatafileEntity answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                DatafileEntity r = (DatafileEntity) args[0];
                r.setId(1l);
                return r;
            }
        });
        return datafileDAO;
    }

    @Bean
    public ArtifactDAO artifactDAO() {
        ArtifactDAO artifactDAO = Mockito.mock(ArtifactDAO.class);
        Mockito.when(artifactDAO.saveAndFlush(Mockito.any())).then(AdditionalAnswers.returnsFirstArg());
        return artifactDAO;
    }

    @Bean
    public ArtifactFileDAO artifactFileDAO() {
        ArtifactFileDAO artifactFileDAO = Mockito.mock(ArtifactFileDAO.class);
        Mockito.when(artifactFileDAO.saveAndFlush(Mockito.any())).then(AdditionalAnswers.returnsFirstArg());
        return artifactFileDAO;
    }

    @Bean
    public ReleaseDAO releaseDAO() {
        ReleaseDAO releaseDAO = Mockito.mock(ReleaseDAO.class);
        Mockito.when(releaseDAO.saveAndFlush(Mockito.any())).then(AdditionalAnswers.returnsFirstArg());
        return releaseDAO;
    }
}
