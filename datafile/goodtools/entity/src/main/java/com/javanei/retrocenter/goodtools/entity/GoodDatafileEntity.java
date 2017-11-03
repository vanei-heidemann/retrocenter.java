package com.javanei.retrocenter.goodtools.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "GOOD_DATAFILE", indexes = {
        @Index(name = "GOOD_DATAFILE_0001", unique = true, columnList = "NAME,VERSION")
})
@NamedQueries({
        @NamedQuery(name = "GoodDatafileEntity.findByUniqueFull", query = "SELECT o from GoodDatafileEntity o WHERE name = :name AND o.version = :version"),
        @NamedQuery(name = "GoodDatafileEntity.findByUnique", query = "SELECT new GoodDatafileEntity(id, name, version, author, date, comment) from GoodDatafileEntity o WHERE name = :name AND o.version = :version")
})
public class GoodDatafileEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "DATAFILE_ID", nullable = false)
    private Long id;

    @Column(name = "NAME", length = 160, nullable = false)
    private String name;

    @Column(name = "VERSION", length = 64, nullable = false)
    private String version;

    @Column(name = "AUTHOR", length = 128, nullable = true)
    private String author;

    @Column(name = "DATE", length = 32, nullable = true)
    private String date;

    @Column(name = "COMMENT", length = 128, nullable = true)
    private String comment;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.REMOVE}, mappedBy = "datafile")
    private Set<GoodDatafileRomEntity> roms = new HashSet<>();

    public GoodDatafileEntity() {
    }

    public GoodDatafileEntity(Long id, String name, String version, String author, String date, String comment) {
        this.id = id;
        this.name = name;
        this.version = version;
        this.author = author;
        this.date = date;
        this.comment = comment;
    }

    public GoodDatafileEntity(String name, String version, String author, String date, String comment) {
        this.name = name;
        this.version = version;
        this.author = author;
        this.date = date;
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

    public Set<GoodDatafileRomEntity> getRoms() {
        return roms;
    }

    public void setRoms(Set<GoodDatafileRomEntity> roms) {
        this.roms = roms;
    }

    public boolean addRom(GoodDatafileRomEntity rom) {
        return this.roms.add(rom);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoodDatafileEntity that = (GoodDatafileEntity) o;

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
    public String toString() {
        return "GoodDatafileEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", version='" + version + '\'' +
                ", author='" + author + '\'' +
                ", date='" + date + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
