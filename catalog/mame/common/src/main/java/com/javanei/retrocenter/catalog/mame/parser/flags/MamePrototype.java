package com.javanei.retrocenter.catalog.mame.parser.flags;

public final class MamePrototype {
    private MamePrototype() {
    }

    public static String parsePrototype(String prot) {
        if (prot.toLowerCase().startsWith("prototype")) {
            return prot;
        }
        return null;
    }
}
