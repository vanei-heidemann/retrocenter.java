package com.javanei.retrocenter.mame;

import com.javanei.retrocenter.mame.util.StringUtil;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

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
