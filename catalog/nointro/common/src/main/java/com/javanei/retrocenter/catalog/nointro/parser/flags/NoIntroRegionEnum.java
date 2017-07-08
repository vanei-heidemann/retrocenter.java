package com.javanei.retrocenter.catalog.nointro.parser.flags;

import java.util.LinkedList;
import java.util.List;

public enum NoIntroRegionEnum {
    Asia("Asia"),
    Australia("Australia"),
    Brazil("Brazil"),
    Canada("Canada"),
    China("China"),
    Denmark("Denmark"),
    Europe("Europe"),
    Finland("Finland"),
    France("France"),
    Germany("Germany"),
    HongKong("Hong Kong"),
    Italy("Italy"),
    Japan("Japan"),
    Korea("Korea"),
    Netherlands("Netherlands"),
    Russia("Russia"),
    Spain("Spain"),
    Sweden("Sweden"),
    Taiwan("Taiwan"),
    Unknown("Unknown"),
    USA("USA"),
    World("World");

    private String name;

    NoIntroRegionEnum(String name) {
        this.name = name;
    }

    public static List<NoIntroRegionEnum> fromName(String name) {
        List<NoIntroRegionEnum> result = new LinkedList<>();
        for (String s : name.split(",")) {
            boolean achou = false;
            for (NoIntroRegionEnum r : NoIntroRegionEnum.values()) {
                if (r.getName().equals(s.trim())) {
                    result.add(r);
                    achou = true;
                }
            }
            if (!achou) {
                return null;
            }
        }
        return result;
    }

    public static List<String> toListString(List<NoIntroRegionEnum> regions) {
        List<String> l = new LinkedList<>();
        for (NoIntroRegionEnum r : regions) {
            l.add(r.getName());
        }
        return l;
    }

    public static String toString(List<NoIntroRegionEnum> regions) {
        StringBuilder sb = new StringBuilder();
        for (NoIntroRegionEnum r : regions) {
            if (sb.length() > 0) {
                sb.append(", ");
            }
            sb.append(r.getName());
        }
        return sb.toString();
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
