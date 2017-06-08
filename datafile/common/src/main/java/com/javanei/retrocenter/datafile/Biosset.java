package com.javanei.retrocenter.datafile;

import java.io.Serializable;

public class Biosset implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * logiqx.biosset.name
     */
    private String name;

    /**
     * logiqx.biosset.description
     */
    private String description;

    /**
     * logiqx.biosset.default
     */
    private String _default = "no";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDefault() {
        return _default;
    }

    public void setDefault(String _default) {
        this._default = _default;
    }
}
