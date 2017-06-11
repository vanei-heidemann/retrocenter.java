package com.javanei.retrocenter.common.util;

public final class ValidValuesUtil {
    public static final String[] YES_NO = new String[]{"yes", "no"};
    public static final String[] YES_NO_PARTIAL = new String[]{"yes", "no", "partial"};
    public static final String[] BADDUMP_NODUMP_GOOD = new String[]{"baddump", "nodump", "good"};
    public static final String[] GOOD_IMPERFECT_PRELIMINARY = new String[]{"good", "imperfect", "preliminary"};
    public static final String[] SUPPORTED_UNSUPPORTED = new String[]{"supported", "unsupported"};
    public static final String[] ORIGINAL_COMPATIBLE = new String[]{"original", "compatible"};

    public static String validateValue(String value, String[] valueList) {
        if (value == null) {
            return null;
        } else {
            for (String s : valueList) {
                if (s.equals(value)) {
                    return value;
                }
            }
        }
        throw new IllegalArgumentException(value);
    }
}
