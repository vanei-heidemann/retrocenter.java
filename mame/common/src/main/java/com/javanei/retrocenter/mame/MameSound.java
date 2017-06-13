package com.javanei.retrocenter.mame;

import java.io.Serializable;
import java.util.Objects;

import com.javanei.retrocenter.common.util.StringUtil;

public class MameSound implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer channels;

    public Integer getChannels() {
        return channels;
    }

    public void setChannels(Integer channels) {
        this.channels = channels;
    }

    public void setChannels(String channels) {
        this.channels = new Integer(channels);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        MameSound mameSound = (MameSound) o;
        return Objects.equals(channels, mameSound.channels);
    }

    @Override
    public int hashCode() {
        return Objects.hash(channels);
    }

    @Override
    public String toString() {
        return "\t\t<sound channels=\"" + this.channels + "\"/>" + StringUtil.LINE_SEPARATOR;
    }
}
