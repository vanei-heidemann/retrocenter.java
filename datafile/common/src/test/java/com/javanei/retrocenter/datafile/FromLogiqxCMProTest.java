package com.javanei.retrocenter.datafile;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.javanei.retrocenter.logiqx.LogiqxDatafile;
import com.javanei.retrocenter.logiqx.LogiqxHeader;

public class FromLogiqxCMProTest {
    private static LogiqxDatafile logiqxCMPro;
    private static Datafile datafile;

    @BeforeClass
    public static void initialize() throws Exception {
        logiqxCMPro = new LogiqxDatafile();

        LogiqxHeader header = new LogiqxHeader("name 001", "description 001", "category 001", "version 001",
                "date 001", "author 001", "email 001", "homepage 001", "url 001", "comment 001");
        header.setHeader("header 001");
        header.setForcemerging("none");
        logiqxCMPro.setHeader(header);

        datafile = Datafile.fromLogiqx(logiqxCMPro);
    }


    @Test
    public void testHeaderName() {
        Assert.assertEquals("name 001", datafile.getName());
    }

    @Test
    public void testHeaderDescription() {
        Assert.assertEquals("description 001", datafile.getDescription());
    }

    @Test
    public void testHeaderCategory() {
        Assert.assertEquals("category 001", datafile.getCategory());
    }

    @Test
    public void testHeaderVersion() {
        Assert.assertEquals("version 001", datafile.getVersion());
    }

    @Test
    public void testHeaderDate() {
        Assert.assertEquals("date 001", datafile.getDate());
    }

    @Test
    public void testHeaderAuthor() {
        Assert.assertEquals("author 001", datafile.getAuthor());
    }

    @Test
    public void testHeaderEmail() {
        Assert.assertEquals("email 001", datafile.getEmail());
    }

    @Test
    public void testHeaderHomepage() {
        Assert.assertEquals("homepage 001", datafile.getHomepage());
    }

    @Test
    public void testHeaderUrl() {
        Assert.assertEquals("url 001", datafile.getUrl());
    }

    @Test
    public void testHeaderComment() {
        Assert.assertEquals("comment 001", datafile.getComment());
    }
}
