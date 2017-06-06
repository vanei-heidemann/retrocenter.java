package com.javanei.retrocenter.logiqx;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * <!ELEMENT game (comment*, description, year?, manufacturer?, release*, biosset*, rom*, disk*, sample*, archive*)>
 */
public class Game implements Serializable {
    private static final long serialVersionUID = 1L;

    // ////////// ATTLIST

    /**
     * <!ATTLIST game name CDATA #REQUIRED>
     */
    private String name;

    /**
     * <!ATTLIST game sourcefile CDATA #IMPLIED>
     */
    private String sourcefile;

    /**
     * <!ATTLIST game isbios (yes|no) "no">
     */
    private String isbios = "no";

    /**
     * <!ATTLIST game cloneof CDATA #IMPLIED>
     */
    private String cloneof;

    /**
     * <!ATTLIST game romof CDATA #IMPLIED>
     */
    private String romof;

    /**
     * <!ATTLIST game sampleof CDATA #IMPLIED>
     */
    private String sampleof;

    /**
     * <!ATTLIST game board CDATA #IMPLIED>
     */
    private String board;

    /**
     * <!ATTLIST game rebuildto CDATA #IMPLIED>
     */
    private String rebuildto;

    // ////////// ELEMENT

    /**
     * <!ELEMENT comment (#PCDATA)>
     */
    private String comment;

    /**
     * <!ELEMENT description (#PCDATA)>
     */
    private String description;

    /**
     * <!ELEMENT year (#PCDATA)>
     */
    private String year;

    /**
     * <!ELEMENT manufacturer (#PCDATA)>
     */
    private String manufacturer;

    //

    private Set<Release> releases = new HashSet<>();

    private Set<Biosset> biossets = new HashSet<>();

    private Set<Rom> roms = new HashSet<>();

    private Set<Disk> disks = new HashSet<>();

    private Set<Sample> samples = new HashSet<>();

    private Set<Archive> archives = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSourcefile() {
        return sourcefile;
    }

    public void setSourcefile(String sourcefile) {
        this.sourcefile = sourcefile;
    }

    public String getIsbios() {
        return isbios;
    }

    public void setIsbios(String isbios) {
        this.isbios = isbios;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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

    public Set<Release> getReleases() {
        return releases;
    }

    public void setReleases(Set<Release> releases) {
        this.releases = releases;
    }

    public void addRelease(Release release) {
        this.releases.add(release);
    }

    public Set<Biosset> getBiossets() {
        return biossets;
    }

    public void setBiossets(Set<Biosset> biossets) {
        this.biossets = biossets;
    }

    public void addBiosset(Biosset biosset) {
        this.biossets.add(biosset);
    }

    public Set<Rom> getRoms() {
        return roms;
    }

    public void setRoms(Set<Rom> roms) {
        this.roms = roms;
    }

    public void addRom(Rom rom) {
        this.roms.add(rom);
    }

    public Set<Disk> getDisks() {
        return disks;
    }

    public void setDisks(Set<Disk> disks) {
        this.disks = disks;
    }

    public void addDisk(Disk disk) {
        this.disks.add(disk);
    }

    public Set<Sample> getSamples() {
        return samples;
    }

    public void setSamples(Set<Sample> samples) {
        this.samples = samples;
    }

    public void addSample(Sample sample) {
        this.samples.add(sample);
    }

    public Set<Archive> getArchives() {
        return archives;
    }

    public void setArchives(Set<Archive> archives) {
        this.archives = archives;
    }

    public void addArchive(Archive archive) {
        this.archives.add(archive);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return Objects.equals(name, game.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
