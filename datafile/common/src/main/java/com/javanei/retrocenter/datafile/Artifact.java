package com.javanei.retrocenter.datafile;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Artifact implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * logiqx.game.name, clrmamepro.game.name
     */
    private String name;
    /**
     * logiqx.game.isbios
     */
    private String isbios = "no";
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
     * logiqx.game.sampleof
     */
    private String sampleof;
    /**
     * logiqx.game.comment
     */
    private String comment;

    private Set<ArtifactFile> files = new HashSet<>();

    private Set<Release> releases = new HashSet<>();

    public Artifact() {
    }

    public Artifact(String name, String isbios, String description, String year, String manufacturer, String cloneof,
                    String romof, String sampleof, String comment) {
        this.name = name;
        this.isbios = isbios;
        this.description = description;
        this.year = year;
        this.manufacturer = manufacturer;
        this.cloneof = cloneof;
        this.romof = romof;
        this.sampleof = sampleof;
        this.comment = comment;
    }

    /*
    public LogiqxGame toLogiqx() {
        LogiqxGame r = new LogiqxGame(name, null, isbios, cloneof, romof, sampleof, null, null, comment, description,
                year, manufacturer);
        for (Release release : this.releases) {
            r.addRelease(release.toLogiqx());
        }
        for (ArtifactFile file : this.files) {
            if (file.getType().equals(ArtifactFileTypeEnum.ROM.name())) {
                r.addRom(file.toLogiqxRom());
            } else if (file.getType().equals(ArtifactFileTypeEnum.DISK.name())) {
                r.addDisk(file.toLogiqxDisk());
            } else if (file.getType().equals(ArtifactFileTypeEnum.SAMPLE.name())) {
                r.addSample(file.toLogiqxSample());
            }
        }
        return r;
    }
    */

    /*
    public CMProGame toCMPro() {
        CMProGame r = new CMProGame(this.name, this.description, this.year, this.manufacturer, this.cloneof, this.romof);
        for (ArtifactFile file : this.files) {
            if (file.getType().equals(ArtifactFileTypeEnum.ROM.name())) {
                r.addRom(file.toCMProRom());
            } else if (file.getType().equals(ArtifactFileTypeEnum.DISK.name())) {
                r.addDisk(file.toCMProDisk());
            }
        }
        if (this.sampleof != null) {
            StringTokenizer st = new StringTokenizer(this.sampleof, ",");
            while (st.hasMoreTokens()) {
                r.addSampleOf(st.nextToken());
            }
        }
        return r;
    }
    */

    public boolean addFile(ArtifactFile file) {
        return this.files.add(file);
    }

    public boolean addRelease(Release release) {
        return this.releases.add(release);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsbios() {
        return isbios;
    }

    public void setIsbios(String isbios) {
        this.isbios = isbios;
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

    public String getSampleof() {
        return sampleof;
    }

    public void setSampleof(String sampleof) {
        this.sampleof = sampleof;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Set<ArtifactFile> getFiles() {
        return files;
    }

    public void setFiles(Set<ArtifactFile> files) {
        this.files = files;
    }

    public Set<Release> getReleases() {
        return releases;
    }

    public void setReleases(Set<Release> releases) {
        this.releases = releases;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Artifact game = (Artifact) o;
        return Objects.equals(name, game.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
