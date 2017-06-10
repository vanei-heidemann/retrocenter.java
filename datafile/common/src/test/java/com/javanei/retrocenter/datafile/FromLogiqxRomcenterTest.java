package com.javanei.retrocenter.datafile;

import com.javanei.retrocenter.logiqx.LogiqxDatafile;
import com.javanei.retrocenter.logiqx.LogiqxHeader;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class FromLogiqxRomcenterTest {
    private static LogiqxDatafile logiqxRetrocenter;
    private static Datafile datafileRetrocenter;

    @BeforeClass
    public static void initialize() throws Exception {
        logiqxRetrocenter = new LogiqxDatafile();

        LogiqxHeader header = new LogiqxHeader("name 001", "description 001", "category 001", "version 001",
                "date 001", "author 001", "email 001", "homepage 001", "url 001", "comment 001");
        header.setPlugin("plugin 001");
        header.setRommode("merged");
        logiqxRetrocenter.setHeader(header);

        datafileRetrocenter = Datafile.fromLogiqx(logiqxRetrocenter);
    }

    @Test
    public void testHeaderRomcenterPlugin() {
        Assert.assertEquals("plugin 001", datafileRetrocenter.getHeader().getCustomFields().get("plugin"));
    }

    @Test
    public void testHeaderRomcenterRommode() {
        Assert.assertEquals("merged", datafileRetrocenter.getHeader().getCustomFields().get("rommode"));
    }

    @Test
    public void testHeaderRomcenterBiosmode() {
        Assert.assertEquals("split", datafileRetrocenter.getHeader().getCustomFields().get("biosmode"));
    }

    @Test
    public void testHeaderRomcenterSamplemode() {
        Assert.assertEquals("merged", datafileRetrocenter.getHeader().getCustomFields().get("samplemode"));
    }

    @Test
    public void testHeaderRomcenterLockrommode() {
        Assert.assertEquals("no", datafileRetrocenter.getHeader().getCustomFields().get("lockrommode"));
    }

    @Test
    public void testHeaderRomcenterLockbiosmode() {
        Assert.assertEquals("no", datafileRetrocenter.getHeader().getCustomFields().get("lockbiosmode"));
    }

    @Test
    public void testHeaderRomcenterLocksamplemode() {
        Assert.assertEquals("no", datafileRetrocenter.getHeader().getCustomFields().get("locksamplemode"));
    }
}
