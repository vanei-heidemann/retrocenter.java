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
@Table(name = "MAME_RAMOPTION", indexes = {
        @Index(name = "MAME_RAMOPTION_0001", unique = false, columnList = "MACHINE_ID,CONTENT,ISDEFAULT")
})
//WARN: Parece haver duplicidade!
public class MameRamoptionEntity implements Serializable, Comparable<MameRamoptionEntity> {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MAME_RAMOPTION_ID", nullable = false)
    private Long id;

    @Column(name = "ISDEFAULT", nullable = true)
    private Integer _default;

    @Column(name = "CONTENT", nullable = true)
    private Long content;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "MACHINE_ID")
    private MameMachineEntity machine;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDefault() {
        return _default;
    }

    public void setDefault(Integer _default) {
        this._default = _default;
    }

    public Long getContent() {
        return content;
    }

    public void setContent(Long content) {
        this.content = content;
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
        MameRamoptionEntity entity = (MameRamoptionEntity) o;
        return Objects.equals(_default, entity._default) &&
                Objects.equals(content, entity.content) &&
                Objects.equals(id, entity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_default, content, id);
    }

    @Override
    public int compareTo(MameRamoptionEntity o) {
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
            i = this.content.compareTo(o.content);
        if (i == 0) {
            if (this._default != null && o._default == null)
                i = 1;
            else if (this._default == null && o._default != null)
                i = -1;
            else if (this._default != null)
                i = this._default.compareTo(o._default);
        }
        return i;
    }
}
