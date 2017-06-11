package com.javanei.retrocenter.mame;

import com.javanei.retrocenter.common.util.StringUtil;
import java.io.Serializable;

public class MameSound implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer channels;

    public Integer getChannels() {
        return channels;
    }

    public void setChannels(String channels) {
        this.channels = new Integer(channels);
    }

    public void setChannels(Integer channels) {
        this.channels = channels;
    }

    @Override
    public String toString() {
        return "\t\t<sound channels=\"" + this.channels + "\"/>" + StringUtil.LINE_SEPARATOR;
    }
}
