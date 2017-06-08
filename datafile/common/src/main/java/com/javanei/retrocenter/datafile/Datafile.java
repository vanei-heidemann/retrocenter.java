package com.javanei.retrocenter.datafile;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Datafile implements Serializable {
    private static final long serialVersionUID = 1L;

    private Header header;
    private Set<Game> games = new HashSet<>();

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        if (games != null)
            this.games = games;
        else
            games = new HashSet<>();
    }

    public void addGame(Game game) {
        if (this.games.contains(game)) {
            throw new IllegalArgumentException("Duplicated game: " + game.getName());
        }
        this.games.add(game);
    }

    @Override
    public String toString() {
        return "Datafile{" +
                "header=" + header +
                ", games=" + games +
                '}';
    }
}
