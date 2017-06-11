package com.javanei.retrocenter.mame;

import com.javanei.retrocenter.common.util.StringUtil;
import java.io.Serializable;

public class MameAnalog implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer mask;

    public Integer getMask() {
        return mask;
    }

    public void setMask(String mask) {
        this.mask = new Integer(mask);
    }

    public void setMask(Integer mask) {
        this.mask = mask;
    }

    @Override
    public String toString() {
        return "\t\t\t<analog mask=\"" + this.mask + "\"/>" + StringUtil.LINE_SEPARATOR;
    }
}
