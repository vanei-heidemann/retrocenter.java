package com.javanei.retrocenter.catalog.tosec.parser.flags;

import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * English is seen as the default language, in other words when no language or country flag is used
 * it is taken that the software is in English or is language neutral.
 */
public enum LanguageFlagEnum {
    ar("ar", "Arabic"),
    bg("bg", "Bulgarian"),
    bs("bs", "Bosnian"),
    cs("cs", "Czech"),
    cy("cy", "Welsh"),
    da("da", "Danish"),
    de("de", "German"),
    el("el", "Greek"),
    en("en", "English"),
    eo("eo", "Esperanto"),
    es("es", "Spanish"),
    et("et", "Estonian"),
    fa("fa", "Persian"),
    fi("fi", "Finnish"),
    fr("fr", "French"),
    ga("ga", "Irish"),
    gu("gu", "Gujarati"),
    he("he", "Hebrew"),
    hi("hi", "Hindi"),
    hr("hr", "Croatian"),
    hu("hu", "Hungarian"),
    is("is", "Icelandic"),
    it("it", "Italian"),
    ja("ja", "Japanese"),
    ko("ko", "Korean"),
    lt("lt", "Lithuanian"),
    lv("lv", "Latvian"),
    ms("ms", "Malay"),
    nl("nl", "Dutch"),
    no("no", "Norwegian"),
    pl("pl", "Polish"),
    pt("pt", "Portuguese"),
    ro("ro", "Romanian"),
    ru("ru", "Russian"),
    sk("sk", "Slovakian"),
    sl("sl", "Slovenian"),
    sq("sq", "Albanian"),
    sr("sr", "Serbian"),
    sv("sv", "Swedish"),
    th("th", "Thai"),
    tr("tr", "Turkish"),
    ur("ur", "Urdu"),
    vi("vi", "Vietnamese"),
    yi("yi", "Yiddish"),
    zh("zh", "Chinese");

    private String name;
    private String description;

    LanguageFlagEnum(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public static List<LanguageFlagEnum> fromName(String name) {
        List<LanguageFlagEnum> l = new LinkedList<>();
        StringTokenizer st = new StringTokenizer(name, "-");
        while (st.hasMoreTokens()) {
            String s = st.nextToken();
            boolean achou = false;
            for (LanguageFlagEnum e : LanguageFlagEnum.values()) {
                if (e.getName().equals(s)) {
                    l.add(e);
                    achou = true;
                }
            }
            if (!achou) return null;
        }
        return l;
    }

    public static LanguageFlagEnum fromDescription(String description) {
        for (LanguageFlagEnum e : LanguageFlagEnum.values()) {
            if (e.getDescription().equals(description)) {
                return e;
            }
        }
        throw new IllegalArgumentException(description);
    }

    public static boolean isLanguage(String name) {
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
