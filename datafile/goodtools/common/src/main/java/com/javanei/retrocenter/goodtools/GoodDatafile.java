package com.javanei.retrocenter.goodtools;

import com.javanei.retrocenter.common.DatafileCatalogEnum;
import com.javanei.retrocenter.datafile.Datafile;
import com.javanei.retrocenter.datafile.DatafileArtifact;
import com.javanei.retrocenter.datafile.DatafileArtifactFile;
import com.javanei.retrocenter.datafile.DatafileObject;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class GoodDatafile implements DatafileObject, Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String version;
    private String author;
    private String date;
    private String comment;
    private Set<GoodDatafileRom> roms = new HashSet<>();

    public GoodDatafile() {
    }

    public GoodDatafile(String name, String version, String author) {
        this.name = name;
        this.version = version;
        this.author = author;
    }

    public GoodDatafile(String name, String version, String author, String date) {
        this.name = name;
        this.version = version;
        this.author = author;
        this.date = date;
    }

    public GoodDatafile(String name, String version, String author, String date, String comment) {
        this.name = name;
        this.version = version;
        this.author = author;
        this.date = date;
        this.comment = comment;
    }

    private static void appendXMLAttributeIfNotNull(StringBuilder sb, String name, Object value) {
        if (value != null) {
            sb.append(" ").append(name).append("=\"").append(value).append("\"");
        }
    }

    private static void appendXMLTagIfNotNull(StringBuilder sb, String name, Object value, int ident) {
        if (value != null) {
            for (int i = 0; i < ident; i++) {
                sb.append("\t");
            }
            sb.append("<").append(name).append(">").append(value).append("</").append(name).append(">\n");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Set<GoodDatafileRom> getRoms() {
        return roms;
    }

    public void setRoms(Set<GoodDatafileRom> roms) {
        this.roms = roms;
    }

    public boolean addRom(GoodDatafileRom rom) {
        return this.roms.add(rom);
    }

    public String getDescription() {
        return this.name + " " + this.version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoodDatafile that = (GoodDatafile) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return version != null ? version.equals(that.version) : that.version == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (version != null ? version.hashCode() : 0);
        return result;
    }

    @Override
    public Datafile toDatafile() {
        Datafile datafile = new Datafile(this.name, DatafileCatalogEnum.GoodSet.name(), this.version, this.name + " " + this.version,
                this.author, this.date, null, "http://cowering.blogspot.com/",
                "https://en.wikipedia.org/wiki/GoodTools", this.comment);
        for (GoodDatafileRom rom : this.roms) {
            DatafileArtifact artifact = new DatafileArtifact(rom.getName(), rom.getDescription(), null, null);
            DatafileArtifactFile file = new DatafileArtifactFile("ROM", rom.getName());
            file.setSize(rom.getSize());
            file.setSha1(rom.getSha1());
            file.setMd5(rom.getMd5());
            file.setCrc(rom.getCrc());
            artifact.addFile(file);
            datafile.addArtifact(artifact);
        }
        return datafile;
    }

    @Override
    public String toString() {
        return this.toFile();
    }

    @Override
    public String toFile() {
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\"?>\n");
        sb.append("<GoodTools");
        appendXMLAttributeIfNotNull(sb, "name", name);
        appendXMLAttributeIfNotNull(sb, "version", version);
        sb.append(">\n");
        appendXMLTagIfNotNull(sb, "author", author, 1);
        appendXMLTagIfNotNull(sb, "date", date, 1);
        appendXMLTagIfNotNull(sb, "comment", comment, 1);

        sb.append("\t<files>\n");
        for (GoodDatafileRom file : this.roms) {
            sb.append("\t\t<file");
            appendXMLAttributeIfNotNull(sb, "name", file.getName());
            appendXMLAttributeIfNotNull(sb, "size", file.getSize());
            appendXMLAttributeIfNotNull(sb, "crc", file.getCrc());
            appendXMLAttributeIfNotNull(sb, "sha1", file.getSha1());
            appendXMLAttributeIfNotNull(sb, "md5", file.getMd5());
            sb.append(" />\n");
        }
        sb.append("\t</files>\n");

        sb.append("</GoodTools>\n");
        return sb.toString();
    }
}
