package com.javanei.retrocenter.datafile;

import com.javanei.retrocenter.logiqx.LogiqxDatafile;
import com.javanei.retrocenter.logiqx.LogiqxHeader;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class FromLogiqxTest {
    private static LogiqxDatafile logiqx;
    private static Datafile datafile;

    @BeforeClass
    public static void initialize() throws Exception {
        logiqx = new LogiqxDatafile();

        LogiqxHeader header = new LogiqxHeader("name 001", "description 001", "category 001", "version 001",
                "date 001", "author 001", "email 001", "homepage 001", "url 001", "comment 001");
        logiqx.setHeader(header);

        datafile = Datafile.fromLogiqx(logiqx);
    }

    @Test
    public void testDebug() {
        Assert.assertEquals("no", datafile.getCustomAttribute("debug"));
    }

    @Test
    public void testHeaderName() {
        Assert.assertEquals("name 001", datafile.getHeader().getName());
    }

    @Test
    public void testHeaderDescription() {
        Assert.assertEquals("description 001", datafile.getHeader().getDescription());
    }

    @Test
    public void testHeaderCategory() {
        Assert.assertEquals("category 001", datafile.getHeader().getCategory());
    }

    @Test
    public void testHeaderVersion() {
        Assert.assertEquals("version 001", datafile.getHeader().getVersion());
    }

    @Test
    public void testHeaderDate() {
        Assert.assertEquals("date 001", datafile.getHeader().getDate());
    }

    @Test
    public void testHeaderAuthor() {
        Assert.assertEquals("author 001", datafile.getHeader().getAuthor());
    }

    @Test
    public void testHeaderEmail() {
        Assert.assertEquals("email 001", datafile.getHeader().getEmail());
    }

    @Test
    public void testHeaderHomepage() {
        Assert.assertEquals("homepage 001", datafile.getHeader().getHomepage());
    }

    @Test
    public void testHeaderUrl() {
        Assert.assertEquals("url 001", datafile.getHeader().getUrl());
    }

    @Test
    public void testHeaderComment() {
        Assert.assertEquals("comment 001", datafile.getHeader().getComment());
    }
}
