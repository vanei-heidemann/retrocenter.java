package com.javanei.retrocenter.datafile;

import java.io.Serializable;
import java.util.Objects;

public class ArtifactFile implements Serializable {
    /**
     * Valid values: ROM, DISK, SAMPLE
     */
    private String type;
    /**
     * logiqx.rom.name, clrmamepro.rom.name
     * logiqx.disk.name, clrmamepro.disk.name
     */
    private String name;
    /**
     * logiqx.rom.size, clrmamepro.rom.size
     * logiqx.disk.sha1, clrmamepro.disk.sha1
     */
    private String size;
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
     * logiqx.disk.md5, clrmamepro.disk.md5
     * MD5 must have 32 characters
     */
    private String md5;
    /**
     * logiqx.rom.status [(baddump|nodump|good|verified) "good"], clrmamepro.rom.flags [baddump|verified]
     * logiqx.disk.status [(baddump|nodump|good|verified) "good"]
     */
    private String status;
    /**
     * logiqx.rom.date
     */
    private String date;
    /**
     * logiqx.rom.merge
     * logiqx.disk.merge
     */
    private String merge;
    /**
     * clrmamepro.rom.region
     */
    private String region;

    public ArtifactFile() {
    }

    public ArtifactFile(String type) {
        this.type = type;
    }

    public ArtifactFile(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public ArtifactFile(String type, String name, String size, String crc, String sha1, String md5, String status,
                        String date, String merge, String region) {
        this.type = type;
        this.name = name;
        this.size = size;
        this.crc = crc;
        this.sha1 = sha1;
        this.md5 = md5;
        this.status = status;
        this.date = date;
        this.merge = merge;
        this.region = region;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
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

    public String getMerge() {
        return merge;
    }

    public void setMerge(String merge) {
        this.merge = merge;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ArtifactFile gameFile = (ArtifactFile) o;
        return Objects.equals(type, gameFile.type) &&
                Objects.equals(name, gameFile.name) &&
                Objects.equals(size, gameFile.size) &&
                Objects.equals(crc, gameFile.crc) &&
                Objects.equals(sha1, gameFile.sha1) &&
                Objects.equals(md5, gameFile.md5) &&
                Objects.equals(status, gameFile.status) &&
                Objects.equals(date, gameFile.date) &&
                Objects.equals(merge, gameFile.merge) &&
                Objects.equals(region, gameFile.region);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, name, size, crc, sha1, md5, status, date, merge, region);
    }
}
