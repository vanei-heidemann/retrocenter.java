package com.javanei.retrocenter.datafile.service;

import com.javanei.retrocenter.common.ArtifactFileTypeEnum;
import com.javanei.retrocenter.common.DatafileCatalogEnum;
import com.javanei.retrocenter.datafile.Datafile;
import com.javanei.retrocenter.datafile.DatafileArtifact;
import com.javanei.retrocenter.datafile.DatafileArtifactFile;
import com.javanei.retrocenter.datafile.Release;
import com.javanei.retrocenter.datafile.entity.DatafileArtifactEntity;
import com.javanei.retrocenter.datafile.entity.DatafileArtifactFileEntity;
import com.javanei.retrocenter.datafile.entity.DatafileEntity;
import com.javanei.retrocenter.datafile.entity.DatafileReleaseEntity;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DatafileServiceConfiguration.class})
public class RetrocenterDatafileServiceTest {
    private static Datafile datafile;
    @Autowired
    private RetrocenterDatafileService retrocenterDatafileService;

    @BeforeClass
    public static void initialize() throws Exception {
        datafile = new Datafile("name 01", "no-intro", "1.00",
                "description 01", "author 01", "2017", "teste@teste.com",
                "homepage 01", "http://www.teste.com", "comment 01");

        DatafileArtifact game = new DatafileArtifact("game 01", "description 01", "2017", "game comment 01");
        game.setIsbios("no");
        game.setCloneof("cloneof 01");
        game.setRomof("romof 01");
        game.setSampleof("sampleof 01");
        game.setManufacturer("manufacturer 01");

        DatafileArtifactFile gf = new DatafileArtifactFile(ArtifactFileTypeEnum.ROM.name(), "file 01", "100", "12345678",
                "1234567890123456789012345678901234567890", "12345678901234567890123456789012",
                "2016");
        gf.setStatus("baddump");
        gf.setMerge("merge 01");
        gf.setRegion("main");
        game.addFile(gf);
        gf = new DatafileArtifactFile(ArtifactFileTypeEnum.DISK.name(), "file 02", "200", null,
                "1234567890123456789012345678901234567890", "12345678901234567890123456789012",
                null);
        game.addFile(gf);
        gf = new DatafileArtifactFile(ArtifactFileTypeEnum.SAMPLE.name(), "sample 01");
        game.addFile(gf);

        Release release = new Release("release 01", "Brazil", "br", "2016", "yes");
        game.addRelease(release);

        datafile.addArtifact(game);
    }

    @Test
    public void create() {
        DatafileEntity d = retrocenterDatafileService.create(datafile);
        Assert.assertEquals("name", "name 01", d.getName());
        Assert.assertEquals("catalog", DatafileCatalogEnum.NoIntro.name(), d.getCatalog());
        Assert.assertEquals("version", "1.00", d.getVersion());
        Assert.assertEquals("description", "description 01", d.getDescription());
        Assert.assertEquals("author", "author 01", d.getAuthor());
        Assert.assertEquals("date", "2017", d.getDate());
        Assert.assertEquals("email", "teste@teste.com", d.getEmail());
        Assert.assertEquals("homepage", "homepage 01", d.getHomepage());
        Assert.assertEquals("url", "http://www.teste.com", d.getUrl());
        Assert.assertEquals("comment", "comment 01", d.getComment());
    }

    @Test
    public void createGame() {
        DatafileEntity d = retrocenterDatafileService.create(datafile);
        DatafileArtifactEntity game = d.getArtifacts().iterator().next();
        Assert.assertEquals("name", "game 01", game.getName());
        Assert.assertEquals("isbios", "no", game.getIsbios());
        Assert.assertEquals("description", "description 01", game.getDescription());
        Assert.assertEquals("year", "2017", game.getYear());
        Assert.assertEquals("manufacturer", "manufacturer 01", game.getManufacturer());
        Assert.assertEquals("cloneof", "cloneof 01", game.getCloneof());
        Assert.assertEquals("romof", "romof 01", game.getRomof());
        Assert.assertEquals("sampleof", "sampleof 01", game.getSampleof());
        Assert.assertEquals("comment", "game comment 01", game.getComment());
    }

    @Test
    public void createRom() {
        DatafileArtifactFileEntity gf = null;
        DatafileEntity d = retrocenterDatafileService.create(datafile);
        DatafileArtifactEntity game = d.getArtifacts().iterator().next();
        for (DatafileArtifactFileEntity g : game.getFiles()) {
            if (g.getType().equals(ArtifactFileTypeEnum.ROM.name())) {
                gf = g;
                break;
            }
        }

        Assert.assertEquals("type", ArtifactFileTypeEnum.ROM.name(), gf.getType());
        Assert.assertEquals("name", "file 01", gf.getName());
        Assert.assertEquals("size", "100", gf.getSize());
        Assert.assertEquals("crc", "12345678", gf.getCrc());
        Assert.assertEquals("sha1", "1234567890123456789012345678901234567890", gf.getSha1());
        Assert.assertEquals("md5", "12345678901234567890123456789012", gf.getMd5());
        Assert.assertEquals("status", "baddump", gf.getStatus());
        Assert.assertEquals("date", "2016", gf.getDate());
        Assert.assertEquals("merge", "merge 01", gf.getMerge());
        Assert.assertEquals("region", "main", gf.getRegion());
    }

    @Test
    public void createDisk() {
        DatafileArtifactFileEntity gf = null;
        DatafileEntity d = retrocenterDatafileService.create(datafile);
        DatafileArtifactEntity game = d.getArtifacts().iterator().next();
        for (DatafileArtifactFileEntity g : game.getFiles()) {
            if (g.getType().equals(ArtifactFileTypeEnum.DISK.name())) {
                gf = g;
                break;
            }
        }

        Assert.assertEquals("type", ArtifactFileTypeEnum.DISK.name(), gf.getType());
        Assert.assertEquals("name", "file 02", gf.getName());
        Assert.assertEquals("size", "200", gf.getSize());
        Assert.assertNull("crc", gf.getCrc());
        Assert.assertEquals("sha1", "1234567890123456789012345678901234567890", gf.getSha1());
        Assert.assertEquals("md5", "12345678901234567890123456789012", gf.getMd5());
        Assert.assertNull("status", gf.getStatus());
        Assert.assertNull("date", gf.getDate());
        Assert.assertNull("merge", gf.getMerge());
        Assert.assertNull("region", gf.getRegion());
    }

    @Test
    public void createSample() {
        DatafileArtifactFileEntity gf = null;
        DatafileEntity d = retrocenterDatafileService.create(datafile);
        DatafileArtifactEntity game = d.getArtifacts().iterator().next();
        for (DatafileArtifactFileEntity g : game.getFiles()) {
            if (g.getType().equals(ArtifactFileTypeEnum.SAMPLE.name())) {
                gf = g;
                break;
            }
        }

        Assert.assertEquals("type", ArtifactFileTypeEnum.SAMPLE.name(), gf.getType());
        Assert.assertEquals("name", "sample 01", gf.getName());
        Assert.assertNull("size", gf.getSize());
        Assert.assertNull("crc", gf.getCrc());
        Assert.assertNull("sha1", gf.getSha1());
        Assert.assertNull("md5", gf.getMd5());
        Assert.assertNull("status", gf.getStatus());
        Assert.assertNull("date", gf.getDate());
        Assert.assertNull("merge", gf.getMerge());
        Assert.assertNull("region", gf.getRegion());
    }

    @Test
    public void createRelease() {
        DatafileEntity d = retrocenterDatafileService.create(datafile);
        DatafileArtifactEntity game = d.getArtifacts().iterator().next();
        DatafileReleaseEntity release = game.getReleases().iterator().next();
        Assert.assertEquals("name", "release 01", release.getName());
        Assert.assertEquals("region", "Brazil", release.getRegion());
        Assert.assertEquals("language", "br", release.getLanguage());
        Assert.assertEquals("date", "2016", release.getDate());
        Assert.assertEquals("default", "yes", release.getDefault());
    }
}
