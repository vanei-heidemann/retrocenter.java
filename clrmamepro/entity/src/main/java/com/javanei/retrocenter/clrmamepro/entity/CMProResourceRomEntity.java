package com.javanei.retrocenter.clrmamepro.entity;

import java.io.Serializable;
import java.util.Objects;
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
import javax.persistence.Table;

@Entity
@Table(name = "CMPRO_RESOURCEROM", indexes = {
        @Index(name = "CMPRO_RESOURCEROM_0001", unique = true, columnList = "CMPRO_RESOURCE_ID,NAME,SIZE,CRC,SHA1,MD5,REGION,FLAGS")
})
public class CMProResourceRomEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CMPRO_RESOURCEROM_ID", nullable = false)
    private Long id;

    //TODO:
    private String name;
    private Long size;
    private String crc;
    private String sha1;
    private String md5;
    private String region;
    private String flags;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "CMPRO_RESOURCE_ID")
    private CMProResourceEntity resource;

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

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getCrc() {
        return crc;
    }

    public void setCrc(String crc) {
        this.crc = crc;
    }

    public String getSha1() {
        return sha1;
    }

    public void setSha1(String sha1) {
        this.sha1 = sha1;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getFlags() {
        return flags;
    }

    public void setFlags(String flags) {
        this.flags = flags;
    }

    public CMProResourceEntity getResource() {
        return resource;
    }

    public void setResource(CMProResourceEntity resource) {
        this.resource = resource;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CMProResourceRomEntity that = (CMProResourceRomEntity) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(size, that.size) &&
                Objects.equals(crc, that.crc) &&
                Objects.equals(sha1, that.sha1) &&
                Objects.equals(md5, that.md5) &&
                Objects.equals(region, that.region) &&
                Objects.equals(flags, that.flags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, size, crc, sha1, md5, region, flags);
    }
}
