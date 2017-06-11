package com.javanei.retrocenter.mame;

import com.javanei.retrocenter.mame.util.StringUtil;
import com.javanei.retrocenter.mame.util.ValidValuesUtil;
import java.io.Serializable;

public class MameSoftwarelist implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String status; // (original|compatible)
    private String filter;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = ValidValuesUtil.validateValue(status, ValidValuesUtil.ORIGINAL_COMPATIBLE);
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\t\t<softwarelist");
        if (this.name != null) {
            sb.append(" name=\"").append(this.name).append("\"");
        }
        if (this.status != null) {
            sb.append(" status=\"").append(this.status).append("\"");
        }
        if (this.filter != null) {
            sb.append(" filter=\"").append(this.filter).append("\"");
        }
        sb.append(" />").append(StringUtil.LINE_SEPARATOR);
        return sb.toString();
    }
}
