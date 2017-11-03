package com.javanei.retrocenter.goodtools.service;

import com.javanei.retrocenter.goodtools.GoodDatafile;

import java.io.Serializable;

public class GoodDatafileDTO extends GoodDatafile implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    public GoodDatafileDTO() {
    }

    public GoodDatafileDTO(Long id) {
        this.id = id;
    }

    public GoodDatafileDTO(String name, String version, String author, String date, String comment, Long id) {
        super(name, version, author, date, comment);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
