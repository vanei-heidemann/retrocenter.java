package com.javanei.retrocenter.datafile;

import com.javanei.retrocenter.clrmamepro.CMProDisk;
import com.javanei.retrocenter.clrmamepro.CMProGame;
import com.javanei.retrocenter.clrmamepro.CMProRom;
import com.javanei.retrocenter.logiqx.LogiqxDisk;
import com.javanei.retrocenter.logiqx.LogiqxGame;
import com.javanei.retrocenter.logiqx.LogiqxRelease;
import com.javanei.retrocenter.logiqx.LogiqxRom;
import com.javanei.retrocenter.logiqx.LogiqxSample;
import com.javanei.retrocenter.mame.MameDisk;
import com.javanei.retrocenter.mame.MameMachine;
import com.javanei.retrocenter.mame.MameRom;
import com.javanei.retrocenter.mame.MameSample;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.StringTokenizer;

public class Game implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * logiqx.game.name, clrmamepro.game.name
     */
    private String name;
    /**
     * logiqx.game.isbios
     */
    private String isbios = "no";
    /**
     * logiqx.game.description, clrmamepro.game.description
     */
    private String description;
    /**
     * logiqx.game.year, clrmamepro.game.year
     */
    private String year;
    /**
     * logiqx.game.manufacturer, clrmamepro.game.manufacturer
     */
    private String manufacturer;
    /**
     * logiqx.game.cloneof, clrmamepro.game.cloneof
     */
    private String cloneof;
    /**
     * logiqx.game.romof, clrmamepro.game.romof
     */
    private String romof;
    /**
     * logiqx.game.sampleof
     */
    private String sampleof;
    /**
     * logiqx.game.comment
     */
    private String comment;

    private Set<GameFile> files = new HashSet<>();

    private Set<Release> releases = new HashSet<>();

    public Game() {
    }

    public Game(String name, String isbios, String description, String year, String manufacturer, String cloneof,
                String romof, String sampleof, String comment) {
        this.name = name;
        this.isbios = isbios;
        this.description = description;
        this.year = year;
        this.manufacturer = manufacturer;
        this.cloneof = cloneof;
        this.romof = romof;
        this.sampleof = sampleof;
        this.comment = comment;
    }

    public static Game fromMame(MameMachine machine) {
        Game game = new Game(machine.getName(), machine.getIsbios(), machine.getDescription(), machine.getYear(),
                machine.getManufacturer(), machine.getCloneof(), machine.getRomof(), machine.getSampleof(), null);
        for (MameRom rom : machine.getRoms()) {
            game.addFile(GameFile.fromMame(rom));
        }
        for (MameDisk disk : machine.getDisks()) {
            game.addFile(GameFile.fromMame(disk));
        }
        for (MameSample sample : machine.getSamples()) {
            game.addFile(GameFile.fromMame(sample));
        }
        return game;
    }

    public static Game fromLogiqx(LogiqxGame p) {
        Game r = new Game(p.getName(), p.getIsbios(), p.getDescription(), p.getYear(), p.getManufacturer(),
                p.getCloneof(), p.getRomof(), p.getSampleof(), p.getComment());
        for (LogiqxRelease release : p.getReleases()) {
            r.addRelease(Release.fromLogiqx(release));
        }
        for (LogiqxRom rom : p.getRoms()) {
            r.addFile(GameFile.fromLogiqx(rom));
        }
        for (LogiqxDisk disk : p.getDisks()) {
            r.addFile(GameFile.fromLogiqx(disk));
        }
        for (LogiqxSample sample : p.getSamples()) {
            r.addFile(GameFile.fromLogiqx(sample));
        }
        return r;
    }

    public static Game fromCMPro(CMProGame p) {
        Game r = new Game(p.getName(), null, p.getDescription(), p.getYear(), p.getManufacturer(), p.getCloneof(),
                p.getRomof(), null, null);
        for (CMProRom rom : p.getRoms()) {
            r.addFile(GameFile.fromCMPro(rom));
        }
        for (CMProDisk disk : p.getDisks()) {
            r.addFile(GameFile.fromCMPro(disk));
        }
        for (String sample : p.getSamples()) {
            r.addFile(new GameFile(GameFileTypeEnum.SAMPLE.name(), sample));
        }
        if (p.getSampleof() != null && !p.getSampleof().isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (String s : p.getSampleof()) {
                if (sb.length() > 0)
                    sb.append(",");
                sb.append(s);
            }
            r.setSampleof(sb.toString());
        }
        return r;
    }

    public LogiqxGame toLogiqx() {
        LogiqxGame r = new LogiqxGame(name, null, isbios, cloneof, romof, sampleof, null, null, comment, description,
                year, manufacturer);
        for (Release release : this.releases) {
            r.addRelease(release.toLogiqx());
        }
        for (GameFile file : this.files) {
            if (file.getType().equals(GameFileTypeEnum.ROM.name())) {
                r.addRom(file.toLogiqxRom());
            } else if (file.getType().equals(GameFileTypeEnum.DISK.name())) {
                r.addDisk(file.toLogiqxDisk());
            } else if (file.getType().equals(GameFileTypeEnum.SAMPLE.name())) {
                r.addSample(file.toLogiqxSample());
            }
        }
        return r;
    }

    public CMProGame toCMPro() {
        CMProGame r = new CMProGame(this.name, this.description, this.year, this.manufacturer, this.cloneof, this.romof);
        for (GameFile file : this.files) {
            if (file.getType().equals(GameFileTypeEnum.ROM.name())) {
                r.addRom(file.toCMProRom());
            } else if (file.getType().equals(GameFileTypeEnum.DISK.name())) {
                r.addDisk(file.toCMProDisk());
            }
        }
        if (this.sampleof != null) {
            StringTokenizer st = new StringTokenizer(this.sampleof, ",");
            while (st.hasMoreTokens()) {
                r.addSampleOf(st.nextToken());
            }
        }
        return r;
    }

    public boolean addFile(GameFile file) {
        return this.files.add(file);
    }

    public boolean addRelease(Release release) {
        return this.releases.add(release);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsbios() {
        return isbios;
    }

    public void setIsbios(String isbios) {
        this.isbios = isbios;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getCloneof() {
        return cloneof;
    }

    public void setCloneof(String cloneof) {
        this.cloneof = cloneof;
    }

    public String getRomof() {
        return romof;
    }

    public void setRomof(String romof) {
        this.romof = romof;
    }

    public String getSampleof() {
        return sampleof;
    }

    public void setSampleof(String sampleof) {
        this.sampleof = sampleof;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Set<GameFile> getFiles() {
        return files;
    }

    public void setFiles(Set<GameFile> files) {
        this.files = files;
    }

    public Set<Release> getReleases() {
        return releases;
    }

    public void setReleases(Set<Release> releases) {
        this.releases = releases;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Game game = (Game) o;
        return Objects.equals(name, game.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
