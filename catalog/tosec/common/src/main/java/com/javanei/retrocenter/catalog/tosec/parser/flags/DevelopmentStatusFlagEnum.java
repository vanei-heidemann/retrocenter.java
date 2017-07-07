package com.javanei.retrocenter.catalog.tosec.parser.flags;

public enum DevelopmentStatusFlagEnum {
    alpha("alpha", "Early test build"),
    beta("beta", "Later, feature complete test build"),
    preview("preview", "Near complete build"),
    pre_release("pre-release", "Near complete build"),
    proto("proto", "Unreleased, prototype software");

    private String name;
    private String description;

    DevelopmentStatusFlagEnum(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public static DevelopmentStatusFlagEnum fromName(String name) {
        for (DevelopmentStatusFlagEnum e : DevelopmentStatusFlagEnum.values()) {
            if (e.getName().equals(name)) {
                return e;
            }
        }
        return null;
    }

    public static boolean isDevelopmentStatus(String name) {
        return fromName(name) != null;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
