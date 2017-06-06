package com.javanei.retrocenter.logiqx;

import java.io.Serializable;
import java.util.Objects;

/**
 * <!ELEMENT rom EMPTY>
 */
public class LogiqxRom implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * <!ATTLIST rom name CDATA #REQUIRED>
     */
    private String name;

    /**
     * <!ATTLIST rom size CDATA #REQUIRED>
     */
    private Long size;

    /**
     * <!ATTLIST rom crc CDATA #IMPLIED>
     */
    private String crc;

    /**
     * <!ATTLIST rom sha1 CDATA #IMPLIED>
     */
    private String sha1;

    /**
     * <!ATTLIST rom md5 CDATA #IMPLIED>
     */
    private String md5;

    /**
     * <!ATTLIST rom merge CDATA #IMPLIED>
     */
    private String merge;

    /**
     * <!ATTLIST rom status (baddump|nodump|good|verified) "good">
     */
    private String status = "good";

    /**
     * <!ATTLIST rom date CDATA #IMPLIED>
     */
    private String date;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogiqxRom rom = (LogiqxRom) o;
        return Objects.equals(name, rom.name) &&
                Objects.equals(size, rom.size) &&
                Objects.equals(crc, rom.crc) &&
                Objects.equals(sha1, rom.sha1) &&
                Objects.equals(md5, rom.md5);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, size, crc, sha1, md5);
    }
}
