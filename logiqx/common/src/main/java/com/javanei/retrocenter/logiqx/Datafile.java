package com.javanei.retrocenter.logiqx;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * <!ELEMENT datafile (header?, game+)>
 */
public class Datafile implements Serializable {
    /**
     * <!ATTLIST datafile build CDATA #IMPLIED>
     */
    private String build;
    
    /**
     * <!ATTLIST datafile debug (yes|no) "no">
     */
    private Boolean debug = Boolean.FALSE;
    
    private Header header;
    
    private Set<Game> games = new HashSet<>();
}
