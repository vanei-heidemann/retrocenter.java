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

    private Set<Rom> roms = new HashSet<>();

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
            throw new IllegalArgumentException("Duplicated rom: " + rom.toString());
        this.roms.add(rom);
    }

    @Override
    public String toString() {
        return "Game{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", year='" + year + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", cloneof='" + cloneof + '\'' +
                ", romof='" + romof + '\'' +
                ", isbios='" + isbios + '\'' +
                ", comment='" + comment + '\'' +
                ", sourcefile='" + sourcefile + '\'' +
                ", sampleof='" + sampleof + '\'' +
                ", board='" + board + '\'' +
                ", rebuildto='" + rebuildto + '\'' +
                '}';
    }
}
