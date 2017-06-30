package com.javanei.retrocenter.datafile.parser;

import java.io.InputStream;
import org.junit.Assert;
import org.junit.Test;

public class DatafileParserTest {
    @Test
    public void testMame() throws Exception {
        DatafileParser parser = new DatafileParser();
        InputStream is = DatafileParserTest.class.getClassLoader().getResourceAsStream("mame.xml");

        Object obj = parser.parse(is);
        Assert.assertNotNull(obj);
    }

    @Test
    public void testCMPro() throws Exception {
        DatafileParser parser = new DatafileParser();
        InputStream is = DatafileParserTest.class.getClassLoader().getResourceAsStream("cmpro.dat");

        Object obj = parser.parse(is);
        Assert.assertNotNull(obj);
    }

    @Test
    public void testLogiqx() throws Exception {
        DatafileParser parser = new DatafileParser();
        InputStream is = DatafileParserTest.class.getClassLoader().getResourceAsStream("logiqx.xml");

        Object obj = parser.parse(is);
        Assert.assertNotNull(obj);
    }
}
