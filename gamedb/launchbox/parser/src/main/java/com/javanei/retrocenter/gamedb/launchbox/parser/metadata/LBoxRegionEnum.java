package com.javanei.retrocenter.gamedb.launchbox.parser.metadata;

public enum LBoxRegionEnum {

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

    LBoxRegionEnum(String name) {
        this.name = name;
    }

    public static LBoxRegionEnum fromName(String name) {
        for (LBoxRegionEnum r : LBoxRegionEnum.values()) {
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
