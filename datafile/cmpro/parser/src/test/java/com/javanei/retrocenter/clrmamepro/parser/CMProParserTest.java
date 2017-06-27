package com.javanei.retrocenter.clrmamepro.parser;

import com.javanei.retrocenter.clrmamepro.CMProDatafile;
import com.javanei.retrocenter.clrmamepro.CMProGame;
import java.io.InputStream;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CMProParserTest {
    private static CMProDatafile psvita;

    @BeforeClass
    public static void initialize() throws Exception {
        CMProParser parser = new CMProParser();

        InputStream is = CMProParserTest.class.getClassLoader().getResourceAsStream("Sony - PlayStation Vita (20170114-224204_CM).dat");
        psvita = parser.parse(is);
        System.out.println(psvita);
    }

    @Test
    public void test010HeaderName() throws Exception {
        Assert.assertTrue("Name", psvita.getHeader().getName().equals("Sony - PlayStation Vita"));
    }

    @Test
    public void test011HeaderDescription() {
        Assert.assertTrue("Description", psvita.getHeader().getDescription().equals("Sony - PlayStation Vita"));
    }

    @Test
    public void test012HeaderVersion() {
        Assert.assertTrue("Version", psvita.getHeader().getVersion().equals("20170114-224204"));
    }

    @Test
    public void test013HeaderAuthor() {
        Assert.assertTrue("Author", psvita.getHeader().getAuthor().equals("Densetsu, einstein95, Gefflon, Hiccup, jimmsu, Money_114, SonGoku, xuom2, zg"));
    }

    @Test
    public void test014CustomField() {
        Assert.assertTrue("Custom field url: ", psvita.getHeader().getCustomField("url").equals("http://www.no-intro.org"));
    }

    @Test
    public void test020QtdGames() {
        Assert.assertTrue("Games count", psvita.getGames().size() == 235);
    }

    @Test
    public void test030NoDump() {
        boolean ok = false;
        for (CMProGame game : psvita.getGames()) {
            if (game.getName().equals("Army Corps of Hell (USA)")) {
                ok = game.getRoms().size() > 0 && game.getRoms().iterator().next().isNodump();
                break;
            }
        }
        Assert.assertTrue("nodump", ok);
    }

    @Test
    public void test031FlagBaddump() {
        boolean ok = false;
        for (CMProGame game : psvita.getGames()) {
            if (game.getName().equals("Amazing Spider-Man, The (USA) [b]")) {
                ok = game.getRoms().size() > 0 && game.getRoms().iterator().next().getFlags().equals("baddump");
                break;
            }
        }
        Assert.assertTrue("baddump", ok);
    }

    /*
    @Test
    public void test100ParseAll() throws Exception {
        Map<String, String> headerCustomFields = new HashMap<>();
        Set<String> flags = new HashSet<>();

        CMProParser parser = new CMProParser();
        File[] files = new File("F:\\Desenv\\Fontes\\Java\\retrocenter\\resources\\no-intro").listFiles();
        for (File f : files) {
            System.out.println("======== Processing file: " + f.getAbsolutePath());
            CMProDatafile m = parser.parse(f);
            System.out.println(m);
            headerCustomFields.putAll(m.getHeader().getCustomFields());
            for (CMProGame game : m.getGames()) {
                for (CMProRom rom : game.getRoms()) {
                    if (rom.getFlags() != null) {
                        if (!flags.contains(rom.getFlags())) {
                            flags.add(rom.getFlags());
                        }
                    }
                }
            }
        }
        System.out.println(":::: Custom Fields:\n" + headerCustomFields);
        System.out.println(":::: flags:\n" + flags);
    }
    */
}
