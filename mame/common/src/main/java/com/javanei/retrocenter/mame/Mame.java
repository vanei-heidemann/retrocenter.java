package com.javanei.retrocenter.mame;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import com.javanei.retrocenter.common.util.StringUtil;
import com.javanei.retrocenter.common.util.ValidValuesUtil;

public class Mame implements Serializable {
    private static final long serialVersionUID = 1L;

    private String build;
    private String debug; // (yes|no) "no"
    private String mameconfig;

    private List<MameMachine> machines = new LinkedList<>();

    public String getBuild() {
        return build;
    }

    public void setBuild(String build) {
        this.build = build;
    }

    public String getDebug() {
        return debug;
    }

    public void setDebug(String debug) {
        this.debug = ValidValuesUtil.validateValue(debug, ValidValuesUtil.YES_NO);
    }

    public String getMameconfig() {
        return mameconfig;
    }

    public void setMameconfig(String mameconfig) {
        this.mameconfig = mameconfig;
    }

    public List<MameMachine> getMachines() {
        return machines;
    }

    public void setMachines(List<MameMachine> machines) {
        this.machines = machines;
    }

    public void addMachine(MameMachine machine) {
        this.machines.add(machine);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Mame mame = (Mame) o;
        return Objects.equals(build, mame.build);
    }

    @Override
    public int hashCode() {
        return Objects.hash(build);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<mame");
        if (this.build != null) {
            sb.append(" build=\"").append(this.build).append("\"");
        }
        if (this.debug != null && !this.debug.equals("no")) {
            sb.append(" debug=\"").append(this.debug).append("\"");
        }
        if (this.mameconfig != null) {
            sb.append(" mameconfig=\"").append(this.mameconfig).append("\"");
        }
        sb.append(">").append(StringUtil.LINE_SEPARATOR);

        for (MameMachine machine : this.machines) {
            sb.append(machine.toString());
        }

        sb.append("</mame>").append(StringUtil.LINE_SEPARATOR);
        return sb.toString();
    }
}
