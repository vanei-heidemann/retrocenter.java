package com.javanei.retrocenter.mame;

import com.javanei.retrocenter.mame.util.StringUtil;
import com.javanei.retrocenter.mame.util.ValidValuesUtil;
import java.io.Serializable;

public class MameDipvalue implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private Long value;
    private String _default; // (yes|no) "no"

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public void setValue(String value) {
        this.value = new Long(value);
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
        sb.append("\t\t\t<dipvalue");
        if (this.name != null) {
            sb.append(" name=\"").append(StringUtil.escapeXMLEntities(this.name)).append("\"");
        }
        if (this.value != null) {
            sb.append(" value=\"").append(this.value).append("\"");
        }
        if (this._default != null && !this._default.equals("no")) {
            sb.append(" default=\"").append(this._default).append("\"");
        }
        sb.append("/>").append(StringUtil.LINE_SEPARATOR);
        return sb.toString();
    }
}
