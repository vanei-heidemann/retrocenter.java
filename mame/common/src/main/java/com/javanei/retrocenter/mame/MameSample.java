package com.javanei.retrocenter.mame;

import com.javanei.retrocenter.common.util.StringUtil;
import java.io.Serializable;

public class MameSample implements Serializable {
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
        return "\t\t<sample name=\"" + this.name + "\"/>" + StringUtil.LINE_SEPARATOR;
    }
}
