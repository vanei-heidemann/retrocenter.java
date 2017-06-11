package com.javanei.retrocenter.mame;

import com.javanei.retrocenter.mame.util.StringUtil;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class MamePort implements Serializable {
    private static final long serialVersionUID = 1L;

    private String tag;

    private List<MameAnalog> analogs = new LinkedList<>();

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public List<MameAnalog> getAnalogs() {
        return analogs;
    }

    public void setAnalogs(List<MameAnalog> analogs) {
        this.analogs = analogs;
    }

    public void addAnalog(MameAnalog analog) {
        this.analogs.add(analog);
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
