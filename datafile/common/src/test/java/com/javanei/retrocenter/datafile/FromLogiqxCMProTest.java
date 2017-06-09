package com.javanei.retrocenter.datafile;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.javanei.retrocenter.logiqx.LogiqxDatafile;
import com.javanei.retrocenter.logiqx.LogiqxHeader;

public class FromLogiqxCMProTest {
    private static LogiqxDatafile logiqxCMPro;
    private static Datafile datafileCMPro;

    @BeforeClass
    public static void initialize() throws Exception {
        logiqxCMPro = new LogiqxDatafile();

        LogiqxHeader header = new LogiqxHeader("name 001", "description 001", "category 001", "version 001",
                "date 001", "author 001", "email 001", "homepage 001", "url 001", "comment 001");
        header.setHeader("header 001");
        header.setForcemerging("none");
        logiqxCMPro.setHeader(header);

        datafileCMPro = Datafile.fromLogiqx(logiqxCMPro);
    }

    @Test
    public void testHeaderCMProHeader() {
        Assert.assertEquals("header 001", datafileCMPro.getHeader().getCustomFields().get("header"));
    }

    @Test
    public void testHeaderCMProForcemerging() {
        Assert.assertEquals("none", datafileCMPro.getHeader().getCustomFields().get("forcemerging"));
    }

    @Test
    public void testHeaderCMProForcenodump() {
        Assert.assertEquals("obsolete", datafileCMPro.getHeader().getCustomFields().get("forcenodump"));
    }

    @Test
    public void testHeaderCMProForcePacking() {
        Assert.assertEquals("zip", datafileCMPro.getHeader().getCustomFields().get("forcepacking"));
    }
}
