package com.javanei.retrocenter.clrmamepro.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "CMPRO_DATAFILE", indexes = {
        @Index(name = "CMPRO_DATAFILE_0001", unique = true, columnList = "NAME,CATEGORY,VERSION")
})
public class CMProDatafileEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CMPRO_DATAFILE_ID", nullable = false)
    private Long id;

    //TODO:
    private String name;
    private String description;
    private String category;
    private String version;
    private String author;
    private String homepage;
    private String url;
    private String forcemerging;
    private String forcezipping;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.REMOVE}, mappedBy = "datafile")
    private Set<CMProCustomFieldEntity> customFields = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.REMOVE}, mappedBy = "datafile")
    private Set<CMProGameEntity> games = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.REMOVE}, mappedBy = "datafile")
    private Set<CMProResourceEntity> resources = new HashSet<>();

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getForcemerging() {
        return forcemerging;
    }

    public void setForcemerging(String forcemerging) {
        this.forcemerging = forcemerging;
    }

    public String getForcezipping() {
        return forcezipping;
    }

    public void setForcezipping(String forcezipping) {
        this.forcezipping = forcezipping;
    }

    public Set<CMProCustomFieldEntity> getCustomFields() {
        return customFields;
    }

    public void setCustomFields(Set<CMProCustomFieldEntity> customFields) {
        this.customFields = customFields;
    }

    public Set<CMProGameEntity> getGames() {
        return games;
    }

    public void setGames(Set<CMProGameEntity> games) {
        this.games = games;
    }

    public Set<CMProResourceEntity> getResources() {
        return resources;
    }

    public void setResources(Set<CMProResourceEntity> resources) {
        this.resources = resources;
    }
}
