package com.javanei.retrocenter.datafile;

import com.javanei.retrocenter.mame.Mame;
import com.javanei.retrocenter.mame.MameDisk;
import com.javanei.retrocenter.mame.MameMachine;
import com.javanei.retrocenter.mame.MameRom;
import com.javanei.retrocenter.mame.MameSample;
import java.util.Iterator;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class FromMameTest {
    private static Mame mame;
    private static Datafile datafile;

    @BeforeClass
    public static void initialize() throws Exception {
        mame = new Mame("0.186", "no", "10");
        MameMachine machine = new MameMachine("machine 01", null, "no", "no", "no", "yes", "cloneof teste", "romof teste",
                "sampleof teste", "machine 01", "2017", "javanei");
        mame.addMachine(machine);

        MameRom rom = new MameRom("rom 01", "false", 1l, "12345678",
                "1234567890123456789012345678901234567890", "no", "region 01", "offset 01",
                "good", "true");
        machine.addRom(rom);

        MameDisk disk = new MameDisk("disk 01", "1234567890123456789012345678901234567890", "yes", "region 01", 1, "no",
                "baddump", "yes");
        machine.addDisk(disk);

        MameSample sample = new MameSample("sample 01");
        machine.addSample(sample);

        datafile = Datafile.fromMame(mame);
    }

    @Test
    public void testName() {
        Assert.assertEquals("MAME", datafile.getName());
    }

    @Test
    public void testVersion() {
        Assert.assertEquals("0.186", datafile.getVersion());
    }

    @Test
    public void testCategory() {
        Assert.assertEquals("MAME", datafile.getCategory());
    }

    @Test
    public void testMachine() {
        Assert.assertEquals("Machines", 1, datafile.getGames().size());

        Game game = datafile.getGames().iterator().next();
        Assert.assertEquals("Game : Name", "machine 01", game.getName());
        Assert.assertEquals("Game : cloneof", "cloneof teste", game.getCloneof());
        Assert.assertEquals("Game : description", "machine 01", game.getDescription());
        Assert.assertEquals("Game : isbios", "no", game.getIsbios());
        Assert.assertEquals("Game : manufacturer", "javanei", game.getManufacturer());
        Assert.assertEquals("Game : romof", "romof teste", game.getRomof());
        Assert.assertEquals("Game : sampleof", "sampleof teste", game.getSampleof());
        Assert.assertEquals("Game : year", "2017", game.getYear());
    }

    @Test
    public void testRom() {
        Game game = datafile.getGames().iterator().next();
        Iterator<GameFile> it = game.getFiles().iterator();
        GameFile gameFile = it.next();
        while (gameFile != null && !gameFile.getType().equals(GameFileTypeEnum.ROM.name()) && it.hasNext()) {
            gameFile = it.next();
        }

        Assert.assertNotNull("ROM", gameFile);
        Assert.assertEquals("Name", "rom 01", gameFile.getName());
        Assert.assertEquals("Size", Long.valueOf(1l), gameFile.getSize());
        Assert.assertEquals("CRC", "12345678", gameFile.getCrc());
        Assert.assertEquals("SHA1", "1234567890123456789012345678901234567890", gameFile.getSha1());
        Assert.assertNull("MD5", gameFile.getMd5());
        Assert.assertEquals("Status", "good", gameFile.getStatus());
        Assert.assertNull("Date", gameFile.getDate());
        Assert.assertEquals("Merge", "no", gameFile.getMerge());
        Assert.assertEquals("Region", "region 01", gameFile.getRegion());
    }

    @Test
    public void testDisk() {
        Game game = datafile.getGames().iterator().next();
        Iterator<GameFile> it = game.getFiles().iterator();
        GameFile gameFile = it.next();
        while (gameFile != null && !gameFile.getType().equals(GameFileTypeEnum.DISK.name()) && it.hasNext()) {
            gameFile = it.next();
        }

        Assert.assertNotNull("Disk", gameFile);
        Assert.assertEquals("Name", "disk 01", gameFile.getName());
        Assert.assertNull("Size", gameFile.getSize());
        Assert.assertNull("CRC", gameFile.getCrc());
        Assert.assertEquals("SHA1", "1234567890123456789012345678901234567890", gameFile.getSha1());
        Assert.assertNull("MD5", gameFile.getMd5());
        Assert.assertEquals("Merge", "yes", gameFile.getMerge());
        Assert.assertEquals("Status", "baddump", gameFile.getStatus());
        Assert.assertNull("Date", gameFile.getDate());
        Assert.assertEquals("Region", "region 01", gameFile.getRegion());
    }

    @Test
    public void testSample() {
        Game game = datafile.getGames().iterator().next();
        Iterator<GameFile> it = game.getFiles().iterator();
        GameFile gameFile = it.next();
        while (gameFile != null && !gameFile.getType().equals(GameFileTypeEnum.SAMPLE.name()) && it.hasNext()) {
            gameFile = it.next();
        }

        Assert.assertNotNull("Sample", gameFile);
        Assert.assertEquals("Name", "sample 01", gameFile.getName());
        Assert.assertNull("Size", gameFile.getSize());
        Assert.assertNull("CRC", gameFile.getCrc());
        Assert.assertNull("SHA1", gameFile.getSha1());
        Assert.assertNull("MD5", gameFile.getMd5());
        Assert.assertNull("Merge", gameFile.getMerge());
        Assert.assertNull("Status", gameFile.getStatus());
        Assert.assertNull("Date", gameFile.getDate());
        Assert.assertNull("Region", gameFile.getRegion());
    }
}
