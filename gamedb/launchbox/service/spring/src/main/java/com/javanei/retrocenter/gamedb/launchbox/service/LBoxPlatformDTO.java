package com.javanei.retrocenter.gamedb.launchbox.service;

import com.javanei.retrocenter.gamedb.launchbox.common.LBoxPlatform;

import java.io.Serializable;

public class LBoxPlatformDTO extends LBoxPlatform implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long platformId;

    public LBoxPlatformDTO() {
    }

    public LBoxPlatformDTO(String name, Long id, Long platformId) {
        super(name);
        this.id = id;
        this.platformId = platformId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Long platformId) {
        this.platformId = platformId;
    }
}
