package com.javanei.retrocenter.datafile;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
