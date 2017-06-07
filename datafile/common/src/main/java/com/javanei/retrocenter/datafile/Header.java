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
}
