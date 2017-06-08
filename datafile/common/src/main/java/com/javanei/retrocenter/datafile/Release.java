package com.javanei.retrocenter.datafile;

import java.io.Serializable;
import java.util.Objects;

public class Release implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * logiqx.game.name
     */
    private String name;

    /**
     * logiqx.game.region
     */
    private String region;

    /**
     * logiqx.game.language
     */
    private String language;

    /**
     * logiqx.game.date
     */
    private String date;

    /**
     * logiqx.game.default
     */
    private String _default = "no";

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

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDefault() {
        return _default;
    }

    public void setDefault(String _default) {
        this._default = _default;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Release release = (Release) o;
        return Objects.equals(name, release.name) &&
                Objects.equals(region, release.region) &&
                Objects.equals(language, release.language) &&
                Objects.equals(date, release.date) &&
                Objects.equals(_default, release._default);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, region, language, date, _default);
    }
}
