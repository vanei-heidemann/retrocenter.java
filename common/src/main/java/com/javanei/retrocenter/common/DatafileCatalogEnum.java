package com.javanei.retrocenter.common;

public enum DatafileCatalogEnum {
    NoIntro,
    TOSEC,
    MAME;

    public static DatafileCatalogEnum fromName(String name) {
        if (name == null) {
            return null;
        } else if (name.toLowerCase().contains("no-intro") || name.toLowerCase().contains("nointro")) {
            return NoIntro;
        } else if (name.toUpperCase().contains("TOSEC")) {
            return TOSEC;
        } else if (name.toUpperCase().contains("MAME")) {
            return MAME;
        }
        throw new IllegalArgumentException(name);
    }

    public static boolean isValid(String name) {
        return name != null && (
                name.toLowerCase().contains("no-intro")
                        || name.toLowerCase().contains("nointro")
                        || name.toUpperCase().contains("TOSEC")
                        || name.toUpperCase().contains("MAME"));
    }
}
