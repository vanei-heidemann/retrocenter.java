package com.javanei.retrocenter.catalog.tosec.parser.flags;

public enum VideoFlagEnum {
    CGA("CGA"),
    EGA("EGA"),
    HGC("HGC"),
    MCGA("MCGA"),
    MDA("MDA"),
    NTSC("NTSC"),
    NTSC_PAL("NTSC-PAL"),
    PAL("PAL"),
    PAL60("PAL-60"),
    PALNTSC("PAL-NTSC"),
    SVGA("SVGA"),
    VGA("VGA"),
    XGA("XGA");

    private String name;

    VideoFlagEnum(String name) {
        this.name = name;
    }

    public static VideoFlagEnum fromName(String name) {
        for (VideoFlagEnum e : VideoFlagEnum.values()) {
            if (e.getName().equals(name)) {
                return e;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public boolean isVideoFlag(String name) {
        return fromName(name) != null;
    }
}
