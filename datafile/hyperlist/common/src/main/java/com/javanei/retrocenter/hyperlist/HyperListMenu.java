package com.javanei.retrocenter.hyperlist;

import com.javanei.retrocenter.datafile.Datafile;
import com.javanei.retrocenter.datafile.DatafileObject;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * https://hyperlist.hyperspin-fe.com/
 */
public class HyperListMenu implements DatafileObject, Serializable {
    private static final long serialVersionUID = 1L;

    private HyperListHeader header;
    private Set<HyperListGame> games = new HashSet<>();

    private static void appendXMLAttribute(StringBuilder sb, String name, Object value) {
        sb.append(" ").append(name).append("=\"").append(value != null ? value : "").append("\"");
    }

    private static void appendXMLTag(StringBuilder sb, String name, Object value, int ident) {
        for (int i = 0; i < ident; i++) {
            sb.append("\t");
        }
        sb.append("<").append(name).append(">").append(value != null ? value : "").append("</").append(name).append(">\n");
    }

    public HyperListHeader getHeader() {
        return header;
    }

    public void setHeader(HyperListHeader header) {
        this.header = header;
    }

    public Set<HyperListGame> getGames() {
        return games;
    }

    public void setGames(Set<HyperListGame> games) {
        this.games = games;
    }

    public boolean addGame(HyperListGame game) {
        return this.games.add(game);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\"?>\n");
        sb.append("<menu>\n");
        if (this.header != null) {
            sb.append("\t<header>\n");
            appendXMLTag(sb, "listname", header.getListname(), 2);
            appendXMLTag(sb, "lastlistupdate", header.getLastlistupdate(), 2);
            appendXMLTag(sb, "listversion", header.getListversion(), 2);
            appendXMLTag(sb, "exporterversion", header.getExporterversion(), 2);
            sb.append("\t</header>\n");
        }
        if (this.games != null) {
            for (HyperListGame game : this.games) {
                sb.append("\t<game");
                appendXMLAttribute(sb, "name", game.getName());
                appendXMLAttribute(sb, "index", game.getIndex());
                appendXMLAttribute(sb, "image", game.getImage());
                sb.append(">\n");
                appendXMLTag(sb, "description", game.getDescription(), 2);
                appendXMLTag(sb, "cloneof", game.getCloneof(), 2);
                appendXMLTag(sb, "crc", game.getCrc(), 2);
                appendXMLTag(sb, "manufacturer", game.getManufacturer(), 2);
                appendXMLTag(sb, "year", game.getYear(), 2);
                appendXMLTag(sb, "genre", game.getGenre(), 2);
                appendXMLTag(sb, "rating", game.getRating(), 2);
                sb.append("\t</game>\n");
            }
        }
        sb.append("</menu>\n");
        return sb.toString();
    }

    @Override
    public Datafile toDatafile() {
        return null;
    }

    @Override
    public String toFile() {
        return toString();
    }
}
