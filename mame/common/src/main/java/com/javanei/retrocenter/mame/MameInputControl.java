package com.javanei.retrocenter.mame;

import com.javanei.retrocenter.common.util.StringUtil;
import com.javanei.retrocenter.common.util.ValidValuesUtil;
import java.io.Serializable;

public class MameInputControl implements Serializable {
    private static final long serialVersionUID = 1L;

    private String type;
    private Integer player;
    private Integer buttons;
    private Integer minimum;
    private Integer maximum;
    private Integer sensitivity;
    private Integer keydelta;
    private String reverse; // (yes|no) "no"
    private String ways;
    private String ways2;
    private String ways3;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = new Integer(player);
    }

    public void setPlayer(Integer player) {
        this.player = player;
    }

    public Integer getButtons() {
        return buttons;
    }

    public void setButtons(String buttons) {
        this.buttons = new Integer(buttons);
    }

    public void setButtons(Integer buttons) {
        this.buttons = buttons;
    }

    public Integer getMinimum() {
        return minimum;
    }

    public void setMinimum(String minimum) {
        this.minimum = new Integer(minimum);
    }

    public void setMinimum(Integer minimum) {
        this.minimum = minimum;
    }

    public Integer getMaximum() {
        return maximum;
    }

    public void setMaximum(String maximum) {
        this.maximum = new Integer(maximum);
    }

    public void setMaximum(Integer maximum) {
        this.maximum = maximum;
    }

    public Integer getSensitivity() {
        return sensitivity;
    }

    public void setSensitivity(String sensitivity) {
        this.sensitivity = new Integer(sensitivity);
    }

    public void setSensitivity(Integer sensitivity) {
        this.sensitivity = sensitivity;
    }

    public Integer getKeydelta() {
        return keydelta;
    }

    public void setKeydelta(String keydelta) {
        this.keydelta = new Integer(keydelta);
    }

    public void setKeydelta(Integer keydelta) {
        this.keydelta = keydelta;
    }

    public String getReverse() {
        return reverse;
    }

    public void setReverse(String reverse) {
        this.reverse = ValidValuesUtil.validateValue(reverse, ValidValuesUtil.YES_NO);
    }

    public String getWays() {
        return ways;
    }

    public void setWays(String ways) {
        this.ways = ways;
    }

    public String getWays2() {
        return ways2;
    }

    public void setWays2(String ways2) {
        this.ways2 = ways2;
    }

    public String getWays3() {
        return ways3;
    }

    public void setWays3(String ways3) {
        this.ways3 = ways3;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\t\t\t<control");
        if (this.type != null) {
            sb.append(" type=\"").append(this.type).append("\"");
        }
        if (this.player != null) {
            sb.append(" player=\"").append(this.player).append("\"");
        }
        if (this.buttons != null) {
            sb.append(" buttons=\"").append(this.buttons).append("\"");
        }
        if (this.minimum != null) {
            sb.append(" minimum=\"").append(this.minimum).append("\"");
        }
        if (this.maximum != null) {
            sb.append(" maximum=\"").append(this.maximum).append("\"");
        }
        if (this.sensitivity != null) {
            sb.append(" sensitivity=\"").append(this.sensitivity).append("\"");
        }
        if (this.keydelta != null) {
            sb.append(" keydelta=\"").append(this.keydelta).append("\"");
        }
        if (this.reverse != null && !this.reverse.equals("no")) {
            sb.append(" reverse=\"").append(this.reverse).append("\"");
        }
        if (this.ways != null) {
            sb.append(" ways=\"").append(this.ways).append("\"");
        }
        if (this.ways2 != null) {
            sb.append(" ways2=\"").append(this.ways2).append("\"");
        }
        if (this.ways3 != null) {
            sb.append(" ways3=\"").append(this.ways3).append("\"");
        }
        sb.append("/>").append(StringUtil.LINE_SEPARATOR);
        return sb.toString();
    }
}
