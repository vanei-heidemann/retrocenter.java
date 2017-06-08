package com.javanei.retrocenter.clrmamepro;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * http://www.logiqx.com/DatFAQs/CMPro.php
 */
public class CMProDatafile implements Serializable {
    private static final long serialVersionUID = 1L;
    private CMProHeader header;
    private Set<CMProGame> games = new HashSet<>();
    private Set<CMProResource> resources = new HashSet<>();

    public CMProDatafile() {
    }

    public CMProDatafile(CMProHeader header) {
        this.header = header;
    }

    public CMProHeader getHeader() {
        return header;
    }

    public void setHeader(CMProHeader header) {
        this.header = header;
    }

    public Set<CMProGame> getGames() {
        return games;
    }

    public void setGames(Set<CMProGame> games) {
        this.games = games;
    }

    public Set<CMProResource> getResources() {
        return resources;
    }

    public void setResources(Set<CMProResource> resources) {
        this.resources = resources;
    }

    public void addGame(CMProGame game) {
        this.games.add(game);
    }

    public void addResource(CMProResource resource) {
        this.resources.add(resource);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.header.toString());
        if (this.games != null) {
            for (CMProGame game : this.games) {
                sb.append(game);
            }
        }
        if (this.resources != null) {
            for (CMProResource resource : this.resources) {
                sb.append(resource);
            }
        }
        return sb.toString();
    }
}
