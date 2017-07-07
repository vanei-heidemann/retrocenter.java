package com.javanei.retrocenter.catalog.tosec.parser.flags;

import java.util.StringTokenizer;

public class DumpInfoFlag {
    private final DumpInfoFlagEnum dumpInfo;
    private final Integer count;
    private final String complement;

    private DumpInfoFlag(DumpInfoFlagEnum dumpInfo, Integer count, String complement) {
        this.dumpInfo = dumpInfo;
        this.count = count;
        this.complement = complement;
    }

    public static DumpInfoFlag parseInfo(String s) {
        DumpInfoFlagEnum flag = null;
        DecomposedTag tag = decomposeTag(s);
        switch (tag.type) {
            case "cr":
                flag = DumpInfoFlagEnum.Cracked;
                break;
            case "f":
                flag = DumpInfoFlagEnum.Fixed;
                break;
            case "h":
                flag = DumpInfoFlagEnum.Hacked;
                break;
            case "m":
                flag = DumpInfoFlagEnum.Modified;
                break;
            case "p":
                flag = DumpInfoFlagEnum.Pirated;
                break;
            case "tr":
                flag = DumpInfoFlagEnum.Translated;
                break;
            case "t":
                flag = DumpInfoFlagEnum.Trained;
                break;
            case "o":
                flag = DumpInfoFlagEnum.OverDump;
                break;
            case "u":
                flag = DumpInfoFlagEnum.UnderDump;
                break;
            case "v":
                flag = DumpInfoFlagEnum.Virus;
                break;
            case "b":
                flag = DumpInfoFlagEnum.BadDump;
                break;
            case "a":
                flag = DumpInfoFlagEnum.Alternate;
                break;
            case "!":
                flag = DumpInfoFlagEnum.VerifiedGoodDump;
                break;
        }
        return flag != null ? new DumpInfoFlag(flag, tag.count, tag.complement) : null;
    }

    private static DecomposedTag decomposeTag(final String tag) {
        DecomposedTag r = new DecomposedTag();
        StringTokenizer st = new StringTokenizer(tag, " ");
        String s1 = st.nextToken();
        for (int i = 0; i < s1.length(); i++) {
            char c = s1.charAt(i);
            if (c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == 8 || c == 9) {
                r.count = Integer.parseInt(s1.substring(i));
                i = s1.length();
                break;
            } else {
                r.type = r.type + c;
            }
        }

        if (st.hasMoreTokens()) {
            r.complement = tag.substring(tag.indexOf(" ")).trim();
        }
        return r;
    }

    public DumpInfoFlagEnum getDumpInfo() {
        return dumpInfo;
    }

    public String getComplement() {
        return complement;
    }

    public String getValue() {
        return dumpInfo.getName()
                + (count != null ? count.toString() : "")
                + (complement != null ? " " + complement : "");
    }

    private static class DecomposedTag {
        String type = "";
        Integer count;
        String complement;
    }
}
