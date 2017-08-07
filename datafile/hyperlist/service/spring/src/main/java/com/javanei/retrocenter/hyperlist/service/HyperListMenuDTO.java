package com.javanei.retrocenter.hyperlist.service;

import com.javanei.retrocenter.hyperlist.HyperListHeader;
import com.javanei.retrocenter.hyperlist.HyperListMenu;

import java.io.Serializable;

public class HyperListMenuDTO extends HyperListMenu implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    public HyperListMenuDTO() {
    }

    public HyperListMenuDTO(HyperListHeader header, Long id) {
        super(header);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
