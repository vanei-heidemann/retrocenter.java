package com.javanei.retrocenter.mame;

import com.javanei.retrocenter.common.DatafileCategoryEnum;
import com.javanei.retrocenter.common.util.StringUtil;
import com.javanei.retrocenter.common.util.ValidValuesUtil;
import com.javanei.retrocenter.datafile.Artifact;
import com.javanei.retrocenter.datafile.ArtifactFile;
import com.javanei.retrocenter.datafile.ArtifactFileTypeEnum;
import com.javanei.retrocenter.datafile.Datafile;
import com.javanei.retrocenter.datafile.DatafileObject;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Mame implements DatafileObject, Serializable {
    private static final long serialVersionUID = 1L;

    private String build;
    private String debug; // (yes|no) "no"
    private String mameconfig;

    private Set<MameMachine> machines = new HashSet<>();

    public Mame() {
    }

    public Mame(String build, String debug, String mameconfig) {
        this.build = build;
        this.debug = debug;
        this.mameconfig = mameconfig;
    }

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

    @Override
    public Datafile toDatafile() {
        Datafile datafile = new Datafile();
        datafile.setName("MAME");
        datafile.setCategory(DatafileCategoryEnum.MAME.name());
        datafile.setVersion(this.build);
        for (MameMachine machine : this.machines) {
            Artifact game = new Artifact(machine.getName(), machine.getIsbios(), machine.getDescription(), machine.getYear(),
                    machine.getManufacturer(), machine.getCloneof(), machine.getRomof(), machine.getSampleof(), null);
            for (MameRom rom : machine.getRoms()) {
                game.addFile(new ArtifactFile(ArtifactFileTypeEnum.ROM.name(), rom.getName(), rom.getSize(), rom.getCrc(),
                        rom.getSha1(), null, rom.getStatus(), null, rom.getMerge(), rom.getRegion()));
            }
            for (MameDisk disk : machine.getDisks()) {
                game.addFile(new ArtifactFile(ArtifactFileTypeEnum.DISK.name(), disk.getName(), null, null,
                        disk.getSha1(), null, disk.getStatus(), null, disk.getMerge(), disk.getRegion()));
            }
            for (MameSample sample : machine.getSamples()) {
                game.addFile(new ArtifactFile(ArtifactFileTypeEnum.SAMPLE.name(), sample.getName()));
            }

            datafile.addArtifact(game);
        }
        return datafile;
    }

    @Override
    public String toFile() {
        return toString();
    }
}
