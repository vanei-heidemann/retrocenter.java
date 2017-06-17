package com.javanei.retrocenter.clrmamepro.entity;

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
@Table(name = "CMPRO_RESOURCE", indexes = {
        @Index(name = "CMPRO_RESOURCE_0001", unique = true, columnList = "DATAFILE_ID,NAME")
})
public class CMProResourceEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CMPRO_RESOURCE_ID", nullable = false)
    private Long id;

    //TODO:
    private String name;
    private String description;
    private String year;
    private String manufacturer;
    //
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.REMOVE}, mappedBy = "resource")
    private Set<CMProResourceRomEntity> roms = new HashSet<>();

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "DATAFILE_ID")
    private CMProDatafileEntity datafile;

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

    public Set<CMProResourceRomEntity> getRoms() {
        return roms;
    }

    public void setRoms(Set<CMProResourceRomEntity> roms) {
        this.roms = roms;
    }

    public CMProDatafileEntity getDatafile() {
        return datafile;
    }

    public void setDatafile(CMProDatafileEntity datafile) {
        this.datafile = datafile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CMProResourceEntity that = (CMProResourceEntity) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
