package com.javanei.retrocenter.mame;

import com.javanei.retrocenter.common.util.StringUtil;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class MamePort implements Serializable {
    private static final long serialVersionUID = 1L;

    private String tag;

    private Set<MameAnalog> analogs = new HashSet<>();

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Set<MameAnalog> getAnalogs() {
        return analogs;
    }

    public void setAnalogs(Set<MameAnalog> analogs) {
        this.analogs = analogs;
    }

    public boolean addAnalog(MameAnalog analog) {
        return this.analogs.add(analog);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        MamePort mamePort = (MamePort) o;
        return Objects.equals(tag, mamePort.tag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tag);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\t\t<port");
        if (this.tag != null) {
            sb.append(" tag=\"").append(StringUtil.escapeXMLEntities(this.tag)).append("\"");
        }
        sb.append(">").append(StringUtil.LINE_SEPARATOR);

        for (MameAnalog analog : this.analogs) {
            sb.append(analog.toString());
        }

        sb.append("\t\t</port>").append(StringUtil.LINE_SEPARATOR);
        return sb.toString();
    }
}
