package com.javanei.retrocenter.mame.mamesoftwarelist;

import com.javanei.retrocenter.common.util.StringUtil;
import java.io.Serializable;

public class MameSoftwareSharedfeat implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\t\t\t<sharedfeat name=\"").append(this.name).append("\" value=\"").append(this.value).append("\"/>").append(StringUtil.LINE_SEPARATOR);
        return sb.toString();
    }
}
