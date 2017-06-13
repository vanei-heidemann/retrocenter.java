package com.javanei.retrocenter.mame;

import java.io.Serializable;
import java.util.Objects;

import com.javanei.retrocenter.common.util.StringUtil;

public class MameDeviceInstance implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String briefname;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBriefname() {
        return briefname;
    }

    public void setBriefname(String briefname) {
        this.briefname = briefname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        MameDeviceInstance that = (MameDeviceInstance) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "\t\t\t<instance name=\"" + this.name + "\" briefname=\"" + this.briefname + "\"/>" + StringUtil.LINE_SEPARATOR;
    }
}
