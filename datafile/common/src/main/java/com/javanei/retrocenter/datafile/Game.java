package com.javanei.retrocenter.datafile;

import java.io.Serializable;
import java.util.Objects;

public class Game implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * logiqx.name, clrmamepro.name
     */
    private String name;
    /**
     * logiqx.description, clrmamepro.description
     */
    private String description;
    /**
     * logiqx.year, clrmamepro.year
     */
    private String year;
    /**
     * logiqx.manufacturer, clrmamepro.manufacturer
     */
    private String manufacturer;
    /**
     * logiqx.cloneof, clrmamepro.cloneof
     */
    private String cloneof;
    /**
     * logiqx.romof, clrmamepro.romof
     */
    private String romof;
    /**
     * logiqx.isbios
     */
    private String isbios = "no";

    /**
     * logiqx.comment
     */
    private String comment;
    /**
     * logiqx.sourcefile
     */
    private String sourcefile;
    /**
     * logiqx.sampleof
     */
    private String sampleof;
    /**
     * logiqx.board
     */
    private String board;
    /**
     * logiqx.rebuildto
     */
    private String rebuildto;

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
