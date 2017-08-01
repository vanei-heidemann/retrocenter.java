package com.javanei.retrocenter.platform.service;

import com.javanei.retrocenter.common.Identified;
import com.javanei.retrocenter.platform.common.Artifact;
import com.javanei.retrocenter.platform.entity.ArtifactEntity;
import com.javanei.retrocenter.platform.persistence.ArtifactDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Service
@Transactional
public class ArtifactService {
    private static final Logger LOG = LoggerFactory.getLogger(ArtifactService.class);

    @Autowired
    private ArtifactDAO artifactDAO;

    @Transactional(propagation = Propagation.SUPPORTS)
    public Identified<Artifact> findArtifactById(Long id) {
        ArtifactEntity entity = artifactDAO.findOne(id);
        if (entity != null) {
            return new Identified<>(entity.getId(), new Artifact(entity.getCode(), entity.getName()));
        }
        return null;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public Identified<Artifact> findArtifactByCode(String code) {
        ArtifactEntity entity = artifactDAO.findByCode(code);
        if (entity != null) {
            return new Identified<>(entity.getId(), new Artifact(entity.getCode(), entity.getName()));
        }
        return null;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Identified<Artifact>> findArtifactByName(String name) {
        List<ArtifactEntity> l = artifactDAO.findByNameLike("%" + name + "%");
        List<Identified<Artifact>> result = new LinkedList<>();
        for (ArtifactEntity entity : l) {
            result.add(new Identified<>(entity.getId(), new Artifact(entity.getCode(), entity.getName())));
        }
        return result;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Identified<Artifact>> findArtifacts(Long id, String code, String name) {
        if (id != null) {
            Identified<Artifact> entity = this.findArtifactById(id);
            if (entity != null) {
                List<Identified<Artifact>> r = new ArrayList<>();
                r.add(entity);
                return r;
            }
            return Collections.emptyList();
        }
        if (code != null) {
            Identified<Artifact> entity = this.findArtifactByCode(code);
            if (entity != null) {
                List<Identified<Artifact>> r = new ArrayList<>();
                r.add(entity);
                return r;
            }
            return Collections.emptyList();
        }
        if (name != null) {
            return this.findArtifactByName(name);
        }
        List<ArtifactEntity> l = artifactDAO.findAll();
        List<Identified<Artifact>> result = new LinkedList<>();
        for (ArtifactEntity entity : l) {
            result.add(new Identified<>(entity.getId(), new Artifact(entity.getCode(), entity.getName())));
        }
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Identified<Artifact> createArtifact(Artifact artifact) {
        ArtifactEntity entity = new ArtifactEntity(artifact.getCode(), artifact.getName());
        entity = artifactDAO.saveAndFlush(entity);
        return new Identified<Artifact>(entity.getId(), artifact);
    }
}
