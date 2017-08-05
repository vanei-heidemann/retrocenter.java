package com.javanei.retrocenter.logiqx.service;

import com.javanei.retrocenter.logiqx.LogiqxDatafile;

import java.io.Serializable;

public class LogiqxDatafileDTO extends LogiqxDatafile implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    public LogiqxDatafileDTO() {
    }

    public LogiqxDatafileDTO(String build, String debug, Long id) {
        super(build, debug);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
