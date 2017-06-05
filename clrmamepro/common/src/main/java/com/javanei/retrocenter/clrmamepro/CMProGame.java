package com.javanei.retrocenter.clrmamepro;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class CMProGame implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String description;
    private String year;
    private String manufacturer;
    //
    private String cloneof;
    private String romof;
    //
    private Set<CMProRom> roms = new HashSet<>();
    private Set<CMProDisk> disks = new HashSet<>();
    private Set<String> sampleof = new HashSet<>();
    private Set<String> samples = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getCloneof() {
        return cloneof;
    }

    public void setCloneof(String cloneof) {
        this.cloneof = cloneof;
    }

    public String getRomof() {
        return romof;
    }

    public void setRomof(String romof) {
        this.romof = romof;
    }

    public Set<CMProRom> getRoms() {
        return roms;
    }

    public void setRoms(Set<CMProRom> roms) {
        this.roms = roms;
    }

    public Set<CMProDisk> getDisks() {
        return disks;
    }

    public Set<String> getSamples() {
        return samples;
    }

    public void setSamples(Set<String> samples) {
        this.samples = samples;
    }

    public void setDisks(Set<CMProDisk> disks) {
        this.disks = disks;
    }

    public Set<String> getSampleof() {
        return sampleof;
    }

    public void setSampleof(Set<String> sampleof) {
        this.sampleof = sampleof;
    }

    public void addRom(CMProRom rom) {
        this.roms.add(rom);
    }

    public void addDisk(CMProDisk disk) {
        this.disks.add(disk);
    }

    public void addSample(String sample) {
        this.samples.add(sample);
    }

    public void addSampleOf(String sampleof) {
        this.sampleof.add(sampleof);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CMProGame game = (CMProGame) o;
        return Objects.equals(name, game.name) &&
                Objects.equals(description, game.description) &&
                Objects.equals(year, game.year) &&
                Objects.equals(manufacturer, game.manufacturer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, year, manufacturer);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\ngame (\n");
        if (this.name != null) sb.append("\t").append("name \"").append(this.name).append("\"\n");
        if (this.description != null) sb.append("\t").append("description \"").append(this.description).append("\"\n");
        if (this.year != null) sb.append("\t").append("year ").append(this.year).append("\n");
        if (this.manufacturer != null)
            sb.append("\t").append("manufacturer \"").append(this.manufacturer).append("\"\n");
        if (this.cloneof != null) sb.append("\t").append("cloneof \"").append(this.cloneof).append("\"\n");
        if (this.romof != null) sb.append("\t").append("romof \"").append(this.romof).append("\"\n");
        if (this.roms != null && !this.roms.isEmpty()) {
            for (CMProRom rom : this.roms) {
                sb.append(rom.toString());
            }
        }
        if (this.disks != null && !this.disks.isEmpty()) {
            for (CMProDisk disk : this.disks) {
                sb.append(disk.toString());
            }
        }
        if (this.sampleof != null) {
            for (String s : this.sampleof) {
                sb.append("\t").append("sampleof \"").append(s).append("\"\n");
            }
        }
        if (this.samples != null && !this.samples.isEmpty()) {
            for (String sample : this.samples) {
                sb.append(sample);
            }
        }
        sb.append(")\n");
        return sb.toString();
    }
}
