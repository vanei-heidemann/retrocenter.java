package com.javanei.retrocenter.datafile;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.javanei.retrocenter.clrmamepro.CMProDatafile;
import com.javanei.retrocenter.clrmamepro.CMProGame;
import com.javanei.retrocenter.clrmamepro.CMProHeader;
import com.javanei.retrocenter.common.DatafileCategoryEnum;
import com.javanei.retrocenter.logiqx.LogiqxDatafile;
import com.javanei.retrocenter.logiqx.LogiqxGame;
import com.javanei.retrocenter.logiqx.LogiqxHeader;
import com.javanei.retrocenter.mame.Mame;
import com.javanei.retrocenter.mame.MameDisk;
import com.javanei.retrocenter.mame.MameMachine;
import com.javanei.retrocenter.mame.MameRom;
import com.javanei.retrocenter.mame.MameSample;

public class Datafile implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * clrmamepro.header.name, logiqx.header.name
     */
    private String name;
    /**
     * clrmamepro.header.category, logiqx.header.category
     */
    private String category;
    /**
     * clrmamepro.header.version, logiqx.header.version
     */
    private String version;
    /**
     * clrmamepro.header.description, logiqx.header.description
     */
    private String description;
    /**
     * clrmamepro.header.author, logiqx.header.author
     */
    private String author;
    /**
     * logiqx.header.date
     */
    private String date;
    /**
     * logiqx.header.email
     */
    private String email;
    /**
     * clrmamepro.header.homepage, logiqx.header.homepage
     */
    private String homepage;
    /**
     * clrmamepro.header.url, logiqx.header.url
     */
    private String url;
    /**
     * logiqx.header.comment
     */
    private String comment;

    private Set<Game> games = new HashSet<>();

    public Datafile() {
    }

    public Datafile(String name) {
        this.name = name;
    }

    public Datafile(String name, String category, String version, String description, String author, String date,
            String email, String homepage, String url, String comment) {
        this.name = name;
        this.category = category;
        this.version = version;
        this.description = description;
        this.author = author;
        this.date = date;
        this.email = email;
        this.homepage = homepage;
        this.url = url;
        this.comment = comment;
    }

    public static Datafile fromLogiqx(LogiqxDatafile p) {
        Datafile r = p.getHeader() != null ? new Datafile(p.getHeader().getName(), p.getHeader().getCategory(), p.getHeader().getVersion(),
                p.getHeader().getDescription(), p.getHeader().getAuthor(), p.getHeader().getDate(),
                p.getHeader().getEmail(), p.getHeader().getHomepage(), p.getHeader().getUrl(),
                p.getHeader().getComment()) : new Datafile();
        for (LogiqxGame game : p.getGames()) {
            r.addGame(Game.fromLogiqx(game));
        }
        return r;
    }

    public static Datafile fromCMPro(CMProDatafile p) {
        Datafile r = new Datafile(p.getHeader().getName(), p.getHeader().getCategory(), p.getHeader().getVersion(),
                p.getHeader().getDescription(), p.getHeader().getAuthor(), null,
                null, p.getHeader().getHomepage(), p.getHeader().getUrl(), null);
        for (CMProGame game : p.getGames()) {
            r.addGame(Game.fromCMPro(game));
        }
        return r;
    }

    public static Datafile fromMame(Mame p) {
        Datafile r = new Datafile();
        r.setName("MAME");
        r.setCategory(DatafileCategoryEnum.MAME.name());
        r.setVersion(p.getBuild());
        for (MameMachine machine : p.getMachines()) {
            Game game = new Game(machine.getName(), machine.getIsbios(), machine.getDescription(), machine.getYear(),
                    machine.getManufacturer(), machine.getCloneof(), machine.getRomof(), machine.getSampleof(), null);
            for (MameRom rom : machine.getRoms()) {
                GameFile f = new GameFile(GameFileTypeEnum.ROM.name(), rom.getName(), rom.getSize(), rom.getCrc(),
                        rom.getSha1(), null, rom.getStatus(), null, rom.getMerge(), rom.getRegion());
                game.addFile(f);
            }
            for (MameDisk disk : machine.getDisks()) {
                GameFile f = new GameFile(GameFileTypeEnum.DISK.name(), disk.getName(), null, null,
                        disk.getSha1(), null, disk.getStatus(), null, disk.getMerge(), disk.getRegion());
                game.addFile(f);
            }
            for (MameSample sample : machine.getSamples()) {
                GameFile f = new GameFile(GameFileTypeEnum.SAMPLE.name(), sample.getName());
                game.addFile(f);
            }
        }
        return r;
    }

    public LogiqxDatafile toLogiqx() {
        LogiqxDatafile r = new LogiqxDatafile(new LogiqxHeader(this.name, this.description, this.category, this.version,
                this.date, this.author, this.email, this.homepage, this.url, this.comment));
        for (Game game : this.games) {
            r.addGame(game.toLogiqx());
        }
        return r;
    }

    public CMProDatafile toCMPro() {
        CMProDatafile r = new CMProDatafile(new CMProHeader(this.name, this.description, this.category, this.version,
                this.author, this.homepage, this.url, null, null));
        for (Game game : this.games) {
            r.addGame(game.toCMPro());
        }
        return r;
    }

    public boolean addGame(Game game) {
        return this.games.add(game);
    }

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
        if (category == null) {
            this.category = null;
        } else if (category.toLowerCase().equals("no-intro") || category.toLowerCase().equals("nointro")) {
            this.category = DatafileCategoryEnum.NoIntro.name();
        } else if (category.toLowerCase().equals("logiqx")) {
            this.category = DatafileCategoryEnum.Logiqx.name();
        } else if (category.toUpperCase().equals("MAME")) {
            this.category = DatafileCategoryEnum.MAME.name();
        } else {
            throw new IllegalArgumentException("Invalid category value: '" + category + "'");
        }
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
}
