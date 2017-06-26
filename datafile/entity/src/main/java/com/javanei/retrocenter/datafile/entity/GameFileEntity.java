package com.javanei.retrocenter.datafile.entity;

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
@Table(name = "DATAFILE_GAMEFILE", indexes = {
        @Index(name = "DATAFILE_GAMEFILE_0001", unique = true, columnList = "GAME_ID,FILE_TYPE,NAME")
})
public class GameFileEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "GAMEFILE_ID", nullable = false)
    private Long id;

    @Column(name = "FILE_TYPE", length = 16, nullable = false)
    private String type;

    @Column(name = "NAME", length = 255, nullable = false)
    private String name;

    @Column(name = "SIZE", nullable = true)
    private Long size;

    @Column(name = "CRC", length = 8, nullable = true)
    private String crc;

    @Column(name = "SHA1", length = 40, nullable = true)
    private String sha1;

    @Column(name = "MD5", length = 32, nullable = true)
    private String md5;

    @Column(name = "STATUS", length = 16, nullable = true)
    private String status;

    @Column(name = "DATE", length = 32, nullable = true)
    private String date;

    @Column(name = "MERGE", length = 32, nullable = true)
    private String merge;

    @Column(name = "REGION", length = 128, nullable = true)
    private String region;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "GAME_ID")
    private GameEntity game;

    public GameFileEntity() {
    }

    public GameFileEntity(Long id) {
        this.id = id;
    }

    public GameFileEntity(String type, String name, Long size, String crc, String sha1, String md5, String status, String date, String merge, String region) {
        this.type = type;
        this.name = name;
        this.size = size;
        this.crc = crc;
        this.sha1 = sha1;
        this.md5 = md5;
        this.status = status;
        this.date = date;
        this.merge = merge;
        this.region = region;
    }

    public GameFileEntity(Long id, String type, String name, Long size, String crc, String sha1, String md5, String status, String date, String merge, String region) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.size = size;
        this.crc = crc;
        this.sha1 = sha1;
        this.md5 = md5;
        this.status = status;
        this.date = date;
        this.merge = merge;
        this.region = region;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMerge() {
        return merge;
    }

    public void setMerge(String merge) {
        this.merge = merge;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public GameEntity getGame() {
        return game;
    }

    public void setGame(GameEntity game) {
        this.game = game;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameFileEntity that = (GameFileEntity) o;
        return Objects.equals(type, that.type) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, name);
    }
}
