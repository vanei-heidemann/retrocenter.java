package com.javanei.retrocenter.catalog.mame.parser.flags;

public final class MameBootleg {
    private MameBootleg() {
    }

    public static final String parseBootleg(String tag) {
        if (tag.toLowerCase().startsWith("bootleg")) {
            return tag;
        }
        return null;
    }
}
