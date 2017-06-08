package com.javanei.retrocenter.datafile;

import java.io.Serializable;
import java.util.Objects;

public class Rom implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * logiqx.rom.name, clrmamepro.rom.name
     */
    private String name;
    /**
     * logiqx.rom.size, clrmamepro.rom.size
     */
    private Long size;
    /**
     * logiqx.rom.crc, clrmamepro.rom.crc
     * CRC must have 8 characters
     */
    private String crc;
    /**
     * logiqx.rom.sha1, clrmamepro.rom.sha1
     * SHA1 must have 40 characters
     */
    private String sha1;
    /**
     * logiqx.rom.md5, clrmamepro.rom.md5
     * MD5 must have 32 characters
     */
    private String md5;
    /**
     * logiqx.rom.status [(baddump|nodump|good|verified) "good"], clrmamepro.rom.flags [baddump|verified]
     */
    private String status;
    /**
     * logiqx.rom.date
     */
    private String date;
    /**
     * logiqx.rom.merge
     */
    private String merge;
    /**
     * clrmamepro.rom.region
     */
    private String region;

    private static void appendAttributeIfNotNull(StringBuilder sb, String name, Object value) {
        if (value != null)
            sb.append(" ").append(name).append("=\"").append(value).append("\"");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getCrc() {
        return crc;
    }

    public void setCrc(String crc) {
        this.crc = crc;
    }

    public String getSha1() {
        return sha1;
    }

    public void setSha1(String sha1) {
        this.sha1 = sha1;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getMerge() {
        return merge;
    }

    public void setMerge(String merge) {
        this.merge = merge;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Rom rom = (Rom) o;
        return Objects.equals(name, rom.name) &&
                Objects.equals(size, rom.size) &&
                Objects.equals(crc, rom.crc) &&
                Objects.equals(sha1, rom.sha1) &&
                Objects.equals(md5, rom.md5) &&
                Objects.equals(status, rom.status) &&
                Objects.equals(date, rom.date) &&
                Objects.equals(region, rom.region);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, size, crc, sha1, md5, status, date, region);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\t\t<rom");

        appendAttributeIfNotNull(sb, "name", this.name);
        appendAttributeIfNotNull(sb, "size", this.size);
        appendAttributeIfNotNull(sb, "crc", this.crc);
        appendAttributeIfNotNull(sb, "sha1", this.sha1);
        appendAttributeIfNotNull(sb, "md5", this.md5);
        appendAttributeIfNotNull(sb, "merge", this.merge);
        appendAttributeIfNotNull(sb, "status", this.status);
        appendAttributeIfNotNull(sb, "date", this.date);
        appendAttributeIfNotNull(sb, "region", this.region);

        sb.append(" />\n");
        return sb.toString();
    }
}
