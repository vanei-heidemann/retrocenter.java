package com.javanei.retrocenter.tosec;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Tosec implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private TosecHeader header;
    private Set<TosecGame> games = new HashSet<>();
    
    public TosecHeader getHeader() {
        return this.header;
    }
    
    public void setHeader(TosecHeader header) {
        this.header = header;
    }
    
    public Set<TosecGame> getGames() {
        return this.games;
    }
    
    public void setGames(Set<TosecGame> games) {
        this.games = games;
    }
    
    public void addGame(TosecGame game) {
        this.games.add(game);
    }
}
