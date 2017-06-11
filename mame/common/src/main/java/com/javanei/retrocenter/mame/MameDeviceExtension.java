package com.javanei.retrocenter.mame;

import com.javanei.retrocenter.common.util.StringUtil;
import java.io.Serializable;

public class MameDeviceExtension implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "\t\t\t<extension name=\"" + this.name + "\"/>" + StringUtil.LINE_SEPARATOR;
    }
}
