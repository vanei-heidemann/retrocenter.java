package com.javanei.retrocenter.mame;

import com.javanei.retrocenter.common.util.StringUtil;
import java.io.Serializable;
import java.util.Objects;

public class MameAdjuster implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private Integer _default;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDefault() {
        return _default;
    }

    public void setDefault(String _default) {
        this._default = new Integer(_default);
    }

    public void setDefault(Integer _default) {
        this._default = _default;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        MameAdjuster that = (MameAdjuster) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "\t\t<adjuster name=\"" + StringUtil.escapeXMLEntities(this.name)
                + "\" default=\"" + this._default + "\"/>" + StringUtil.LINE_SEPARATOR;
    }
}
