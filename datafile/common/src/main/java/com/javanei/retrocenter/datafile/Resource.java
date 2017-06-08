package com.javanei.retrocenter.datafile;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.javanei.retrocenter.clrmamepro.CMProResource;
import com.javanei.retrocenter.clrmamepro.CMProRom;

public class Resource implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String description;
    private String year;
    private String manufacturer;
    private Set<Rom> roms = new HashSet<>();

    public Resource() {
    }

    public Resource(String name, String description, String year, String manufacturer) {
        this.name = name;
        this.description = description;
        this.year = year;
        this.manufacturer = manufacturer;
    }

    public static Resource fromClrmamepro(CMProResource p) {
        Resource r = new Resource(p.getName(), p.getDescription(), p.getYear(), p.getManufacturer());
        for (CMProRom rom : p.getRoms()) {
            r.addRom(Rom.fromClrmamepro(rom));
        }
        return r;
    }

    public CMProResource toClrmamepro() {
        CMProResource r = new CMProResource(this.name, this.description, this.year, this.manufacturer);
        for (Rom rom : this.roms) {
            r.addRom(rom.toClrmamepro());
        }
        return r;
    }

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

    public Set<Rom> getRoms() {
        return roms;
    }

    public void setRoms(Set<Rom> roms) {
        this.roms = roms;
    }

    public void addRom(Rom rom) {
        if (this.roms.contains(rom))
            throw new IllegalArgumentException("Duplicated rom: " + rom.getName());
        this.roms.add(rom);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Resource resource = (Resource) o;
        return Objects.equals(name, resource.name) &&
                Objects.equals(description, resource.description) &&
                Objects.equals(year, resource.year) &&
                Objects.equals(manufacturer, resource.manufacturer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, year, manufacturer);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nresource (\n");
        if (this.name != null)
            sb.append("\t").append("name \"").append(this.name).append("\"\n");
        if (this.description != null)
            sb.append("\t").append("description \"").append(this.description).append("\"\n");
        if (this.year != null)
            sb.append("\t").append("year ").append(this.year).append("\n");
        if (this.manufacturer != null)
            sb.append("\t").append("manufacturer \"").append(this.manufacturer).append("\"\n");
        if (this.roms != null && !this.roms.isEmpty()) {
            for (Rom rom : this.roms) {
                sb.append(rom.toString());
            }
        }
        sb.append(")\n");
        return sb.toString();
    }
}
