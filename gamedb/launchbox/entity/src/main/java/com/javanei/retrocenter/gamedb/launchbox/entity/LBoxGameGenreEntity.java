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
@Table(name = "LBOX_GAME_GENRE",
        uniqueConstraints = @UniqueConstraint(columnNames = {"GAME_ID", "GENRE_ID"}))
public class LBoxGameGenreEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "GAME_GENRE_ID", nullable = false)
    private Long id;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "GAME_ID")
    private LBoxGameEntity game;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "GENRE_ID")
    private LBoxGenreEntity genre;

    public LBoxGameGenreEntity() {
    }

    public LBoxGameGenreEntity(LBoxGameEntity game, LBoxGenreEntity genre) {
        this.game = game;
        this.genre = genre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LBoxGameEntity getGame() {
        return game;
    }

    public void setGame(LBoxGameEntity game) {
        this.game = game;
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

        LBoxGameGenreEntity that = (LBoxGameGenreEntity) o;

        if (!game.equals(that.game)) return false;
        return genre.equals(that.genre);
    }

    @Override
    public int hashCode() {
        int result = game.hashCode();
        result = 31 * result + genre.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "LBoxGameGenreEntity{" +
                "id=" + id +
                ", genre=" + genre +
                '}';
    }
}
