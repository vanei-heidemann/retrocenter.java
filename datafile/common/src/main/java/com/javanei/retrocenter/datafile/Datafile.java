package com.javanei.retrocenter.datafile;

import com.javanei.retrocenter.common.DatafileCategoryEnum;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Datafile implements DatafileObject, Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * clrmamepro.header.name, logiqx.header.name
     */
    private String name;
    /**
     * clrmamepro.header.category, logiqx.header.category
     */
    private String category;
    /**
     * clrmamepro.header.version, logiqx.header.version
     */
    private String version;
    /**
     * clrmamepro.header.description, logiqx.header.description
     */
    private String description;
    /**
     * clrmamepro.header.author, logiqx.header.author
     */
    private String author;
    /**
     * logiqx.header.date
     */
    private String date;
    /**
     * logiqx.header.email
     */
    private String email;
    /**
     * clrmamepro.header.homepage, logiqx.header.homepage
     */
    private String homepage;
    /**
     * clrmamepro.header.url, logiqx.header.url
     */
    private String url;
    /**
     * logiqx.header.comment
     */
    private String comment;

    private Set<Game> games = new HashSet<>();

    public Datafile() {
    }

    public Datafile(String name) {
        this.name = name;
    }

    public Datafile(String name, String category, String version, String description, String author, String date,
                    String email, String homepage, String url, String comment) {
        this.name = name;
        this.setCategory(category);
        this.version = version;
        this.description = description;
        this.author = author;
        this.date = date;
        this.email = email;
        this.homepage = homepage;
        this.url = url;
        this.comment = comment;
    }

    private static void appendXMLAttributeIfNotNull(StringBuilder sb, String name, Object value) {
        if (value != null) {
            sb.append(" ").append(name).append("=\"").append(value).append("\"");
        }
    }

    private static void appendXMLTagIfNotNull(StringBuilder sb, String name, Object value, int ident) {
        if (value != null) {
            for (int i = 0; i < ident; i++) {
                sb.append("\t");
            }
            sb.append("<").append(name).append(">").append(value).append("</").append(name).append(">\n");
        }
    }

    public boolean addGame(Game game) {
        return this.games.add(game);
    }

    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        if (category == null) {
            this.category = null;
        } else if (category.toLowerCase().equals("no-intro") || category.toLowerCase().equals("nointro")) {
            this.category = DatafileCategoryEnum.NoIntro.name();
        } else if (category.toLowerCase().equals("tosec")) {
            this.category = DatafileCategoryEnum.TOSEC.name();
        } else if (category.toUpperCase().equals("MAME")) {
            this.category = DatafileCategoryEnum.MAME.name();
        } else {
            throw new IllegalArgumentException("Invalid category value: '" + category + "'");
        }
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Datafile datafile = (Datafile) o;
        return Objects.equals(name, datafile.name) &&
                Objects.equals(category, datafile.category) &&
                Objects.equals(version, datafile.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, category, version);
    }

    @Override
    public Datafile toDatafile() {
        return this;
    }

    @Override
    public String toFile() {
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\"?>\n");
        sb.append("<datafile");
        appendXMLAttributeIfNotNull(sb, "name", name);
        appendXMLAttributeIfNotNull(sb, "category", category);
        appendXMLAttributeIfNotNull(sb, "version", version);
        sb.append(">\n");
        appendXMLTagIfNotNull(sb, "description", description, 1);
        appendXMLTagIfNotNull(sb, "author", author, 1);
        appendXMLTagIfNotNull(sb, "date", date, 1);
        appendXMLTagIfNotNull(sb, "email", email, 1);
        appendXMLTagIfNotNull(sb, "homepage", homepage, 1);
        appendXMLTagIfNotNull(sb, "url", url, 1);
        appendXMLTagIfNotNull(sb, "comment", comment, 1);

        for (Game game : this.games) {
            sb.append("\t<game");
            appendXMLAttributeIfNotNull(sb, "name", game.getName());
            appendXMLAttributeIfNotNull(sb, "isbios", game.getIsbios());
            sb.append(">\n");
            appendXMLTagIfNotNull(sb, "description", game.getDescription(), 2);
            appendXMLTagIfNotNull(sb, "year", game.getYear(), 2);
            appendXMLTagIfNotNull(sb, "manufacturer", game.getManufacturer(), 2);
            appendXMLTagIfNotNull(sb, "cloneof", game.getCloneof(), 2);
            appendXMLTagIfNotNull(sb, "romof", game.getRomof(), 2);
            appendXMLTagIfNotNull(sb, "sampleof", game.getSampleof(), 2);
            appendXMLTagIfNotNull(sb, "comment", game.getComment(), 2);

            for (GameFile file : game.getFiles()) {
                sb.append("\t\t<file");
                appendXMLAttributeIfNotNull(sb, "name", file.getName());
                appendXMLAttributeIfNotNull(sb, "type", file.getType());
                sb.append(">\n");
                appendXMLTagIfNotNull(sb, "size", file.getSize(), 3);
                appendXMLTagIfNotNull(sb, "crc", file.getCrc(), 3);
                appendXMLTagIfNotNull(sb, "sha1", file.getSha1(), 3);
                appendXMLTagIfNotNull(sb, "md5", file.getMd5(), 3);
                appendXMLTagIfNotNull(sb, "status", file.getStatus(), 3);
                appendXMLTagIfNotNull(sb, "date", file.getDate(), 3);
                appendXMLTagIfNotNull(sb, "merge", file.getMerge(), 3);
                appendXMLTagIfNotNull(sb, "region", file.getRegion(), 3);
                sb.append("\t\t</file>\n");
            }

            for (Release release : game.getReleases()) {
                sb.append("\t\t<release");
                appendXMLAttributeIfNotNull(sb, "name", release.getName());
                appendXMLAttributeIfNotNull(sb, "region", release.getRegion());
                appendXMLAttributeIfNotNull(sb, "language", release.getLanguage());
                appendXMLAttributeIfNotNull(sb, "date", release.getDate());
                appendXMLAttributeIfNotNull(sb, "default", release.getDefault());
                sb.append("/>\n");
            }

            sb.append("\t</game>\n");
        }

        sb.append("</datafile>\n");
        return sb.toString();
    }
}
