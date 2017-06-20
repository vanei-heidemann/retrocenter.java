package com.javanei.retrocenter.datafile;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.javanei.retrocenter.mame.Mame;
import com.javanei.retrocenter.mame.MameMachine;

public class FromMameTest {
    private static Mame mame;
    private static Datafile datafile;

    @BeforeClass
    public static void initialize() throws Exception {
        mame = new Mame("0.186", "no", "10");
        mame.addMachine(new MameMachine("machine 01", null, "no", "no", "no", "yes", "cloneof teste", "romof teste",
                "sampleof teste", "machine 01", "2017", "javanei"));

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
}
