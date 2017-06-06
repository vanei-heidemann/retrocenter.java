package com.javanei.retrocenter.logiqx;

import java.io.Serializable;

/**
 * <!ELEMENT header (name, description, category?, version, date?, author, email?, homepage?, url?, comment?, clrmamepro?, romcenter?)>
 */
public class LogiqxHeader implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * <!ELEMENT name (#PCDATA)>
     */
    private String name;
    /**
     * <!ELEMENT description (#PCDATA)>
     */
    private String description;
    /**
     * <!ELEMENT category (#PCDATA)>
     */
    private String category;
    /**
     * <!ELEMENT version (#PCDATA)>
     */
    private String version;
    /**
     * <!ELEMENT date (#PCDATA)>
     */
    private String date;
    /**
     * <!ELEMENT author (#PCDATA)>
     */
    private String author;
    /**
     * <!ELEMENT email (#PCDATA)>
     */
    private String email;
    /**
     * <!ELEMENT homepage (#PCDATA)>
     */
    private String homepage;
    /**
     * <!ELEMENT url (#PCDATA)>
     */
    private String url;
    /**
     * <!ELEMENT comment (#PCDATA)>
     */
    private String comment;

    // ///////////// clrmamepro properties

    /**
     * <!ATTLIST clrmamepro header CDATA #IMPLIED>
     */
    private String header;

    /**
     * <!ATTLIST clrmamepro forcemerging (none|split|full) "split">
     */
    private String forcemerging;

    /**
     * <!ATTLIST clrmamepro forcenodump (obsolete|required|ignore) "obsolete">
     */
    private String forcenodump;

    /**
     * <!ATTLIST clrmamepro forcepacking (zip|unzip) "zip">
     */
    private String forcepacking;

    // ///////////// romcenter properties

    /**
     * <!ATTLIST romcenter plugin CDATA #IMPLIED>
     */
    private String plugin;

    /**
     * <!ATTLIST romcenter rommode (merged|split|unmerged) "split">
     */
    private String rommode;

    /**
     * <!ATTLIST romcenter biosmode (merged|split|unmerged) "split">
     */
    private String biosmode;

    /**
     * <!ATTLIST romcenter samplemode (merged|unmerged) "merged">
     */
    private String samplemode;

    /**
     * <!ATTLIST romcenter lockrommode (yes|no) "no">
     */
    private String lockrommode;

    /**
     * <!ATTLIST romcenter lockbiosmode (yes|no) "no">
     */
    private String lockbiosmode;

    /**
     * <!ATTLIST romcenter locksamplemode (yes|no) "no">
     */
    private String locksamplemode;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getForcemerging() {
        return forcemerging;
    }

    public void setForcemerging(String forcemerging) {
        this.forcemerging = forcemerging;
    }

    public String getForcenodump() {
        return forcenodump;
    }

    public void setForcenodump(String forcenodump) {
        this.forcenodump = forcenodump;
    }

    public String getForcepacking() {
        return forcepacking;
    }

    public void setForcepacking(String forcepacking) {
        this.forcepacking = forcepacking;
    }

    public String getPlugin() {
        return plugin;
    }

    public void setPlugin(String plugin) {
        this.plugin = plugin;
    }

    public String getRommode() {
        return rommode;
    }

    public void setRommode(String rommode) {
        this.rommode = rommode;
    }

    public String getBiosmode() {
        return biosmode;
    }

    public void setBiosmode(String biosmode) {
        this.biosmode = biosmode;
    }

    public String getSamplemode() {
        return samplemode;
    }

    public void setSamplemode(String samplemode) {
        this.samplemode = samplemode;
    }

    public String getLockrommode() {
        return lockrommode;
    }

    public void setLockrommode(String lockrommode) {
        this.lockrommode = lockrommode;
    }

    public String getLockbiosmode() {
        return lockbiosmode;
    }

    public void setLockbiosmode(String lockbiosmode) {
        this.lockbiosmode = lockbiosmode;
    }

    public String getLocksamplemode() {
        return locksamplemode;
    }

    public void setLocksamplemode(String locksamplemode) {
        this.locksamplemode = locksamplemode;
    }
}
