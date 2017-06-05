package com.javanei.retrocenter.logiqx;

import java.io.Serializable;

/**
 * <!ELEMENT header (name, description, category?, version, date?, author, email?, homepage?, url?, comment?, clrmamepro?, romcenter?)>
 */
public class Header implements Serializable {
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
}
