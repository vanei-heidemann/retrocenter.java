package com.javanei.retrocenter.gamedb.launchbox.service;

import com.javanei.retrocenter.gamedb.launchbox.common.LBoxPlatform;

import java.io.Serializable;

public class LBoxPlatformDTO extends LBoxPlatform implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    public LBoxPlatformDTO() {
    }

    public LBoxPlatformDTO(String name, Long id) {
        super(name);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
