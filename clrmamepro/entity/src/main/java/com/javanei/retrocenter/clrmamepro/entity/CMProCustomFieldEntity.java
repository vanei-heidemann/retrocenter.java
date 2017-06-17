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
@Table(name = "CMPRO_CUSTOMFIELD", indexes = {
        @Index(name = "CMPRO_CUSTOMFIELD_0001", unique = true, columnList = "CMPRO_DATAFILE_ID,KEY")
})
public class CMProCustomFieldEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CMPRO_CUSTOMFIELD_ID", nullable = false)
    private Long id;

    //TODO:
    private String key;
    private String value;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "CMPRO_DATAFILE_ID")
    private CMProDatafileEntity datafile;

    public CMProCustomFieldEntity() {
    }

    public CMProCustomFieldEntity(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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
        CMProCustomFieldEntity that = (CMProCustomFieldEntity) o;
        return Objects.equals(key, that.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }
}
