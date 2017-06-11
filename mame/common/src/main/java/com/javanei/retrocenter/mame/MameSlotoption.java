package com.javanei.retrocenter.mame;

import com.javanei.retrocenter.common.util.StringUtil;
import com.javanei.retrocenter.common.util.ValidValuesUtil;
import java.io.Serializable;

public class MameSlotoption implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String devname;
    private String _default; // (yes|no) "no"

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDevname() {
        return devname;
    }

    public void setDevname(String devname) {
        this.devname = devname;
    }

    public String getDefault() {
        return _default;
    }

    public void setDefault(String _default) {
        this._default = ValidValuesUtil.validateValue(_default, ValidValuesUtil.YES_NO);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\t\t\t<slotoption");
        if (this.name != null) {
            sb.append(" name=\"").append(this.name).append("\"");
        }
        if (this.devname != null) {
            sb.append(" devname=\"").append(this.devname).append("\"");
        }
        if (this._default != null && !this._default.equals("no")) {
            sb.append(" default=\"").append(this._default).append("\"");
        }
        sb.append("/>").append(StringUtil.LINE_SEPARATOR);
        return sb.toString();
    }
}
