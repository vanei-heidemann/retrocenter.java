package com.javanei.retrocenter.catalog.tosec.parser.flags;

import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public enum CountryFlagEnum {
    AE("AE", "United Arab Emirates"),
    AL("AL", "Albania"),
    AS("AS", "Asia"),
    AT("AT", "Austria"),
    AU("AU", "Australia"),
    BA("BA", "Bosnia and Herzegovina"),
    BE("BE", "Belgium"),
    BG("BG", "Bulgaria"),
    BR("BR", "Brazil"),
    CA("CA", "Canada"),
    CH("CH", "Switzerland"),
    CL("CL", "Chile"),
    CN("CN", "China"),
    CS("CS", "Serbia and Montenegro"),
    CY("CY", "Cyprus"),
    CZ("CZ", "Czech Republic"),
    DE("DE", "Germany"),
    DK("DK", "Denmark"),
    EE("EE", "Estonia"),
    EG("EG", "Egypt"),
    ES("ES", "Spain"),
    EU("EU", "Europe"),
    FI("FI", "Finland"),
    FR("FR", "France"),
    GB("GB", "United Kingdom"),
    GR("GR", "Greece"),
    HK("HK", "Hong Kong"),
    HR("HR", "Croatia"),
    HU("HU", "Hungary"),
    ID("ID", "Indonesia"),
    IE("IE", "Ireland"),
    IL("IL", "Israel"),
    IN("IN", "India"),
    IR("IR", "Iran"),
    IS("IS", "Iceland"),
    IT("IT", "Italy"),
    JO("JO", "Jordan"),
    JP("JP", "Japan"),
    KR("KR", "South Korea"),
    LT("LT", "Lithuania"),
    LU("LU", "Luxembourg"),
    LV("LV", "Latvia"),
    MN("MN", "Mongolia"),
    MX("MX", "Mexico"),
    MY("MY", "Malaysia"),
    NL("NL", "Netherlands"),
    NO("NO", "Norway"),
    NP("NP", "Nepal"),
    NZ("NZ", "New Zealand"),
    OM("OM", "Oman"),
    PE("PE", "Peru"),
    PH("PH", "Philippines"),
    PL("PL", "Poland"),
    PT("PT", "Portugal"),
    QA("QA", "Qatar"),
    RO("RO", "Romania"),
    RU("RU", "Russia"),
    SE("SE", "Sweden"),
    SG("SG", "Singapore"),
    SI("SI", "Slovenia"),
    SK("SK", "Slovakia"),
    TH("TH", "Thailand"),
    TR("TR", "Turkey"),
    TW("TW", "Taiwan"),
    US("US", "United States"),
    VN("VN", "Vietnam"),
    YU("YU", "Yugoslavia"),
    ZA("ZA", "South Africa");

    private String name;
    private String description;

    CountryFlagEnum(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public static List<CountryFlagEnum> fromName(String name) {
        List<CountryFlagEnum> r = new LinkedList<>();
        if (name.equals("-")) {
            return r;
        }
        StringTokenizer st = new StringTokenizer(name, "-");
        while (st.hasMoreTokens()) {
            String s = st.nextToken();
            boolean achou = false;
            for (CountryFlagEnum e : CountryFlagEnum.values()) {
                if (e.getName().equals(s)) {
                    r.add(e);
                    achou = true;
                }
            }
            if (!achou) return null;
        }
        return r;
    }

    public static CountryFlagEnum fromDescription(String description) {
        for (CountryFlagEnum e : CountryFlagEnum.values()) {
            if (e.getDescription().equals(description)) {
                return e;
            }
        }
        return null;
    }

    public static boolean isCountry(String name) {
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
        return name;
    }
}
