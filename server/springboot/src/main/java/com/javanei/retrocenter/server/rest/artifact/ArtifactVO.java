package com.javanei.retrocenter.server.rest.artifact;

import com.javanei.retrocenter.platform.common.Artifact;

import java.io.Serializable;

public class ArtifactVO extends Artifact implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    public ArtifactVO() {
    }

    public ArtifactVO(Long id) {
        this.id = id;
    }

    public ArtifactVO(Long id, String code, String name) {
        super(code, name);
        this.id = id;
    }
}
