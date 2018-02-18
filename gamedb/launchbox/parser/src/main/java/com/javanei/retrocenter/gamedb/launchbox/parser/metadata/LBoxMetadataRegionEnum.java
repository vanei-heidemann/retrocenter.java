package com.javanei.retrocenter.gamedb.launchbox.parser.metadata;

public enum LBoxMetadataRegionEnum {

    SouthAmerica("South America"),
    TheNetherlands("The Netherlands"),
    HongKong("Hong Kong"),
    UnitedStates("United States"),
    World("World"),
    NorthAmerica("North America"),
    Russia("Russia"),
    Oceania("Oceania"),
    Greece("Greece"),
    Sweden("Sweden"),
    China("China"),
    Brazil("Brazil"),
    France("France"),
    Korea("Korea"),
    Europe("Europe"),
    Japan("Japan"),
    UnitedKingdom("United Kingdom"),
    Spain("Spain"),
    Canada("Canada"),
    Asia("Asia"),
    Norway("Norway"),
    Finland("Finland"),
    Italy("Italy"),
    Australia("Australia"),
    Germany("Germany");

    private final String name;

    LBoxMetadataRegionEnum(String name) {
        this.name = name;
    }

    public static LBoxMetadataRegionEnum fromName(String name) {
        if (name.equals("USA")) {
            return UnitedStates;
        }

        for (LBoxMetadataRegionEnum r : LBoxMetadataRegionEnum.values()) {
            if (r.name.equals(name)) {
                return r;
            }
        }
        return null;
    }

    public static boolean isRegion(String name) {
        return fromName(name) != null;
    }
}
