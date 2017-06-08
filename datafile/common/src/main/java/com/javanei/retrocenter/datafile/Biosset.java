package com.javanei.retrocenter.datafile;

import java.io.Serializable;
import java.util.Objects;

public class Biosset implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * logiqx.biosset.name
     */
    private String name;

    /**
     * logiqx.biosset.description
     */
    private String description;

    /**
     * logiqx.biosset.default
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        Biosset biosset = (Biosset) o;
        return Objects.equals(name, biosset.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\t\t<biosset");
        appendAttributeIfNotNull(sb, "name", this.name);
        appendAttributeIfNotNull(sb, "description", this.description);
        appendAttributeIfNotNull(sb, "default", this._default);
        sb.append(" />\n");
        return sb.toString();
    }
}
