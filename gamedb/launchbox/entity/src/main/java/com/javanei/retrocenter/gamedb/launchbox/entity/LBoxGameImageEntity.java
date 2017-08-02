package com.javanei.retrocenter.gamedb.launchbox.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.io.Serializable;

@Entity
@Table(name = "LBOX_GAME_IMAGE", uniqueConstraints =
        {@UniqueConstraint(columnNames =
                {"GAME_ID", "IMAGE_TYPE", "FILE_NAME", "CRC32", "REGION_ID"})
        })
public class LBoxGameImageEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "GAME_IMAGE_ID", nullable = false)
    private Long id;

    @Column(name = "FILE_NAME", length = 255, nullable = false)
    private String fileName;

    @Column(name = "IMAGE_TYPE", length = 64, nullable = false)
    private String type;

    @Column(name = "CRC32", length = 10, nullable = false)
    private String crc32;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "REGION_ID")
    private LBoxRegionEntity region;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "GAME_ID")
    private LBoxGameEntity game;

    public LBoxGameImageEntity() {
    }

    public LBoxGameImageEntity(LBoxGameEntity game) {
        this.game = game;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LBoxGameImageEntity that = (LBoxGameImageEntity) o;

        if (!fileName.equals(that.fileName)) return false;
        if (!type.equals(that.type)) return false;
        if (crc32 != null ? !crc32.equals(that.crc32) : that.crc32 != null) return false;
        return region != null ? region.equals(that.region) : that.region == null;
    }

    @Override
    public int hashCode() {
        int result = fileName.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + (crc32 != null ? crc32.hashCode() : 0);
        result = 31 * result + (region != null ? region.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "LBoxGameImageEntity{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", type='" + type + '\'' +
                ", crc32='" + crc32 + '\'' +
                ", region=" + region +
                '}';
    }
}
