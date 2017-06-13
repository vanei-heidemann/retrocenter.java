package com.javanei.retrocenter.mame;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import com.javanei.retrocenter.common.util.StringUtil;

public class MameSlot implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;

    private List<MameSlotoption> slotoptions = new LinkedList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MameSlotoption> getSlotoptions() {
        return slotoptions;
    }

    public void setSlotoptions(List<MameSlotoption> slotoptions) {
        this.slotoptions = slotoptions;
    }

    public void addSlotoption(MameSlotoption slotoption) {
        this.slotoptions.add(slotoption);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        MameSlot mameSlot = (MameSlot) o;
        return Objects.equals(name, mameSlot.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\t\t<slot name=\"").append(this.name).append("\">").append(StringUtil.LINE_SEPARATOR);

        for (MameSlotoption slotoption : this.slotoptions) {
            sb.append(slotoption.toString());
        }

        sb.append("\t\t</slot>").append(StringUtil.LINE_SEPARATOR);
        return sb.toString();
    }
}
