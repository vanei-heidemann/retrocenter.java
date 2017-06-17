package com.javanei.retrocenter.mame;

import com.javanei.retrocenter.common.util.StringUtil;
import java.io.Serializable;
import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        MameAnalog that = (MameAnalog) o;
        return Objects.equals(mask, that.mask);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mask);
    }

    @Override
    public String toString() {
        return "\t\t\t<analog mask=\"" + this.mask + "\"/>" + StringUtil.LINE_SEPARATOR;
    }
}
