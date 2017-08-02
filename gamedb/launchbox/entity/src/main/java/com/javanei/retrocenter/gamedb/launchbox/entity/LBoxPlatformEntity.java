package com.javanei.retrocenter.gamedb.launchbox.entity;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "LBOX_PLATFORM")
public class LBoxPlatformEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PLATFORM_ID", nullable = false)
    private Long id;

    @Column(name = "NAME", length = 255, nullable = false, unique = true)
    private String name;

    @Column(name = "RELEASE_DATE", length = 32, nullable = true, unique = false)
    private String releaseDate;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "DEVELOPER_ID")
    private LBoxCompanyEntity developer;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "MANUFACTURER_ID")
    private LBoxCompanyEntity manufacturer;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "LBOX_PLATFORM_ALT_NAME",
            joinColumns = @JoinColumn(name = "PLATFORM_IDX",
                    foreignKey = @ForeignKey(name = "FK_LBOX_PLAT_ALT_NAME"),
                    unique = false)
    )
    @Column(name = "ALTERNATE_NAME", length = 255, nullable = false, unique = true)
    private Set<String> alternateNames = new HashSet<>();

    public LBoxPlatformEntity() {
    }

    public LBoxPlatformEntity(String name) {
        this.name = name;
    }

    public LBoxPlatformEntity(String name, String releaseDate) {
        this.name = name;
        this.releaseDate = releaseDate;
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

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public LBoxCompanyEntity getDeveloper() {
        return developer;
    }

    public void setDeveloper(LBoxCompanyEntity developer) {
        this.developer = developer;
    }

    public LBoxCompanyEntity getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(LBoxCompanyEntity manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Set<String> getAlternateNames() {
        return alternateNames;
    }

    public void setAlternateNames(Set<String> alternateNames) {
        this.alternateNames = alternateNames;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LBoxPlatformEntity that = (LBoxPlatformEntity) o;

        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return "LBoxPlatformEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", developer=" + developer +
                ", manufacturer=" + manufacturer +
                ", alternateNames=" + alternateNames +
                '}';
    }
}
