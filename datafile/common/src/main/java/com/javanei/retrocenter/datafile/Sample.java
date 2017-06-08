package com.javanei.retrocenter.datafile;

import java.io.Serializable;
import java.util.Objects;

import com.javanei.retrocenter.logiqx.LogiqxSample;

public class Sample implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * logiqx.sample.name
     */
    private String name;

    public Sample() {
    }

    public Sample(String name) {
        this.name = name;
    }

    public static Sample fromLogiqx(LogiqxSample p) {
        return new Sample(p.getName());
    }

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
        Sample sample = (Sample) o;
        return Objects.equals(name, sample.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\t\t<sample name=\"").append(this.name).append(" />\n");
        return sb.toString();
    }
}
