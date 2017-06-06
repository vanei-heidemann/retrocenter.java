package com.javanei.retrocenter.logiqx;

import java.io.Serializable;
import java.util.Objects;

/**
 * <!ELEMENT archive EMPTY>
 */
public class LogiqxArchive implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * <!ATTLIST archive name CDATA #REQUIRED>
     */
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogiqxArchive archive = (LogiqxArchive) o;
        return Objects.equals(name, archive.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
