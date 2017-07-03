package com.javanei.retrocenter.clrmamepro;

import com.javanei.retrocenter.common.DuplicatedItemException;
import com.javanei.retrocenter.datafile.Artifact;
import com.javanei.retrocenter.datafile.ArtifactFile;
import com.javanei.retrocenter.datafile.ArtifactFileTypeEnum;
import com.javanei.retrocenter.datafile.Datafile;
import com.javanei.retrocenter.datafile.DatafileObject;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * http://www.logiqx.com/DatFAQs/CMPro.php
 */
public class CMProDatafile implements DatafileObject, Serializable {
    private static final long serialVersionUID = 1L;
    private CMProHeader header;
    private Set<CMProGame> games = new HashSet<>();
    private Set<CMProResource> resources = new HashSet<>();

    public CMProDatafile() {
    }

    public CMProDatafile(CMProHeader header) {
        this.header = header;
    }

    public CMProHeader getHeader() {
        return header;
    }

    public void setHeader(CMProHeader header) {
        if (this.header != null) {
            throw new DuplicatedItemException("header");
        }
        this.header = header;
    }

    public Set<CMProGame> getGames() {
        return games;
    }

    public void setGames(Set<CMProGame> games) {
        this.games = games;
    }

    public Set<CMProResource> getResources() {
        return resources;
    }

    public void setResources(Set<CMProResource> resources) {
        this.resources = resources;
    }

    public boolean addGame(CMProGame game) {
        return this.games.add(game);
    }

    public boolean addResource(CMProResource resource) {
        return this.resources.add(resource);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.header.toString());
        if (this.games != null) {
            for (CMProGame game : this.games) {
                sb.append(game);
            }
        }
        if (this.resources != null) {
            for (CMProResource resource : this.resources) {
                sb.append(resource);
            }
        }
        return sb.toString();
    }

    @Override
    public Datafile toDatafile() {
        Datafile r = new Datafile(header.getName(), header.getCategory(), header.getVersion(),
                header.getDescription(), header.getAuthor(), null,
                null, header.getHomepage(), header.getUrl(), null);
        for (CMProGame game : this.getGames()) {
            Artifact rGame = new Artifact(game.getName(), game.getDescription(), game.getYear(), null);
            rGame.setManufacturer(game.getManufacturer());
            rGame.setCloneof(game.getCloneof());
            rGame.setRomof(game.getRomof());
            for (CMProRom rom : game.getRoms()) {
                rGame.addFile(new ArtifactFile(ArtifactFileTypeEnum.ROM.name(), rom.getName(),
                        rom.getSize() != null ? rom.getSize().toString() : null, rom.getCrc(),
                        rom.getSha1(), rom.getMd5(), rom.getFlags(), null, null, rom.getRegion()));
            }
            for (CMProDisk disk : game.getDisks()) {
                rGame.addFile(new ArtifactFile(ArtifactFileTypeEnum.DISK.name(), disk.getName(), null, null,
                        disk.getSha1(), disk.getMd5(), null, null, null, null));
            }
            for (String sample : game.getSamples()) {
                rGame.addFile(new ArtifactFile(ArtifactFileTypeEnum.SAMPLE.name(), sample));
            }
            if (game.getSampleof() != null && !game.getSampleof().isEmpty()) {
                StringBuilder sb = new StringBuilder();
                for (String s : game.getSampleof()) {
                    if (sb.length() > 0)
                        sb.append(",");
                    sb.append(s);
                }
                rGame.setSampleof(sb.toString());
            }
            r.addArtifact(rGame);
        }
        return r;
    }

    @Override
    public String toFile() {
        return toString();
    }
}
