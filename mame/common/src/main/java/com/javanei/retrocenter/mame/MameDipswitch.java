package com.javanei.retrocenter.mame;

import com.javanei.retrocenter.common.util.StringUtil;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class MameDipswitch implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String tag;
    private Long mask;

    private List<MameDipvalue> dipvalues = new LinkedList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Long getMask() {
        return mask;
    }

    public void setMask(String mask) {
        this.mask = new Long(mask);
    }

    public void setMask(Long mask) {
        this.mask = mask;
    }

    public List<MameDipvalue> getDipvalues() {
        return dipvalues;
    }

    public void setDipvalues(List<MameDipvalue> dipvalues) {
        this.dipvalues = dipvalues;
    }

    public void addDipvalue(MameDipvalue dipvalue) {
        this.dipvalues.add(dipvalue);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        MameDipswitch that = (MameDipswitch) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(tag, that.tag) &&
                Objects.equals(mask, that.mask) &&
                Objects.equals(dipvalues, that.dipvalues);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, tag, mask, dipvalues);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\t\t<dipswitch");
        if (this.name != null) {
            sb.append(" name=\"").append(StringUtil.escapeXMLEntities(this.name)).append("\"");
        }
        if (this.tag != null) {
            sb.append(" tag=\"").append(this.tag).append("\"");
        }
        if (this.mask != null) {
            sb.append(" mask=\"").append(this.mask).append("\"");
        }
        sb.append(">").append(StringUtil.LINE_SEPARATOR);

        for (MameDipvalue dipvalue : dipvalues) {
            sb.append(dipvalue.toString());
        }

        sb.append("\t\t</dipswitch>").append(StringUtil.LINE_SEPARATOR);
        return sb.toString();
    }
}
