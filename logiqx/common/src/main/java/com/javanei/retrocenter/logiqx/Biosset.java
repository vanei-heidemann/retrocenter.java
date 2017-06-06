package com.javanei.retrocenter.logiqx;

import java.io.Serializable;
import java.util.Objects;

/**
 * <!ELEMENT biosset EMPTY>
 */
public class Biosset implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * <!ATTLIST biosset name CDATA #REQUIRED>
     */
    private String name;

    /**
     * <!ATTLIST biosset description CDATA #REQUIRED>
     */
    private String description;

    /**
     * <!ATTLIST biosset default (yes|no) "no">
     */
    private String _default =  "no";

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Biosset biosset = (Biosset) o;
        return Objects.equals(name, biosset.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
