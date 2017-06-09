package com.javanei.retrocenter.datafile;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.javanei.retrocenter.clrmamepro.CMProDatafile;
import com.javanei.retrocenter.clrmamepro.CMProGame;
import com.javanei.retrocenter.clrmamepro.CMProResource;
import com.javanei.retrocenter.logiqx.LogiqxDatafile;
import com.javanei.retrocenter.logiqx.LogiqxGame;

public class Datafile implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * logiqx.build
     * logiqx.debug
     */
    private Map<String, String> customAttributes = new HashMap<>();
    private Header header;
    private Set<Game> games = new HashSet<>();
    private Set<Resource> resources = new HashSet<>();

    public Datafile() {
    }

    public static Datafile fromLogiqx(LogiqxDatafile p) {
        Datafile r = new Datafile();
        r.setHeader(Header.fromLogiqx(p.getHeader()));
        for (LogiqxGame game : p.getGames()) {
            r.addGame(Game.fromLogiqx(game));
        }
        if (p.getBuild() != null)
            r.addCustomAttribute("build", p.getBuild());
        if (p.getDebug() != null)
            r.addCustomAttribute("debug", p.getDebug());
        return r;
    }

    public static Datafile fromClrmamepro(CMProDatafile p) {
        Datafile r = new Datafile();
        r.setHeader(Header.fromClrmamepro(p.getHeader()));
        for (CMProGame game : p.getGames()) {
            r.addGame(Game.fromClrmamepro(game));
        }
        for (CMProResource resource : p.getResources()) {
            r.addResource(Resource.fromClrmamepro(resource));
        }
        return r;
    }

    public LogiqxDatafile toLogiqx() {
        LogiqxDatafile r = new LogiqxDatafile(this.customAttributes.get("build"));
        if (this.customAttributes.get("debug") != null)
            r.setDebug(this.customAttributes.get("debug"));
        if (this.header != null)
            r.setHeader(this.header.toLogiqx());
        for (Game game : this.games) {
            r.addGame(game.toLogiqx());
        }
        return r;
    }

    public CMProDatafile toClrmamepro() {
        CMProDatafile r = new CMProDatafile();
        if (this.header != null)
            r.setHeader(this.header.toClrmamepro());
        for (Game game : this.games) {
            r.addGame(game.toClrmamepro());
        }
        for (Resource resource : this.resources) {
            r.addResource(resource.toClrmamepro());
        }
        return r;
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

    public Set<Resource> getResources() {
        return resources;
    }

    public void setResources(Set<Resource> resources) {
        this.resources = resources;
    }

    public void addResource(Resource resource) {
        if (this.resources.contains(resource))
            throw new IllegalArgumentException("Duplicated resource: " + resource.getName());
        this.resources.add(resource);
    }

    public Map<String, String> getCustomAttributes() {
        return customAttributes;
    }

    public String getCustomAttribute(String key) {
        return this.customAttributes.get(key);
    }

    public String getCustomAttribute(String key) {
        return this.customAttributes.get(key);
    }

    public void setCustomAttributes(Map<String, String> customAttributes) {
        this.customAttributes = customAttributes;
    }

    public void addCustomAttribute(String key, String value) {
        if (this.customAttributes.containsKey(key))
            throw new IllegalArgumentException("Duplicated custom attribute: " + key);
        this.customAttributes.put(key, value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n")
                .append("<!DOCTYPE datafile PUBLIC \"-//Logiqx//DTD ROM Management Datafile//EN\" \"http://www.logiqx.com/Dats/datafile.dtd\">\n\n")
                .append("<datafile");
        for (String key : this.customAttributes.keySet()) {
            sb.append(" ").append(key).append("=\"").append(this.customAttributes.get(key)).append("\"");
        }
        sb.append(">\n");

        if (this.header != null) {
            sb.append(this.header.toString());
        }

        for (Game game : this.games) {
            sb.append(game.toString());
        }
        for (Resource resource : this.resources) {
            sb.append(resource.toString());
        }

        sb.append("<datafile>\n");
        return sb.toString();
    }
}
