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
@Table(name = "LBOX_DATAFILE_GENRE",
        uniqueConstraints = @UniqueConstraint(columnNames = {"DATAFILE_ID", "GENRE_ID"}))
public class LBoxDatafileGenreEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "DATAFILE_GENRE_ID", nullable = false)
    private Long id;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "DATAFILE_ID")
    private LBoxDatafileEntity datafile;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "GENRE_ID")
    private LBoxGenreEntity genre;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LBoxDatafileEntity getDatafile() {
        return datafile;
    }

    public void setDatafile(LBoxDatafileEntity datafile) {
        this.datafile = datafile;
    }

    public LBoxGenreEntity getGenre() {
        return genre;
    }

    public void setGenre(LBoxGenreEntity genre) {
        this.genre = genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LBoxDatafileGenreEntity that = (LBoxDatafileGenreEntity) o;

        if (!datafile.equals(that.datafile)) return false;
        return genre.equals(that.genre);
    }

    @Override
    public int hashCode() {
        int result = datafile.hashCode();
        result = 31 * result + genre.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "LBoxDatafileGenreEntity{" +
                "id=" + id +
                ", genre=" + genre +
                '}';
    }
}
