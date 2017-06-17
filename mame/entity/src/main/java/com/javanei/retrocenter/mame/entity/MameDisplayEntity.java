package com.javanei.retrocenter.mame.entity;

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
@Table(name = "MAME_DISPLAY", indexes = {
        @Index(name = "MAME_DISPLAY_0001", unique = true, columnList = "MACHINE_ID,TAG,TYPE")
})
public class MameDisplayEntity implements Serializable, Comparable<MameDisplayEntity> {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MAME_DISPLAY_ID", nullable = false)
    private Long id;

    @Column(name = "TAG", length = 64, nullable = false)
    private String tag;

    @Column(name = "TYPE", length = 8, nullable = false)
    private String type;

    @Column(name = "ROTATE", length = 3, nullable = true)
    private String rotate;

    @Column(name = "FLIPX", length = 3, nullable = true)
    private String flipx;

    @Column(name = "WIDTH", nullable = true)
    private Integer width;

    @Column(name = "HEIGHT", nullable = true)
    private Integer height;

    @Column(name = "REFRESH", length = 16, nullable = true)
    private String refresh;

    @Column(name = "PIXCLOCK", nullable = true)
    private Integer pixclock;

    @Column(name = "HTOTAL", nullable = true)
    private Integer htotal;

    @Column(name = "HBEND", nullable = true)
    private Integer hbend;

    @Column(name = "HBSTART", nullable = true)
    private Integer hbstart;

    @Column(name = "VTOTAL", nullable = true)
    private Integer vtotal;

    @Column(name = "VBEND", nullable = true)
    private Integer vbend;

    @Column(name = "VBSTART", nullable = true)
    private Integer vbstart;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "MACHINE_ID")
    private MameMachineEntity machine;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRotate() {
        return rotate;
    }

    public void setRotate(String rotate) {
        this.rotate = rotate;
    }

    public String getFlipx() {
        return flipx;
    }

    public void setFlipx(String flipx) {
        this.flipx = flipx;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getRefresh() {
        return refresh;
    }

    public void setRefresh(String refresh) {
        this.refresh = refresh;
    }

    public Integer getPixclock() {
        return pixclock;
    }

    public void setPixclock(Integer pixclock) {
        this.pixclock = pixclock;
    }

    public Integer getHtotal() {
        return htotal;
    }

    public void setHtotal(Integer htotal) {
        this.htotal = htotal;
    }

    public Integer getHbend() {
        return hbend;
    }

    public void setHbend(Integer hbend) {
        this.hbend = hbend;
    }

    public Integer getHbstart() {
        return hbstart;
    }

    public void setHbstart(Integer hbstart) {
        this.hbstart = hbstart;
    }

    public Integer getVtotal() {
        return vtotal;
    }

    public void setVtotal(Integer vtotal) {
        this.vtotal = vtotal;
    }

    public Integer getVbend() {
        return vbend;
    }

    public void setVbend(Integer vbend) {
        this.vbend = vbend;
    }

    public Integer getVbstart() {
        return vbstart;
    }

    public void setVbstart(Integer vbstart) {
        this.vbstart = vbstart;
    }

    public MameMachineEntity getMachine() {
        return machine;
    }

    public void setMachine(MameMachineEntity machine) {
        this.machine = machine;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        MameDisplayEntity entity = (MameDisplayEntity) o;
        return Objects.equals(tag, entity.tag) &&
                Objects.equals(type, entity.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tag, type);
    }

    @Override
    public int compareTo(MameDisplayEntity o) {
        if (this == o)
            return 0;

        int i = 0;
        if (this.id != null && o.id == null)
            i = 1;
        else if (this.id == null && o.id != null)
            i = -1;
        else if (this.id != null)
            i = this.id.compareTo(o.id);
        if (i == 0)
            i = this.tag.compareTo(o.tag);
        if (i == 0) {
            i = this.type.compareTo(o.type);
        }
        return i;
    }
}
