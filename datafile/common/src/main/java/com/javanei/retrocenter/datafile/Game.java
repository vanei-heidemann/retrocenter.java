package com.javanei.retrocenter.datafile;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Game implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * logiqx.game.name, clrmamepro.game.name
     */
    private String name;
    /**
     * logiqx.game.description, clrmamepro.game.description
     */
    private String description;
    /**
     * logiqx.game.year, clrmamepro.game.year
     */
    private String year;
    /**
     * logiqx.game.manufacturer, clrmamepro.game.manufacturer
     */
    private String manufacturer;
    /**
     * logiqx.game.cloneof, clrmamepro.game.cloneof
     */
    private String cloneof;
    /**
     * logiqx.game.romof, clrmamepro.game.romof
     */
    private String romof;
    /**
     * logiqx.game.isbios
     */
    private String isbios = "no";

    /**
     * logiqx.game.comment
     */
    private String comment;
    /**
     * logiqx.game.sourcefile
     */
    private String sourcefile;
    /**
     * logiqx.game.sampleof
     */
    private String sampleof;
    /**
     * logiqx.game.board
     */
    private String board;
    /**
     * logiqx.game.rebuildto
     */
    private String rebuildto;

    private Set<Release> releases = new HashSet<>();
    private Set<Biosset> biossets = new HashSet<>();
    private Set<Rom> roms = new HashSet<>();
    private Set<Disk> disks = new HashSet<>();
    private Set<Sample> samples = new HashSet<>();
    private Set<Archive> archives = new HashSet<>();

    private static void appendTagIfNotNull(StringBuilder sb, String name, Object value) {
        if (value != null)
            sb.append("\t\t<").append(name).append(">").append(value).append("</").append(name).append(">\n");
    }

    private static void appendAttributeIfNotNull(StringBuilder sb, String name, Object value) {
        if (value != null)
            sb.append(" ").append(name).append("=\"").append(value).append("\"");
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

    public String getIsbios() {
        return isbios;
    }

    public void setIsbios(String isbios) {
        this.isbios = isbios;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getSourcefile() {
        return sourcefile;
    }

    public void setSourcefile(String sourcefile) {
        this.sourcefile = sourcefile;
    }

    public String getSampleof() {
        return sampleof;
    }

    public void setSampleof(String sampleof) {
        this.sampleof = sampleof;
    }

    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
    }

    public String getRebuildto() {
        return rebuildto;
    }

    public void setRebuildto(String rebuildto) {
        this.rebuildto = rebuildto;
    }

    public Set<Rom> getRoms() {
        return roms;
    }

    public void setRoms(Set<Rom> roms) {
        if (roms != null)
            this.roms = roms;
        else
            this.roms = new HashSet<>();
    }

    public void addRom(Rom rom) {
        if (this.roms.contains(rom))
            throw new IllegalArgumentException("Duplicated rom: " + rom.getName());
        this.roms.add(rom);
    }

    public Set<Disk> getDisks() {
        return disks;
    }

    public void setDisks(Set<Disk> disks) {
        if (disks != null)
            this.disks = disks;
        else
            this.disks = new HashSet<>();
    }

    public void addDisk(Disk disk) {
        if (this.disks.contains(disk))
            throw new IllegalArgumentException("Duplicated disk: " + disk.getName());
        this.disks.add(disk);
    }

    public Set<Release> getReleases() {
        return releases;
    }

    public void setReleases(Set<Release> releases) {
        if (releases != null)
            this.releases = releases;
        else
            this.releases = new HashSet<>();
    }

    public void addRelease(Release release) {
        if (this.releases.contains(release))
            throw new IllegalArgumentException("Duplicated release: " + release.getName() + "/" + release.getDate()
                    + "/" + release.getLanguage() + "/" + release.getRegion());
        this.releases.add(release);
    }

    public Set<Biosset> getBiossets() {
        return biossets;
    }

    public void setBiossets(Set<Biosset> biossets) {
        if (biossets != null)
            this.biossets = biossets;
        else
            this.biossets = new HashSet<>();
    }

    public void addBiosset(Biosset biosset) {
        if (this.biossets.contains(biosset))
            throw new IllegalArgumentException("Duplicated biosset: " + biosset.getName());
    }

    public Set<Sample> getSamples() {
        return samples;
    }

    public void setSamples(Set<Sample> samples) {
        if (samples != null)
            this.samples = samples;
        else
            this.samples = new HashSet<>();
    }

    public void addSample(Sample sample) {
        if (this.samples.contains(sample))
            throw new IllegalArgumentException("Duplicated sample: " + sample.getName());
    }

    public Set<Archive> getArchives() {
        return archives;
    }

    public void setArchives(Set<Archive> archives) {
        if (archives != null)
            this.archives = archives;
        else
            this.archives = new HashSet<>();
    }

    public void addArchive(Archive archive) {
        if (this.archives.contains(archive))
            throw new IllegalArgumentException("Duplicated archive: " + archive.getName());
        this.archives.add(archive);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Game game = (Game) o;
        return Objects.equals(name, game.name) &&
                Objects.equals(year, game.year) &&
                Objects.equals(manufacturer, game.manufacturer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, year, manufacturer);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\t<game");
        appendAttributeIfNotNull(sb, "name", this.name);
        appendAttributeIfNotNull(sb, "sourcefile", this.sourcefile);
        appendAttributeIfNotNull(sb, "isbios", this.isbios);
        appendAttributeIfNotNull(sb, "cloneof", this.cloneof);
        appendAttributeIfNotNull(sb, "romof", this.romof);
        appendAttributeIfNotNull(sb, "sampleof", this.sampleof);
        appendAttributeIfNotNull(sb, "board", this.board);
        appendAttributeIfNotNull(sb, "rebuildto", this.rebuildto);
        sb.append(">\n");
        appendTagIfNotNull(sb, "comment", comment);
        appendTagIfNotNull(sb, "description", description);
        appendTagIfNotNull(sb, "year", year);
        appendTagIfNotNull(sb, "manufacturer", manufacturer);
        for (Release release : this.releases) {
            sb.append(release.toString());
        }
        for (Biosset biosset : this.biossets) {
            sb.append(biosset.toString());
        }
        for (Rom rom : this.roms) {
            sb.append(rom.toString());
        }
        for (Disk disk : this.disks) {
            sb.append(disk.toString());
        }
        for (Sample sample : this.samples) {
            sb.append(sample.toString());
        }
        for (Archive archive : this.archives) {
            sb.append(archive.toString());
        }
        sb.append("\t</game>\n");
        return sb.toString();
    }
}
