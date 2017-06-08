package com.javanei.retrocenter.datafile;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Header implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * clrmamepro.header.name, logiqx.header.name
     */
    private String name;
    /**
     * clrmamepro.header.description, logiqx.header.description
     */
    private String description;
    /**
     * clrmamepro.header.category, logiqx.header.category
     */
    private String category;
    /**
     * clrmamepro.header.version, logiqx.header.version
     */
    private String version;
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
    /**
     * Adicional not standard fields
     * logiqx.header.clrmamepro.header
     * logiqx.header.clrmamepro.forcemerging
     * logiqx.header.clrmamepro.forcenodump
     * logiqx.header.clrmamepro.forcepacking
     * logiqx.header.,romcenter.plugin
     * logiqx.header.,romcenter.rommode
     * logiqx.header.,romcenter.biosmode
     * logiqx.header.,romcenter.samplemode
     * logiqx.header.,romcenter.lockrommode
     * logiqx.header.,romcenter.lockbiosmode
     * logiqx.header.,romcenter.locksamplemode
     */
    private Map<String, String> customFields = new HashMap<>();

    private static void appendTagIfNotNull(StringBuilder sb, String name, Object value) {
        if (value != null)
            sb.append("\t\t<").append(name).append(">").append(value).append("</").append(name).append(">\n");
    }

    private static void appendAttributeIfNotNull(StringBuilder sb, String name, Object value) {
        if (value != null)
            sb.append(" ").append(name).append("=\"").append(value).append("\"");
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
        this.category = category;
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

    public Map<String, String> getCustomFields() {
        return customFields;
    }

    public void setCustomFields(Map<String, String> customFields) {
        if (customFields != null)
            this.customFields = customFields;
        else
            this.customFields = new HashMap<>();
    }

    public void addCustomField(String key, String value) {
        if (this.customFields.containsKey(key))
            throw new IllegalArgumentException("Duplicated custom field: " + key);
        this.customFields.put(key, value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\t<header>\n");
        appendTagIfNotNull(sb, "name", this.name);
        appendTagIfNotNull(sb, "description", this.description);
        appendTagIfNotNull(sb, "category", this.category);
        appendTagIfNotNull(sb, "version", this.version);
        appendTagIfNotNull(sb, "author", this.author);
        appendTagIfNotNull(sb, "date", this.date);
        appendTagIfNotNull(sb, "email", this.email);
        appendTagIfNotNull(sb, "homepage", this.homepage);
        appendTagIfNotNull(sb, "url", this.url);
        appendTagIfNotNull(sb, "comment", this.comment);

        for (String key : this.customFields.keySet()) {
            appendTagIfNotNull(sb, key, this.customFields.get(key));
        }

        sb.append("\t</header>\n");
        return sb.toString();
    }
}
