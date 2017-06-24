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
@Table(name = "DAT_GAME", indexes = {
        @Index(name = "DAT_GAME_0001", unique = true, columnList = "DATAFILE_ID,NAME")
})
public class GameEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "GAME_ID", nullable = false)
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

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.REMOVE}, mappedBy = "game")
    private Set<GameFileEntity> files = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.REMOVE}, mappedBy = "game")
    private Set<ReleaseEntity> releases = new HashSet<>();

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "DATAFILE_ID")
    private DatafileEntity datafile;

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

    public Set<GameFileEntity> getFiles() {
        return files;
    }

    public void setFiles(Set<GameFileEntity> files) {
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
        GameEntity that = (GameEntity) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
