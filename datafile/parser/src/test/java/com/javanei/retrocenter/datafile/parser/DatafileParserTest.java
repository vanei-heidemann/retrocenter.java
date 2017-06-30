package com.javanei.retrocenter.datafile.parser;

import com.javanei.retrocenter.clrmamepro.CMProDatafile;
import com.javanei.retrocenter.datafile.Datafile;
import com.javanei.retrocenter.logiqx.LogiqxDatafile;
import com.javanei.retrocenter.mame.Mame;
import java.io.InputStream;
import org.junit.Assert;
import org.junit.Test;

public class DatafileParserTest {
    @Test
    public void testMame() throws Exception {
        DatafileParser parser = new DatafileParser();
        InputStream is = DatafileParserTest.class.getClassLoader().getResourceAsStream("mame.xml");

        Mame obj = (Mame) parser.parse(is);
        Assert.assertNotNull(obj);
    }

    @Test
    public void testCMPro() throws Exception {
        DatafileParser parser = new DatafileParser();
        InputStream is = DatafileParserTest.class.getClassLoader().getResourceAsStream("cmpro.dat");

        CMProDatafile obj = (CMProDatafile) parser.parse(is);
        Assert.assertNotNull(obj);
    }

    @Test
    public void testLogiqx() throws Exception {
        DatafileParser parser = new DatafileParser();
        InputStream is = DatafileParserTest.class.getClassLoader().getResourceAsStream("logiqx.xml");

        LogiqxDatafile obj = (LogiqxDatafile) parser.parse(is);
        Assert.assertNotNull(obj);
    }

    @Test
    public void testRetrocenter() throws Exception {
        DatafileParser parser = new DatafileParser();
        InputStream is = DatafileParserTest.class.getClassLoader().getResourceAsStream("retrocenter.xml");

        Datafile obj = (Datafile) parser.parse(is);
        Assert.assertNotNull(obj);
    }
}
