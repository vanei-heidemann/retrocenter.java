package com.javanei.retrocenter.datafile;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Header implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * clrmamepro.name, logiqx.name
     */
    private String name;
    /**
     * clrmamepro.description, logiqx.description
     */
    private String description;
    /**
     * clrmamepro.category, logiqx.category
     */
    private String category;
    /**
     * clrmamepro.version, logiqx.version
     */
    private String version;
    /**
     * clrmamepro.author, logiqx.author
     */
    private String author;
    /**
     * logiqx.date
     */
    private String date;
    /**
     * logiqx.email
     */
    private String email;
    /**
     * clrmamepro.homepage, logiqx.homepage
     */
    private String homepage;
    /**
     * clrmamepro.url, logiqx.url
     */
    private String url;
    /**
     * logiqx.comment
     */
    private String comment;
    /**
     * Adicional not standard fields
     */
    private Map<String, String> customFields = new HashMap<>();

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
        return "Header{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", version='" + version + '\'' +
                ", author='" + author + '\'' +
                ", date='" + date + '\'' +
                ", email='" + email + '\'' +
                ", homepage='" + homepage + '\'' +
                ", url='" + url + '\'' +
                ", comment='" + comment + '\'' +
                ", customFields=" + customFields +
                '}';
    }
}
