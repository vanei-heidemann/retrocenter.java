package com.javanei.retrocenter.mame;

import com.javanei.retrocenter.mame.util.StringUtil;
import java.io.Serializable;

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

    public void setDefault(Integer _default) {
        this._default = _default;
    }

    public void setDefault(String _default) {
        this._default = new Integer(_default);
    }

    @Override
    public String toString() {
        return "\t\t<adjuster name=\"" + StringUtil.escapeXMLEntities(this.name)
                + "\" default=\"" + this._default + "\"/>" + StringUtil.LINE_SEPARATOR;
    }
}
