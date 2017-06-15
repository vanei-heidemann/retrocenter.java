package com.javanei.retrocenter.mame;

import com.javanei.retrocenter.common.util.StringUtil;
import com.javanei.retrocenter.common.util.ValidValuesUtil;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Mame implements Serializable {
    private static final long serialVersionUID = 1L;

    private String build;
    private String debug; // (yes|no) "no"
    private String mameconfig;

    private Set<MameMachine> machines = new HashSet<>();

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

    public Set<MameMachine> getMachines() {
        return machines;
    }

    public void setMachines(Set<MameMachine> machines) {
        this.machines = machines;
    }

    public boolean addMachine(MameMachine machine) {
        return this.machines.add(machine);
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
