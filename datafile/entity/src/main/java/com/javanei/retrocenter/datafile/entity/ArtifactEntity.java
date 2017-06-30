package com.javanei.retrocenter.datafile.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "DATAFILE_ARTIFACT", indexes = {
        @Index(name = "DATAFILE_ARTIFACT_0001", unique = true, columnList = "DATAFILE_ID,NAME")
})
public class ArtifactEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ARTIFACT_ID", nullable = false)
    private Long id;

    @Column(name = "NAME", length = 255, nullable = false)
    private String name;

    @Column(name = "ISBIOS", length = 3, nullable = true)
    private String isbios;

    @Column(name = "DESCRIPTION", length = 255, nullable = true)
    private String description;

    @Column(name = "YEAR", length = 32, nullable = true)
    private String year;

    @Column(name = "MANUFACTURER", length = 255, nullable = true)
    private String manufacturer;

    @Column(name = "CLONEOF", length = 255, nullable = true)
    private String cloneof;

    @Column(name = "ROMOF", length = 255, nullable = true)
    private String romof;

    @Column(name = "SAMPLEOF", length = 255, nullable = true)
    private String sampleof;

    @Column(name = "COMMENT", length = 255, nullable = true)
    private String comment;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.REMOVE}, mappedBy = "artifact")
    private Set<ArtifactFileEntity> files = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.REMOVE}, mappedBy = "artifact")
    private Set<ReleaseEntity> releases = new HashSet<>();

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "DATAFILE_ID")
    private DatafileEntity datafile;

    public ArtifactEntity() {
    }

    public ArtifactEntity(Long id) {
        this.id = id;
    }

    public ArtifactEntity(String name, String isbios, String description, String year, String manufacturer, String cloneof, String romof, String sampleof, String comment) {
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

    public ArtifactEntity(Long id, String name, String isbios, String description, String year, String manufacturer, String cloneof, String romof, String sampleof, String comment) {
        this.id = id;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Set<ArtifactFileEntity> getFiles() {
        return files;
    }

    public void setFiles(Set<ArtifactFileEntity> files) {
        this.files = files;
    }

    public Set<ReleaseEntity> getReleases() {
        return releases;
    }

    public void setReleases(Set<ReleaseEntity> releases) {
        this.releases = releases;
    }

    public DatafileEntity getDatafile() {
        return datafile;
    }

    public void setDatafile(DatafileEntity datafile) {
        this.datafile = datafile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArtifactEntity that = (ArtifactEntity) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
