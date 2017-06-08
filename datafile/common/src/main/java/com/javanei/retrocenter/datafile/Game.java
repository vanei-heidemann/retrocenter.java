package com.javanei.retrocenter.datafile;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.StringTokenizer;

import com.javanei.retrocenter.clrmamepro.CMProDisk;
import com.javanei.retrocenter.clrmamepro.CMProGame;
import com.javanei.retrocenter.clrmamepro.CMProRom;
import com.javanei.retrocenter.logiqx.LogiqxArchive;
import com.javanei.retrocenter.logiqx.LogiqxBiosset;
import com.javanei.retrocenter.logiqx.LogiqxDisk;
import com.javanei.retrocenter.logiqx.LogiqxGame;
import com.javanei.retrocenter.logiqx.LogiqxRelease;
import com.javanei.retrocenter.logiqx.LogiqxRom;
import com.javanei.retrocenter.logiqx.LogiqxSample;

public class Game implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * logiqx.game.name, clrmamepro.game.name
     */
    private String name;
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
     * logiqx.game.isbios
     */
    private String isbios = "no";

    /**
     * logiqx.game.comment
     */
    private String comment;
    /**
     * logiqx.game.sourcefile
     */
    private String sourcefile;
    /**
     * logiqx.game.sampleof
     */
    private String sampleof;
    /**
     * logiqx.game.board
     */
    private String board;
    /**
     * logiqx.game.rebuildto
     */
    private String rebuildto;

    private Set<Release> releases = new HashSet<>();
    private Set<Biosset> biossets = new HashSet<>();
    private Set<Rom> roms = new HashSet<>();
    private Set<Disk> disks = new HashSet<>();
    private Set<Sample> samples = new HashSet<>();
    private Set<Archive> archives = new HashSet<>();

    public Game() {
    }

    public Game(String name, String description, String year, String manufacturer, String cloneof, String romof,
            String isbios, String comment, String sourcefile, String sampleof, String board, String rebuildto) {
        this.name = name;
        this.description = description;
        this.year = year;
        this.manufacturer = manufacturer;
        this.cloneof = cloneof;
        this.romof = romof;
        this.isbios = isbios;
        this.comment = comment;
        this.sourcefile = sourcefile;
        this.sampleof = sampleof;
        this.board = board;
        this.rebuildto = rebuildto;
    }

    public Game(String name, String description, String year, String manufacturer, String cloneof, String romof) {
        this.name = name;
        this.description = description;
        this.year = year;
        this.manufacturer = manufacturer;
        this.cloneof = cloneof;
        this.romof = romof;
    }

    public static Game fromLogiqx(LogiqxGame p) {
        Game r = new Game(p.getName(), p.getDescription(), p.getYear(), p.getManufacturer(), p.getCloneof(), p.getRomof(), p.getIsbios(), p.getComment(), p.getSourcefile(), p.getSampleof(), p.getBoard(), p.getRebuildto());
        for (LogiqxRelease release : p.getReleases()) {
            r.addRelease(Release.fromLogiqx(release));
        }
        for (LogiqxBiosset biosset : p.getBiossets()) {
            r.addBiosset(Biosset.fromLogiqx(biosset));
        }
        for (LogiqxRom rom : p.getRoms()) {
            r.addRom(Rom.fromLogiqx(rom));
        }
        for (LogiqxDisk disk : p.getDisks()) {
            r.addDisk(Disk.fromLogiqx(disk));
        }
        for (LogiqxSample sample : p.getSamples()) {
            r.addSample(Sample.fromLogiqx(sample));
        }
        for (LogiqxArchive archive : p.getArchives()) {
            r.addArchive(Archive.fromLogiqx(archive));
        }
        return r;
    }

    public static Game fromClrmamepro(CMProGame p) {
        Game r = new Game(p.getName(), p.getDescription(), p.getYear(), p.getManufacturer(), p.getCloneof(), p.getRomof());
        for (CMProRom rom : p.getRoms()) {
            r.addRom(Rom.fromClrmamepro(rom));
        }
        for (CMProDisk disk : p.getDisks()) {
            r.addDisk(Disk.fromClrmamepro(disk));
        }
        for (String sample : p.getSamples()) {
            r.addSample(new Sample(sample));
        }
        if (p.getSampleof() != null) {
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

    private static void appendTagIfNotNull(StringBuilder sb, String name, Object value) {
        if (value != null)
            sb.append("\t\t<").append(name).append(">").append(value).append("</").append(name).append(">\n");
    }

    private static void appendAttributeIfNotNull(StringBuilder sb, String name, Object value) {
        if (value != null)
            sb.append(" ").append(name).append("=\"").append(value).append("\"");
    }

    public LogiqxGame toLogiqx() {
        LogiqxGame r = this.isbios != null ? new LogiqxGame(this.name, this.sourcefile, this.isbios, this.cloneof,
                this.romof, this.sampleof, this.board, this.rebuildto, this.comment, this.description, this.year,
                this.manufacturer) :
                new LogiqxGame(this.name, this.sourcefile, this.cloneof, this.romof, this.sampleof,
                        this.board, this.rebuildto, this.comment, this.description, this.year, this.manufacturer);
        for (Release release : this.releases) {
            r.addRelease(release.toLogiqx());
        }
        for (Biosset biosset : this.biossets) {
            r.addBiosset(biosset.toLogiqx());
        }
        for (Rom rom : this.roms) {
            r.addRom(rom.toLogiqx());
        }
        for (Disk disk : this.disks) {
            r.addDisk(disk.toLogiqx());
        }
        for (Sample sample : this.samples) {
            r.addSample(sample.toLogiqx());
        }
        for (Archive archive : this.archives) {
            r.addArchive(archive.toLogiqx());
        }
        return r;
    }

    public CMProGame toClrmamepro() {
        CMProGame r = new CMProGame(this.name, this.description, this.year, this.manufacturer, this.cloneof, this.romof);
        for (Rom rom : this.roms) {
            r.addRom(rom.toClrmamepro());
        }
        for (Disk disk : this.disks) {
            r.addDisk(disk.toClrmamepro());
        }
        for (Sample sample : this.samples) {
            r.addSample(sample.getName());
        }
        if (this.sampleof != null) {
            StringTokenizer st = new StringTokenizer(this.sampleof, ",");
            while (st.hasMoreTokens()) {
                r.addSampleOf(st.nextToken());
            }
        }
        return r;
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

    public String getIsbios() {
        return isbios;
    }

    public void setIsbios(String isbios) {
        this.isbios = isbios;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getSourcefile() {
        return sourcefile;
    }

    public void setSourcefile(String sourcefile) {
        this.sourcefile = sourcefile;
    }

    public String getSampleof() {
        return sampleof;
    }

    public void setSampleof(String sampleof) {
        this.sampleof = sampleof;
    }

    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
    }

    public String getRebuildto() {
        return rebuildto;
    }

    public void setRebuildto(String rebuildto) {
        this.rebuildto = rebuildto;
    }

    public Set<Rom> getRoms() {
        return roms;
    }

    public void setRoms(Set<Rom> roms) {
        if (roms != null)
            this.roms = roms;
        else
            this.roms = new HashSet<>();
    }

    public void addRom(Rom rom) {
        if (this.roms.contains(rom))
            throw new IllegalArgumentException("Duplicated rom: " + rom.getName());
        this.roms.add(rom);
    }

    public Set<Disk> getDisks() {
        return disks;
    }

    public void setDisks(Set<Disk> disks) {
        if (disks != null)
            this.disks = disks;
        else
            this.disks = new HashSet<>();
    }

    public void addDisk(Disk disk) {
        if (this.disks.contains(disk))
            throw new IllegalArgumentException("Duplicated disk: " + disk.getName());
        this.disks.add(disk);
    }

    public Set<Release> getReleases() {
        return releases;
    }

    public void setReleases(Set<Release> releases) {
        if (releases != null)
            this.releases = releases;
        else
            this.releases = new HashSet<>();
    }

    public void addRelease(Release release) {
        if (this.releases.contains(release))
            throw new IllegalArgumentException("Duplicated release: " + release.getName() + "/" + release.getDate()
                    + "/" + release.getLanguage() + "/" + release.getRegion());
        this.releases.add(release);
    }

    public Set<Biosset> getBiossets() {
        return biossets;
    }

    public void setBiossets(Set<Biosset> biossets) {
        if (biossets != null)
            this.biossets = biossets;
        else
            this.biossets = new HashSet<>();
    }

    public void addBiosset(Biosset biosset) {
        if (this.biossets.contains(biosset))
            throw new IllegalArgumentException("Duplicated biosset: " + biosset.getName());
    }

    public Set<Sample> getSamples() {
        return samples;
    }

    public void setSamples(Set<Sample> samples) {
        if (samples != null)
            this.samples = samples;
        else
            this.samples = new HashSet<>();
    }

    public void addSample(Sample sample) {
        if (this.samples.contains(sample))
            throw new IllegalArgumentException("Duplicated sample: " + sample.getName());
    }

    public Set<Archive> getArchives() {
        return archives;
    }

    public void setArchives(Set<Archive> archives) {
        if (archives != null)
            this.archives = archives;
        else
            this.archives = new HashSet<>();
    }

    public void addArchive(Archive archive) {
        if (this.archives.contains(archive))
            throw new IllegalArgumentException("Duplicated archive: " + archive.getName());
        this.archives.add(archive);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Game game = (Game) o;
        return Objects.equals(name, game.name) &&
                Objects.equals(year, game.year) &&
                Objects.equals(manufacturer, game.manufacturer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, year, manufacturer);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\t<game");
        appendAttributeIfNotNull(sb, "name", this.name);
        appendAttributeIfNotNull(sb, "sourcefile", this.sourcefile);
        appendAttributeIfNotNull(sb, "isbios", this.isbios);
        appendAttributeIfNotNull(sb, "cloneof", this.cloneof);
        appendAttributeIfNotNull(sb, "romof", this.romof);
        appendAttributeIfNotNull(sb, "sampleof", this.sampleof);
        appendAttributeIfNotNull(sb, "board", this.board);
        appendAttributeIfNotNull(sb, "rebuildto", this.rebuildto);
        sb.append(">\n");
        appendTagIfNotNull(sb, "comment", comment);
        appendTagIfNotNull(sb, "description", description);
        appendTagIfNotNull(sb, "year", year);
        appendTagIfNotNull(sb, "manufacturer", manufacturer);
        for (Release release : this.releases) {
            sb.append(release.toString());
        }
        for (Biosset biosset : this.biossets) {
            sb.append(biosset.toString());
        }
        for (Rom rom : this.roms) {
            sb.append(rom.toString());
        }
        for (Disk disk : this.disks) {
            sb.append(disk.toString());
        }
        for (Sample sample : this.samples) {
            sb.append(sample.toString());
        }
        for (Archive archive : this.archives) {
            sb.append(archive.toString());
        }
        sb.append("\t</game>\n");
        return sb.toString();
    }
}
