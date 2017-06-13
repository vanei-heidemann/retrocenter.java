package com.javanei.retrocenter.mame;

import java.io.Serializable;
import java.util.Objects;

import com.javanei.retrocenter.common.util.StringUtil;

public class MameDeviceref implements Serializable {
    private static final long serialVersionUID = 1L;

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
        MameDeviceref that = (MameDeviceref) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "\t\t<device_ref name=\"" + this.name + "\"/>" + StringUtil.LINE_SEPARATOR;
    }
}
