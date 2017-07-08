package com.javanei.retrocenter.catalog.nointro.parser;

import java.io.Serializable;

public class TagValue implements Serializable {
    private static final long serialVersionUID = 1L;
    private String value;
    private char[] separators;

    public TagValue(String value) {
        this.value = value;
    }

    public TagValue(String value, char[] separators) {
        this.value = value;
        this.separators = separators;
    }

    public String getValue() {
        return this.value;
    }

    public char getStartSeparator() {
        return this.separators != null ? this.separators[0] : null;
    }

    public char getEndSeparator() {
        return this.separators != null ? this.separators[1] : null;
    }

    @Override
    public String toString() {
        if (this.separators == null) return this.value;
        StringBuilder sb = new StringBuilder();
        sb.append(separators[0]).append(this.value).append(separators[1]);
        return sb.toString();
    }
}
