package com.javanei.retrocenter.mame.parser;

import com.javanei.retrocenter.mame.Mame;
import java.io.InputStream;
import org.junit.Assert;

public class MameParserTest {
    private static Mame mame;

    //@BeforeClass
    public static void initialize() throws Exception {
        MameParser parser = new MameParser();

        InputStream is = MameParserTest.class.getClassLoader().getResourceAsStream("mame0186.xml");
        mame = parser.parse(is);
        //System.out.println(mame);
    }

    //@Test
    public void test() {
        //System.out.println(mame);
        Assert.assertTrue(true);
    }
}
