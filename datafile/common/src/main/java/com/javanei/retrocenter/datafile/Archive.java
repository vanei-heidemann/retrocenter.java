package com.javanei.retrocenter.datafile;

import java.io.Serializable;
import java.util.Objects;

public class Archive implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * logiqx.archive.name
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
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Archive archive = (Archive) o;
        return Objects.equals(name, archive.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\t\t<archive name=\"").append(this.name).append(" />\n");
        return sb.toString();
    }
}
