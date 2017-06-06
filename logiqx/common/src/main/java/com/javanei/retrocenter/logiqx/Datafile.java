package com.javanei.retrocenter.logiqx;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * <!ELEMENT datafile (header?, game+)>
 */
public class Datafile implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * <!ATTLIST datafile build CDATA #IMPLIED>
     */
    private String build;

    /**
     * <!ATTLIST datafile debug (yes|no) "no">
     */
    private String debug = "no";

    private Header header;

    private Set<Game> games = new HashSet<>();

    public String getBuild() {
        return build;
    }

    public void setBuild(String build) {
        this.build = build;
    }

    public String getDebug() {
        return debug;
    }

    public void setDebug(String debug) {
        this.debug = debug;
    }

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
        this.games = games;
    }

    public void addGame(Game game) {
        this.games.add(game);
    }
}
