package com.javanei.retrocenter.goodtools;

import java.io.Serializable;

public class GoodDatafileRom implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String size;
    private String crc;
    private String sha1;
    private String md5;

    public GoodDatafileRom() {
    }

    public GoodDatafileRom(String name) {
        this.name = name;
    }

    public GoodDatafileRom(String name, String size, String crc, String sha1, String md5) {
        this.name = name;
        this.size = size;
        this.crc = crc;
        this.sha1 = sha1;
        this.md5 = md5;
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

    public String getDescription() {
        if (this.name.contains(".")) {
            return this.name.substring(0, this.name.lastIndexOf("."));
        }
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoodDatafileRom that = (GoodDatafileRom) o;

        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "GoodDatafileRom{" +
                "name='" + name + '\'' +
                ", size='" + size + '\'' +
                ", crc='" + crc + '\'' +
                ", sha1='" + sha1 + '\'' +
                ", md5='" + md5 + '\'' +
                '}';
    }
}
