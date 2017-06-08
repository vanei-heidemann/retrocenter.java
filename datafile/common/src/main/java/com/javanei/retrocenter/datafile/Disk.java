package com.javanei.retrocenter.datafile;

import java.io.Serializable;
import java.util.Objects;

import com.javanei.retrocenter.clrmamepro.CMProDisk;
import com.javanei.retrocenter.logiqx.LogiqxDisk;

public class Disk implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * logiqx.disk.name, clrmamepro.disk.name
     */
    private String name;
    /**
     * logiqx.disk.sha1, clrmamepro.disk.sha1
     */
    private String sha1;
    /**
     * logiqx.disk.md5, clrmamepro.disk.md5
     */
    private String md5;
    /**
     * logiqx.disk.merge
     */
    private String merge;
    /**
     * logiqx.disk.status [(baddump|nodump|good|verified) "good"]
     */
    private String status;

    public Disk() {
    }

    public Disk(String name, String sha1, String md5) {
        this.name = name;
        this.sha1 = sha1;
        this.md5 = md5;
    }

    public Disk(String name, String sha1, String md5, String merge, String status) {
        this.name = name;
        this.sha1 = sha1;
        this.md5 = md5;
        this.merge = merge;
        this.status = status;
    }

    public static Disk fromLogiqx(LogiqxDisk p) {
        return new Disk(p.getName(), p.getSha1(), p.getMd5(), p.getMerge(), p.getStatus());
    }

    public static Disk fromClrmamepro(CMProDisk p) {
        return new Disk(p.getName(), p.getSha1(), p.getMd5());
    }

    private static void appendAttributeIfNotNull(StringBuilder sb, String name, Object value) {
        if (value != null)
            sb.append(" ").append(name).append("=\"").append(value).append("\"");
    }

    public LogiqxDisk toLogiqx() {
        return this.status == null ? new LogiqxDisk(this.name, this.sha1, this.md5, this.merge)
                : new LogiqxDisk(this.name, this.sha1, this.md5, this.merge, this.status);
    }

    public CMProDisk toClrmamepro() {
        return new CMProDisk(this.name, this.sha1, this.md5);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getMerge() {
        return merge;
    }

    public void setMerge(String merge) {
        this.merge = merge;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Disk disk = (Disk) o;
        return Objects.equals(name, disk.name) &&
                Objects.equals(sha1, disk.sha1) &&
                Objects.equals(md5, disk.md5);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, sha1, md5);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\t\t<disk");

        appendAttributeIfNotNull(sb, "name", this.name);
        appendAttributeIfNotNull(sb, "sha1", this.sha1);
        appendAttributeIfNotNull(sb, "md5", this.md5);
        appendAttributeIfNotNull(sb, "merge", this.merge);
        appendAttributeIfNotNull(sb, "status", this.status);

        sb.append(" />\n");
        return sb.toString();
    }
}
