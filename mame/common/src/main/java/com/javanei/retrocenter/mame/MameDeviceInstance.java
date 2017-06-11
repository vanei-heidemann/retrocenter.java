package com.javanei.retrocenter.mame;

import com.javanei.retrocenter.common.util.StringUtil;
import java.io.Serializable;

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
    public String toString() {
        return "\t\t\t<instance name=\"" + this.name + "\" briefname=\"" + this.briefname + "\"/>" + StringUtil.LINE_SEPARATOR;
    }
}
