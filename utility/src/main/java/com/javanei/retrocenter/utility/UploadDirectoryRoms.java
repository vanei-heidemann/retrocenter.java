package com.javanei.retrocenter.utility;

import com.javanei.retrocenter.common.ArtifactFileTypeEnum;
import org.glassfish.jersey.media.multipart.MultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;

public class UploadDirectoryRoms {
    private static final String SERVER_URL = "http://localhost:8080/retrocenter/api/";
    private static final String TARGET_URL = SERVER_URL + "/platforms/";
    private static final boolean DELETE_FILE = false;
    private static final String[][] IMPORT = new String[][]{
            {"2", "G:\\NASBACKUP\\Download\\MAME 0.37b11_Full romset GP2X_Wiz MAME 2.0\\Current Roms", "MAME 0.37b11 Full romset GP2X_Wiz MAME 2.0 - ROMs"},
            {"2", "G:\\NASBACKUP\\Download\\MAME 0.37b11_Full romset GP2X_Wiz MAME 2.0\\Current Samples", "MAME 0.37b11 Full romset GP2X_Wiz MAME 2.0 - Samples"}
            //{"platformId", "path", null}
            //,{"platformId", "path", null}
/*
            ,{"3", "G:\\importar\\no-intro\\Coleco\\Coleco - ColecoVision - No-Intro (20140803-055913)", "Coleco - ColecoVision - No-Intro (20140803-055913)"}
            {"4", "G:\\importar\\tosec\\Atari\\2600 & VCS\\Atari 2600 & VCS - Applications (TOSEC-v2014-01-18)", null}
            ,{"4", "G:\\importar\\tosec\\Atari\\2600 & VCS\\Atari 2600 & VCS - Demos (TOSEC-v2011-02-22)", null}
            ,{"4", "G:\\importar\\tosec\\Atari\\2600 & VCS\\Atari 2600 & VCS - Educational (TOSEC-v2014-01-18)", null}
            ,{"4", "G:\\importar\\tosec\\Atari\\2600 & VCS\\Atari 2600 & VCS - Games (TOSEC-v2011-08-12)", null}
ERROR!!!            ,{"5", "G:\\importar\\no-intro\\Nintendo\\Nintendo - Super Nintendo Entertainment System - No-Intro (20141221-212957)", "Nintendo - Super Nintendo Entertainment System - No-Intro (20141221-212957)"}
            ,{"5", "G:\\importar\\no-intro\\Atari\\Atari - 5200 - No-Intro (20130303-220600)", "Atari - 5200 - No-Intro (20130303-220600)"}
            ,{"5", "G:\\importar\\tosec\\Atari\\5200\\Atari 5200 - Applications (TOSEC-v2013-12-13)", null}
            ,{"5", "G:\\importar\\tosec\\Atari\\5200\\Atari 5200 - Demos (TOSEC-v2013-12-13)", null}
            ,{"5", "G:\\importar\\tosec\\Atari\\5200\\Atari 5200 - Firmware (TOSEC-v2013-12-13)", null}
            ,{"5", "G:\\importar\\tosec\\Atari\\5200\\Atari 5200 - Games (TOSEC-v2013-12-13)", null}
            , {"6", "G:\\importar\\no-intro\\Atari\\Atari - 7800 - No-Intro (20140326-040142)", "Atari - 7800 - No-Intro (20140326-040142)"}
            ,{"6", "G:\\importar\\tosec\\Atari\\7800\\Atari 7800 - Applications (TOSEC-v2013-12-13)", null}
            ,{"6", "G:\\importar\\tosec\\Atari\\7800\\Atari 7800 - Firmware (TOSEC-v2013-12-13)", null}
            ,{"6", "G:\\importar\\tosec\\Atari\\7800\\Atari 7800 - Games (TOSEC-v2013-12-13)", null}
            ,{"7", "G:\\importar\\tosec\\Atari\\8bit\\Applications\\Atari 8bit - Applications - [ATR] (TOSEC-v2014-10-30)", null} // Atari 800
            ,{"7", "G:\\importar\\tosec\\Atari\\8bit\\Applications\\Atari 8bit - Applications - [ATX] (TOSEC-v2013-12-13)", null} // Atari 800
            ,{"7", "G:\\importar\\tosec\\Atari\\8bit\\Applications\\Atari 8bit - Applications - [BAS] (TOSEC-v2011-09-07)", null} // Atari 800
            ,{"7", "G:\\importar\\tosec\\Atari\\8bit\\Applications\\Atari 8bit - Applications - [BIN] (TOSEC-v2014-10-30)", null} // Atari 800
            ,{"7", "G:\\importar\\tosec\\Atari\\8bit\\Applications\\Atari 8bit - Applications - [CAS] (TOSEC-v2014-10-30)", null} // Atari 800
            ,{"7", "G:\\importar\\tosec\\Atari\\8bit\\Applications\\Atari 8bit - Applications - [XEX] (TOSEC-v2012-07-18)", null} // Atari 800
            ,{"7", "G:\\importar\\tosec\\Atari\\8bit\\Compilations\\Applications\\Atari 8bit - Compilations - Applications - [ATR] (TOSEC-v2013-12-13)", null} // Atari 800
            ,{"7", "G:\\importar\\tosec\\Atari\\8bit\\Compilations\\Demos\\Atari 8bit - Compilations - Demos - [ATR] (TOSEC-v2013-12-13)", null} // Atari 800
            ,{"7", "G:\\importar\\tosec\\Atari\\8bit\\Compilations\\Educational\\Atari 8bit - Compilations - Educational - [ATR] (TOSEC-v2013-12-13)", null} // Atari 800
            ,{"7", "G:\\importar\\tosec\\Atari\\8bit\\Compilations\\Educational\\Atari 8bit - Compilations - Educational - [CAS] (TOSEC-v2012-07-18)", null} // Atari 800
            ,{"7", "G:\\importar\\tosec\\Atari\\8bit\\Compilations\\Games\\Atari 8bit - Compilations - Games - [ATR] (TOSEC-v2012-07-18)", null} // Atari 800
            ,{"7", "G:\\importar\\tosec\\Atari\\8bit\\Compilations\\Games\\Atari 8bit - Compilations - Games - [ATX] (TOSEC-v2013-12-13)", null} // Atari 800
            ,{"7", "G:\\importar\\tosec\\Atari\\8bit\\Compilations\\Games\\Atari 8bit - Compilations - Games - [BAS] (TOSEC-v2012-07-18)", null} // Atari 800
            ,{"7", "G:\\importar\\tosec\\Atari\\8bit\\Compilations\\Games\\Atari 8bit - Compilations - Games - [CAS] (TOSEC-v2012-07-18)", null} // Atari 800
            ,{"7", "G:\\importar\\tosec\\Atari\\8bit\\Compilations\\Games\\Atari 8bit - Compilations - Games - [XEX] (TOSEC-v2011-08-25)", null} // Atari 800
            ,{"7", "G:\\importar\\tosec\\Atari\\8bit\\Demos\\Atari 8bit - Demos - [XEX] (TOSEC-v2014-10-30)", null} // Atari 800
            ,{"7", "G:\\importar\\tosec\\Atari\\8bit\\Demos\\Atari 8bit - Demos - [ATR] (TOSEC-v2014-10-30)", null} // Atari 800
            ,{"7", "G:\\importar\\tosec\\Atari\\8bit\\Docs\\Atari 8bit - Docs - [ATR] (TOSEC-v2013-12-13)", null} // Atari 800
            ,{"7", "G:\\importar\\tosec\\Atari\\8bit\\Docs\\Atari 8bit - Docs - [XEX] (TOSEC-v2013-12-13)", null} // Atari 800
            ,{"7", "G:\\importar\\tosec\\Atari\\8bit\\Educational\\Atari 8bit - Educational - [ATR] (TOSEC-v2012-07-18)", null} // Atari 800
            ,{"7", "G:\\importar\\tosec\\Atari\\8bit\\Educational\\Atari 8bit - Educational - [ATX] (TOSEC-v2011-09-07)", null} // Atari 800
            ,{"7", "G:\\importar\\tosec\\Atari\\8bit\\Educational\\Atari 8bit - Educational - [BAS] (TOSEC-v2012-07-18)", null} // Atari 800
            ,{"7", "G:\\importar\\tosec\\Atari\\8bit\\Educational\\Atari 8bit - Educational - [BIN] (TOSEC-v2014-10-30)", null} // Atari 800
            ,{"7", "G:\\importar\\tosec\\Atari\\8bit\\Educational\\Atari 8bit - Educational - [CAS] (TOSEC-v2012-07-18)", null} // Atari 800
            ,{"7", "G:\\importar\\tosec\\Atari\\8bit\\Educational\\Atari 8bit - Educational - [XEX] (TOSEC-v2012-07-18)", null} // Atari 800
            ,{"7", "G:\\importar\\tosec\\Atari\\8bit\\Games\\Atari 8bit - Games - [ATR] (TOSEC-v2014-10-30)", null} // Atari 800
            ,{"7", "G:\\importar\\tosec\\Atari\\8bit\\Games\\Atari 8bit - Games - [ATX] (TOSEC-v2012-07-18)", null} // Atari 800
            ,{"7", "G:\\importar\\tosec\\Atari\\8bit\\Games\\Atari 8bit - Games - [BAS] (TOSEC-v2012-07-18)", null} // Atari 800
            ,{"7", "G:\\importar\\tosec\\Atari\\8bit\\Games\\Atari 8bit - Games - [BIN] (TOSEC-v2014-10-30)", null} // Atari 800
            ,{"7", "G:\\importar\\tosec\\Atari\\8bit\\Games\\Atari 8bit - Games - [CAS] (TOSEC-v2014-10-30)", null} // Atari 800
            ,{"7", "G:\\importar\\tosec\\Atari\\8bit\\Games\\Atari 8bit - Games - [XEX] (TOSEC-v2014-10-30)", null} // Atari 800
            ,{"7", "G:\\importar\\tosec\\Atari\\8bit\\Magazines\\Atari 8bit - Magazines - [XEX] (TOSEC-v2013-12-13)", null} // Atari 800
            ,{"7", "G:\\importar\\tosec\\Atari\\8bit\\Magazines\\Atari 8bit - Magazines - [ATR] (TOSEC-v2014-10-30)", null} // Atari 800
            ,{"7", "G:\\importar\\tosec\\Atari\\8bit\\Sources\\Atari 8bit - Sources - [ASM] (TOSEC-v2013-12-13)", null} // Atari 800
            ,{"7", "G:\\importar\\tosec\\Atari\\8bit\\Sources\\Atari 8bit - Sources - [ATR] (TOSEC-v2013-12-13)", null} // Atari 800
            ,{"7", "G:\\importar\\tosec\\Atari\\8bit\\Sources\\Atari 8bit - Sources - [CAS] (TOSEC-v2013-12-13)", null} // Atari 800
            ,{"7", "G:\\importar\\tosec\\Atari\\8bit\\Atari 8bit - Operating Systems (TOSEC-v2014-10-30)", null} // Atari 800
            ,{"8", "G:\\importar\\no-intro\\Atari\\Atari - Jaguar - No-Intro (20130217-102044)", "Atari - Jaguar - No-Intro (20130217-102044)"}
            ,{"8", "G:\\importar\\tosec\\Atari\\Jaguar\\Atari Jaguar - Applications (TOSEC-v2013-12-13)", null}
            ,{"8", "G:\\importar\\tosec\\Atari\\Jaguar\\Atari Jaguar - Demos (TOSEC-v2013-12-13)", null}
            ,{"8", "G:\\importar\\tosec\\Atari\\Jaguar\\Atari Jaguar - Firmware (TOSEC-v2013-12-13)", null}
            ,{"8", "G:\\importar\\tosec\\Atari\\Jaguar\\Atari Jaguar - Games (TOSEC-v2013-12-13)", null}
            ,{"10", "G:\\importar\\no-intro\\Atari\\Atari - Lynx - No-Intro (20130303-222635)", "Atari - Lynx - No-Intro (20130303-222635)"}
            ,{"10", "G:\\importar\\tosec\\Atari\\Lynx\\Atari Lynx - Applications (TOSEC-v2013-12-13)", null}
            ,{"10", "G:\\importar\\tosec\\Atari\\Lynx\\Atari Lynx - Demos (TOSEC-v2013-12-13)", null}
            ,{"10", "G:\\importar\\tosec\\Atari\\Lynx\\Atari Lynx - Firmware (TOSEC-v2013-12-13)", null}
            ,{"10", "G:\\importar\\tosec\\Atari\\Lynx\\Atari Lynx - Games (TOSEC-v2013-12-13)", null}
            ,{"11", "G:\\importar\\no-intro\\Atari\\Atari - ST - No-Intro (20141101-034609)", "Atari - ST - No-Intro (20141101-034609)"}
            ,{"11", "G:\\importar\\tosec\\Atari\\ST\\Applications\\Atari ST - Applications - [ST] (TOSEC-v2014-10-28)", null}
            ,{"11", "G:\\importar\\tosec\\Atari\\ST\\Applications\\Atari ST - Applications - [STX] (TOSEC-v2013-12-13)", null}
            ,{"11", "G:\\importar\\tosec\\Atari\\ST\\Atari ST - Coverdisks (TOSEC-v2011-08-31)", null}
            ,{"11", "G:\\importar\\tosec\\Atari\\ST\\Atari ST - Docs (TOSEC-v2011-03-04)", null}
            ,{"11", "G:\\importar\\tosec\\Atari\\ST\\Atari ST - Online Magazines (TOSEC-v2013-12-13)", null}
            ,{"11", "G:\\importar\\tosec\\Atari\\ST\\Collections\\Atari ST - Collections - 4U (TOSEC-v2013-12-13)", null}
            ,{"11", "G:\\importar\\tosec\\Atari\\ST\\Collections\\Atari ST - Collections - ACN (TOSEC-v2013-12-13)", null}
            ,{"11", "G:\\importar\\tosec\\Atari\\ST\\Collections\\Atari ST - Collections - Atari Inside (TOSEC-v2013-12-13)", null}
            ,{"11", "G:\\importar\\tosec\\Atari\\ST\\Collections\\Atari ST - Collections - Atari Journal (TOSEC-v2013-12-13)", null}
            ,{"11", "G:\\importar\\tosec\\Atari\\ST\\Collections\\Atari ST - Collections - Atari Magazin (TOSEC-v2013-12-13)", null}
            ,{"11", "G:\\importar\\tosec\\Atari\\ST\\Collections\\Atari ST - Collections - Bitz (TOSEC-v2013-12-13)", null}
            ,{"11", "G:\\importar\\tosec\\Atari\\ST\\Collections\\Atari ST - Collections - Budgie UK (TOSEC-v2013-12-13)", null}
            ,{"11", "G:\\importar\\tosec\\Atari\\ST\\Collections\\Atari ST - Collections - Cobra (TOSEC-v2013-12-13)", null}
            ,{"11", "G:\\importar\\tosec\\Atari\\ST\\Collections\\Atari ST - Collections - Delta Labs (TOSEC-v2013-12-13)", null}
            ,{"11", "G:\\importar\\tosec\\Atari\\ST\\Collections\\Atari ST - Collections - Diverse (TOSEC-v2013-12-13)", null}
            ,{"11", "G:\\importar\\tosec\\Atari\\ST\\Collections\\Atari ST - Collections - Falcon (TOSEC-v2013-12-13)", null}
            ,{"11", "G:\\importar\\tosec\\Atari\\ST\\Collections\\Atari ST - Collections - FaST Club (TOSEC-v2013-12-13)", null}
            ,{"11", "G:\\importar\\tosec\\Atari\\ST\\Collections\\Atari ST - Collections - Floppyshop (TOSEC-v2011-03-07)", null}
            ,{"11", "G:\\importar\\tosec\\Atari\\ST\\Collections\\Atari ST - Collections - GFA Club (TOSEC-v2013-12-13)", null}
            ,{"11", "G:\\importar\\tosec\\Atari\\ST\\Collections\\Atari ST - Collections - Heim Special Line (TOSEC-v2013-12-13)", null}
            ,{"11", "G:\\importar\\tosec\\Atari\\ST\\Collections\\Atari ST - Collections - Kontrast (TOSEC-v2013-12-13)", null}
            ,{"11", "G:\\importar\\tosec\\Atari\\ST\\Collections\\Atari ST - Collections - Poolware (TOSEC-v2013-12-13)", null}
            ,{"11", "G:\\importar\\tosec\\Atari\\ST\\Collections\\Atari ST - Collections - ST Computer (TOSEC-v2013-12-13)", null}
            ,{"11", "G:\\importar\\tosec\\Atari\\ST\\Collections\\Atari ST - Collections - ST Vision (TOSEC-v2013-12-13)", null}
            ,{"11", "G:\\importar\\tosec\\Atari\\ST\\Collections\\Atari ST - Collections - XEST (TOSEC-v2013-12-13)", null}
            ,{"11", "G:\\importar\\tosec\\Atari\\ST\\Compilations\\Atari ST - Compilations - Various (TOSEC-v2011-03-18)", null}
            ,{"11", "G:\\importar\\tosec\\Atari\\ST\\Compilations\\Atari ST - Compilations - Applications (TOSEC-v2013-12-13)", null}
            ,{"11", "G:\\importar\\tosec\\Atari\\ST\\Compilations\\Games\\Atari ST - Compilations - Games - [STX] (TOSEC-v2013-12-13)", null}
            ,{"11", "G:\\importar\\tosec\\Atari\\ST\\Compilations\\Atari ST - Compilations - Demos (TOSEC-v2014-10-28)", null}
            ,{"11", "G:\\importar\\tosec\\Atari\\ST\\Compilations\\Atari ST - Compilations - Educational (TOSEC-v2013-12-13)", null}
            ,{"11", "G:\\importar\\tosec\\Atari\\ST\\Compilations\\Atari ST - Compilations - Public Domain (TOSEC-v2013-12-13)", null}
            ,{"11", "G:\\importar\\tosec\\Atari\\ST\\Compilations\\Games\\Atari ST - Compilations - Games - [ST] (TOSEC-v2011-08-31)", null}
            ,{"11", "G:\\importar\\tosec\\Atari\\ST\\Demos\\Atari ST - Demos - [ST] (TOSEC-v2014-10-28)", null}
            ,{"11", "G:\\importar\\tosec\\Atari\\ST\\Demos\\Atari ST - Demos - [STX] (TOSEC-v2013-12-13)", null}
            ,{"11", "G:\\importar\\tosec\\Atari\\ST\\Diskmags\\Atari ST - Diskmags - [ST] (TOSEC-v2011-04-11)", null}
            ,{"11", "G:\\importar\\tosec\\Atari\\ST\\Diskmags\\Atari ST - Diskmags - [STX] (TOSEC-v2013-12-13)", null}
            ,{"11", "G:\\importar\\tosec\\Atari\\ST\\Educational\\Atari ST - Educational - [ST] (TOSEC-v2013-12-13)", null}
            ,{"11", "G:\\importar\\tosec\\Atari\\ST\\Educational\\Atari ST - Educational - [STX] (TOSEC-v2013-12-13)", null}
            ,{"11", "G:\\importar\\tosec\\Atari\\ST\\TOS\\Atari ST - TOS - [IMG] (TOSEC-v2013-12-13)", null}
            ,{"11", "G:\\importar\\tosec\\Atari\\ST\\TOS\\Atari ST - TOS - [ST] (TOSEC-v2013-12-13)", null}
            ,{"11", "G:\\importar\\tosec\\Atari\\ST\\Games\\Atari ST - Games - Public Domain (TOSEC-v2013-12-13)", null}
            ,{"11", "G:\\importar\\tosec\\Atari\\ST\\Games\\Atari ST - Games - [STX] (TOSEC-v2013-05-05)", null}
            ,{"11", "G:\\importar\\tosec\\Atari\\ST\\Games\\Atari ST - Games - [ST] (TOSEC-v2014-10-28)", null}
            ,{"12", "G:\\importar\\no-intro\\Commodore\\Commodore - Amiga (20141228-005201)", null}
            ,{"13", "G:\\importar\\no-intro\\Commodore\\Commodore - 64 (20130331-070011)", null}
            ,{"13", "G:\\importar\\no-intro\\Commodore\\Commodore - 64 (PP) (20131204-081826)", null}
            ,{"13", "G:\\importar\\no-intro\\Commodore\\Commodore - 64 (Tapes) (20130730-055353)", null}
            ,{"14", "G:\\importar\\no-intro\\Commodore\\Commodore - VIC-20 (20130331-065627)", null}
            ,{"15", "G:\\importar\\no-intro\\Microsoft\\Microsoft - MSX - No-Intro (20140821-082335)", "Microsoft - MSX - No-Intro (20140821-082335)"}
            ,{"15", "G:\\importar\\no-intro\\Microsoft\\Microsoft - MSX 2 - No-Intro (20130225-030822)", "Microsoft - MSX 2 - No-Intro (20130225-030822)"}
            ,{"15", "G:\\importar\\tosec\\MSX\\MSX2+\\MSX MSX2+ - Applications (TOSEC-v2012-04-15)", null}
            ,{"15", "G:\\importar\\tosec\\MSX\\MSX2+\\MSX MSX2+ - Demos (TOSEC-v2012-04-15)", null}
            ,{"15", "G:\\importar\\tosec\\MSX\\MSX2+\\MSX MSX2+ - Firmware (TOSEC-v2012-04-15)", null}
            ,{"15", "G:\\importar\\tosec\\MSX\\MSX2+\\MSX MSX2+ - Games (TOSEC-v2012-04-15)", null}
            ,{"15", "G:\\importar\\tosec\\MSX\\TurboR\\MSX TurboR - Applications (TOSEC-v2012-04-15)", null}
            ,{"15", "G:\\importar\\tosec\\MSX\\TurboR\\MSX TurboR - Demos (TOSEC-v2012-04-15)", null}
            ,{"15", "G:\\importar\\tosec\\MSX\\TurboR\\MSX TurboR - Educational (TOSEC-v2012-04-15)", null}
            ,{"15", "G:\\importar\\tosec\\MSX\\TurboR\\MSX TurboR - Firmware (TOSEC-v2012-04-15)", null}
            ,{"15", "G:\\importar\\tosec\\MSX\\TurboR\\MSX TurboR - Games (TOSEC-v2012-04-15)", null}
            ,{"15", "G:\\importar\\tosec\\MSX\\TurboR\\MSX TurboR - Magazines (TOSEC-v2012-04-15)", null}
            ,{"15", "G:\\importar\\tosec\\MSX\\MSX2\\Applications\\MSX MSX2 - Applications - [CAS] (TOSEC-v2012-04-15)", null}
            ,{"15", "G:\\importar\\tosec\\MSX\\MSX2\\Applications\\MSX MSX2 - Applications - [DSK] (TOSEC-v2012-04-15)", null}
            ,{"15", "G:\\importar\\tosec\\MSX\\MSX2\\Applications\\MSX MSX2 - Applications - [ROM] (TOSEC-v2012-04-15)", null}
            ,{"15", "G:\\importar\\tosec\\MSX\\MSX2\\Compilations\\MSX MSX2 - Compilations - [CAS] (TOSEC-v2012-04-15)", null}
            ,{"15", "G:\\importar\\tosec\\MSX\\MSX2\\Compilations\\MSX MSX2 - Compilations - [DSK] (TOSEC-v2012-04-15)", null}
            ,{"15", "G:\\importar\\tosec\\MSX\\MSX2\\Games\\MSX MSX2 - Games - [CAS] (TOSEC-v2012-04-15)", null}
            ,{"15", "G:\\importar\\tosec\\MSX\\MSX2\\Games\\MSX MSX2 - Games - [ROM] (TOSEC-v2012-04-15)", null}
            ,{"15", "G:\\importar\\tosec\\MSX\\MSX2\\Games\\MSX MSX2 - Games - [DSK] (TOSEC-v2012-04-15)", null}
            ,{"15", "G:\\importar\\tosec\\MSX\\MSX2\\MSX MSX2 - Demos (TOSEC-v2012-04-15)", null}
            ,{"15", "G:\\importar\\tosec\\MSX\\MSX2\\MSX MSX2 - Educational (TOSEC-v2012-04-15)", null}
            ,{"15", "G:\\importar\\tosec\\MSX\\MSX2\\MSX MSX2 - Firmware (TOSEC-v2012-04-15)", null}
            ,{"15", "G:\\importar\\tosec\\MSX\\MSX2\\MSX MSX2 - Magazines (TOSEC-v2012-04-15)", null}
            ,{"15", "G:\\importar\\tosec\\MSX\\MSX\\Applications\\MSX MSX - Applications - [CAS] (TOSEC-v2012-04-15)", null}
            ,{"15", "G:\\importar\\tosec\\MSX\\MSX\\Applications\\MSX MSX - Applications - [DSK] (TOSEC-v2012-04-15)", null}
            ,{"15", "G:\\importar\\tosec\\MSX\\MSX\\Applications\\MSX MSX - Applications - [ROM] (TOSEC-v2012-04-15)", null}
            ,{"15", "G:\\importar\\tosec\\MSX\\MSX\\Compilations\\MSX MSX - Compilations - [CAS] (TOSEC-v2012-04-15)", null}
            ,{"15", "G:\\importar\\tosec\\MSX\\MSX\\Compilations\\MSX MSX - Compilations - [DSK] (TOSEC-v2012-04-15)", null}
            ,{"15", "G:\\importar\\tosec\\MSX\\MSX\\Compilations\\MSX MSX - Compilations - [ROM] (TOSEC-v2012-04-15)", null}
            ,{"15", "G:\\importar\\tosec\\MSX\\MSX\\Demos\\MSX MSX - Demos - [CAS] (TOSEC-v2012-04-15)", null}
            ,{"15", "G:\\importar\\tosec\\MSX\\MSX\\Demos\\MSX MSX - Demos - [DSK] (TOSEC-v2012-04-15)", null}
            ,{"15", "G:\\importar\\tosec\\MSX\\MSX\\Demos\\MSX MSX - Demos - [ROM] (TOSEC-v2012-04-15)", null}
            ,{"15", "G:\\importar\\tosec\\MSX\\MSX\\Educational\\MSX MSX - Educational - [CAS] (TOSEC-v2012-04-15)", null}
            ,{"15", "G:\\importar\\tosec\\MSX\\MSX\\Educational\\MSX MSX - Educational - [DSK] (TOSEC-v2012-04-15)", null}
            ,{"15", "G:\\importar\\tosec\\MSX\\MSX\\Educational\\MSX MSX - Educational - [ROM] (TOSEC-v2012-04-15)", null}
            ,{"15", "G:\\importar\\tosec\\MSX\\MSX\\Games\\MSX MSX - Games - [CAS] (TOSEC-v2012-04-15)", null}
            ,{"15", "G:\\importar\\tosec\\MSX\\MSX\\Games\\MSX MSX - Games - [DSK] (TOSEC-v2012-04-15)", null}
            ,{"15", "G:\\importar\\tosec\\MSX\\MSX\\Games\\MSX MSX - Games - [ROM] (TOSEC-v2012-04-15)", null}
            ,{"15", "G:\\importar\\tosec\\MSX\\MSX\\Magazines\\MSX MSX - Magazines - [CAS] (TOSEC-v2012-04-15)", null}
            ,{"15", "G:\\importar\\tosec\\MSX\\MSX\\Magazines\\MSX MSX - Magazines - [DSK] (TOSEC-v2012-04-15)", null}
            ,{"15", "G:\\importar\\tosec\\MSX\\MSX\\MSX MSX - Firmware (TOSEC-v2012-04-15_CM)", null}
            ,{"17", "G:\\importar\\no-intro\\SNK\\SNK - Neo Geo Pocket - No-Intro (20120228-094313)", "SNK - Neo Geo Pocket - No-Intro (20120228-094313)"}
            ,{"18", "G:\\importar\\no-intro\\SNK\\SNK - Neo Geo Pocket Color - No-Intro (20120801-232127)", "SNK - Neo Geo Pocket Color - No-Intro (20120801-232127)"}
            ,{"19", "G:\\importar\\no-intro\\Nintendo\\Nintendo - Nintendo Entertainment System - No-Intro (20141025-064058)", "Nintendo - Nintendo Entertainment System - No-Intro (20141025-064058)"}
            ,{"20", "G:\\importar\\no-intro\\Nintendo\\Nintendo - Famicom Disk System - No-Intro (20140803-044742)", "Nintendo - Famicom Disk System - No-Intro (20140803-044742)"}
            ,{"22", "G:\\importar\\no-intro\\Nintendo\\Nintendo - Nintendo 64 - No-Intro (20140703-111811)", "Nintendo - Nintendo 64 - No-Intro (20140703-111811)"}
            ,{"24", "G:\\importar\\no-intro\\Nintendo\\Nintendo - Game Boy - No-Intro (20150122-143021)", "Nintendo - Game Boy - No-Intro (20150122-143021)"}
            ,{"25", "G:\\importar\\no-intro\\Nintendo\\Nintendo - Game Boy Color - No-Intro (20141219-094251)", "Nintendo - Game Boy Color - No-Intro (20141219-094251)"}
            ,{"26", "G:\\importar\\no-intro\\Nintendo\\Nintendo - Game Boy Advance - No-Intro (20150101-185803)", "Nintendo - Game Boy Advance - No-Intro (20150101-185803)"}
            ,{"26", "G:\\importar\\no-intro\\Nintendo\\Nintendo - Game Boy Advance (e-Cards) - No-Intro (20140901-211411)", "Nintendo - Game Boy Advance (e-Cards) - No-Intro (20140901-211411)"}
            ,{"27", "G:\\importar\\no-intro\\Nintendo\\Nintendo - Pokemon Mini (20140804-123237)", null}
            ,{"28", "G:\\importar\\no-intro\\Nintendo\\Nintendo - Satellaview (20141015-003338)", null}
            ,{"29", "G:\\importar\\no-intro\\Nintendo\\Nintendo - Sufami Turbo (20140502-000000)", null}
            ,{"30", "G:\\importar\\no-intro\\Nintendo\\Nintendo - Virtual Boy - No-Intro (20140803-080208)", "Nintendo - Virtual Boy - No-Intro (20140803-080208)"}
            ,{"31", "G:\\importar\\no-intro\\Sega\\Sega - 32X - No-Intro (20130217-102211)", "Sega - 32X - No-Intro (20130217-102211)"}
            ,{"32", "G:\\importar\\no-intro\\Sega\\Sega - Game Gear - No-Intro (20141028-143709)", "Sega - Game Gear - No-Intro (20141028-143709)"}
            ,{"33", "G:\\importar\\no-intro\\Sega\\Sega - Master System - Mark III - No-Intro (20141028-150443)", "Sega - Master System - Mark III - No-Intro (20141028-150443)"}
            ,{"34", "G:\\importar\\no-intro\\Sega\\Sega - Mega Drive - Genesis - No-Intro (20141209-191853)", "Sega - Mega Drive - Genesis - No-Intro (20141209-191853)"}
            ,{"35", "G:\\importar\\no-intro\\Sega\\Sega - PICO - No-Intro (20141213-123705)", "Sega - PICO - No-Intro (20141213-123705)"}
            ,{"36", "G:\\importar\\no-intro\\Sega\\Sega - SG-1000 - No-Intro (20140508-002745)", "Sega - SG-1000 - No-Intro (20140508-002745)"}
            ,{"37", "G:\\importar\\diversos\\37-Apple 1\\Apple 1 - Applications (TOSEC-v2011-08-31)", null}
            ,{"37", "G:\\importar\\diversos\\37-Apple 1\\Apple 1 - Games (TOSEC-v2011-08-31)", null}
            ,{"38", "G:\\importar\\diversos\\38-Apple 2\\TOSEC-v2013-12-12 Apple II Docs\\Apple II - Docs - [DO] (TOSEC-v2013-12-12)", null}
            ,{"38", "G:\\importar\\diversos\\38-Apple 2\\TOSEC-v2013-12-12 Apple II Docs\\Apple II - Docs - [DSK] (TOSEC-v2013-12-12)", null}
            ,{"38", "G:\\importar\\diversos\\38-Apple 2\\TOSEC-v2013-12-12 Apple II Docs\\Apple II - Docs - [PO] (TOSEC-v2013-12-12)", null}
            ,{"38", "G:\\importar\\diversos\\38-Apple 2\\TOSEC-v2013-10-29 Apple II Applications\\Apple II - Applications - [DO] (TOSEC-v2013-10-29)", null}
            ,{"38", "G:\\importar\\diversos\\38-Apple 2\\TOSEC-v2013-10-29 Apple II Applications\\Apple II - Applications - [DSK] (TOSEC-v2014-10-28)", null}
            ,{"38", "G:\\importar\\diversos\\38-Apple 2\\TOSEC-v2013-10-29 Apple II Applications\\Apple II - Applications - [NIB] (TOSEC-v2013-10-29)", null}
            ,{"38", "G:\\importar\\diversos\\38-Apple 2\\TOSEC-v2013-10-29 Apple II Applications\\Apple II - Applications - [PO] (TOSEC-v2013-10-29)", null}
            ,{"38", "G:\\importar\\diversos\\38-Apple 2\\TOSEC-v2013-10-29 Apple II Collections\\Apple II - Collections - A.P.P.L.E. Public Domain Software (TOSEC-v2013-10-29)", null}
            ,{"38", "G:\\importar\\diversos\\38-Apple 2\\TOSEC-v2013-10-29 Apple II Collections\\Apple II - Collections - Call-A.P.P.L.E. Anthology (TOSEC-v2013-10-29)", null}
            ,{"38", "G:\\importar\\diversos\\38-Apple 2\\TOSEC-v2013-10-29 Apple II Compilations\\Applications\\Apple II - Compilations - Applications - [DO] (TOSEC-v2013-10-29)", null}
            ,{"38", "G:\\importar\\diversos\\38-Apple 2\\TOSEC-v2013-10-29 Apple II Compilations\\Applications\\Apple II - Compilations - Applications - [PO] (TOSEC-v2013-10-29)", null}
            ,{"38", "G:\\importar\\diversos\\38-Apple 2\\TOSEC-v2013-10-29 Apple II Compilations\\Games\\Apple II - Compilations - Games - [DO] (TOSEC-v2013-10-29)", null}
            ,{"38", "G:\\importar\\diversos\\38-Apple 2\\TOSEC-v2013-10-29 Apple II Compilations\\Games\\Apple II - Compilations - Games - [DSK] (TOSEC-v2013-10-29)", null}
            ,{"38", "G:\\importar\\diversos\\38-Apple 2\\TOSEC-v2013-10-29 Apple II Compilations\\Games\\Apple II - Compilations - Games - [PO] (TOSEC-v2013-10-29)", null}
            ,{"38", "G:\\importar\\diversos\\38-Apple 2\\TOSEC-v2013-12-12 Apple II Demos\\Apple II - Demos - [DO] (TOSEC-v2013-12-12)", null}
            ,{"38", "G:\\importar\\diversos\\38-Apple 2\\TOSEC-v2013-12-12 Apple II Demos\\Apple II - Demos - [DSK] (TOSEC-v2013-12-12)", null}
            ,{"38", "G:\\importar\\diversos\\38-Apple 2\\TOSEC-v2013-12-12 Apple II Demos\\Apple II - Demos - [PO] (TOSEC-v2013-12-12)", null}
            ,{"38", "G:\\importar\\diversos\\38-Apple 2\\TOSEC-v2013-12-12 Apple II Educational\\Apple II - Educational - [DO] (TOSEC-v2013-12-12)", null}
            ,{"38", "G:\\importar\\diversos\\38-Apple 2\\TOSEC-v2013-12-12 Apple II Educational\\Apple II - Educational - [DSK] (TOSEC-v2013-12-12)", null}
            ,{"38", "G:\\importar\\diversos\\38-Apple 2\\TOSEC-v2013-12-12 Apple II Educational\\Apple II - Educational - [NIB] (TOSEC-v2013-12-12)", null}
            ,{"38", "G:\\importar\\diversos\\38-Apple 2\\TOSEC-v2013-12-12 Apple II Educational\\Apple II - Educational - [PO] (TOSEC-v2013-12-12)", null}
            ,{"38", "G:\\importar\\diversos\\38-Apple 2\\TOSEC-v2013-12-12 Apple II Games\\Apple II - Games - [DO] (TOSEC-v2013-12-12)", null}
            ,{"38", "G:\\importar\\diversos\\38-Apple 2\\TOSEC-v2013-12-12 Apple II Games\\Apple II - Games - [DSK] (TOSEC-v2013-12-12)", null}
            ,{"38", "G:\\importar\\diversos\\38-Apple 2\\TOSEC-v2013-12-12 Apple II Games\\Apple II - Games - [NIB] (TOSEC-v2013-12-12)", null}
            ,{"38", "G:\\importar\\diversos\\38-Apple 2\\TOSEC-v2013-12-12 Apple II Games\\Apple II - Games - [PO] (TOSEC-v2013-12-12)", null}
            ,{"38", "G:\\importar\\diversos\\38-Apple 2\\TOSEC-v2013-12-12 Apple II Magazines\\Apple II - Magazines - [DO] (TOSEC-v2013-12-12)", null}
            ,{"38", "G:\\importar\\diversos\\38-Apple 2\\TOSEC-v2013-12-12 Apple II Magazines\\Apple II - Magazines - [DSK] (TOSEC-v2013-12-12)", null}
            ,{"38", "G:\\importar\\diversos\\38-Apple 2\\TOSEC-v2013-12-12 Apple II Magazines\\Apple II - Magazines - [PO] (TOSEC-v2013-12-12)", null}
            ,{"38", "G:\\importar\\diversos\\38-Apple 2\\TOSEC-v2013-12-13 Apple II Operating Systems\\Apple II - Operating Systems - [DO] (TOSEC-v2013-12-13)", null}
            ,{"38", "G:\\importar\\diversos\\38-Apple 2\\TOSEC-v2013-12-13 Apple II Operating Systems\\Apple II - Operating Systems - [PO] (TOSEC-v2013-12-13)", null}
            ,{"38", "G:\\importar\\diversos\\38-Apple 2\\Unclassified\\Unclassified - A.P.P.L.E. Public Domain Software", null}
            ,{"38", "G:\\importar\\diversos\\38-Apple 2\\Unclassified\\Unclassified - Call-A.P.P.L.E. Anthology", null}
            ,{"38", "G:\\importar\\diversos\\38-Apple 2\\Unclassified\\Unclassified - Disk Images", null}
            ,{"38", "G:\\importar\\diversos\\38-Apple 2\\TOSEC-v0.24 Apple II", null}
            ,{"39", "G:\\importar\\diversos\\39-Apple IIGS\\Apple IIGS - Applications (TOSEC-v2013-12-13)", null}
            ,{"39", "G:\\importar\\diversos\\39-Apple IIGS\\Apple IIGS - Compilations (TOSEC-v2013-12-13)", null}
            ,{"39", "G:\\importar\\diversos\\39-Apple IIGS\\Apple IIGS - Demos (TOSEC-v2013-12-13)", null}
            ,{"39", "G:\\importar\\diversos\\39-Apple IIGS\\Apple IIGS - Docs (TOSEC-v2013-12-13)", null}
            ,{"39", "G:\\importar\\diversos\\39-Apple IIGS\\Apple IIGS - Educational (TOSEC-v2013-12-13)", null}
            ,{"39", "G:\\importar\\diversos\\39-Apple IIGS\\Apple IIGS - Games (TOSEC-v2013-12-13)", null}
            ,{"39", "G:\\importar\\diversos\\39-Apple IIGS\\Apple IIGS - Operating Systems (TOSEC-v2013-12-13)", null}
            ,{"39", "G:\\importar\\diversos\\39-Apple IIGS\\Apple IIGS - Sources (TOSEC-v2013-12-13)", null}
            ,{"40", "G:\\importar\\diversos\\40-Apple III\\TOSEC-v2012-08-27 Apple III Applications\\Apple III - Applications (TOSEC-v2012-08-27)", null}
            ,{"41", "G:\\importar\\diversos\\41-Apple Lisa\\Apple Lisa - Applications (TOSEC-v2013-12-13)", null}
            ,{"43", "G:\\importar\\no-intro\\Sinclair\\Sinclair - ZX Spectrum +3 (20130225-035911)", null}
            ,{"46", "G:\\importar\\no-intro\\Tiger\\Tiger - Game.com No-Intro (20081125-110950)", "Tiger - Game.com No-Intro (20081125-110950)"}
            ,{"49", "G:\\importar\\no-intro\\NEC\\NEC - PC Engine - TurboGrafx 16 - No-Intro (20140803-081637)", "NEC - PC Engine - TurboGrafx 16 - No-Intro (20140803-081637)"}
            ,{"52", "G:\\importar\\no-intro\\GCE\\GCE - Vectrex - No-Intro (20081109-035804)", "GCE - Vectrex - No-Intro (20081109-035804)"}
            ,{"53", "G:\\importar\\no-intro\\Watara\\Watara - Supervision - No-Intro (20130102-044026)", "Watara - Supervision - No-Intro (20130102-044026)"}
            ,{"54", "G:\\importar\\no-intro\\Bandai\\Bandai - WonderSwan - No-Intro (20140803-052611)", "Bandai - WonderSwan - No-Intro (20140803-052611)"}
            ,{"55", "G:\\importar\\no-intro\\Bandai\\Bandai - WonderSwan Color - No-Intro (20140107-045955)", "Bandai - WonderSwan Color - No-Intro (20140107-045955)"}
            ,{"59", "G:\\importar\\no-intro\\NEC\\NEC - Super Grafx - No-Intro (20110307-113453)", "NEC - Super Grafx - No-Intro (20110307-113453)"}
            ,{"33", "F:\\tmp\\roms\\No-Intro - Sega - Master System - Mark III (2017-04-14)", "Sega - Master System - Mark III - No-Intro (2017-04-14)"}
            ,{"34", "F:\\tmp\\roms\\No-Intro - Sega - Mega Drive - Genesis (2017-03-18)", "Sega - Mega Drive - Genesis - No-Intro (2017-03-18)"}
*/
            //,{"platformId", "path", "description"}
    };
    //private static final String IMPORT_INFO = "Unclassified";
    //private static final String BASE_DIR = "G:\\importar\\goodset\\GoodPSID v0.999.4\\PSIDRen";
    //private static final String IMPORT_INFO = "GoodPSID 0.999.4";
    /*
+-------------+-------------------------------------+
| platform_id | name                                |
+-------------+-------------------------------------+
|           1 | Multi Platforms                     |
|           2 | MAME                                |
|           3 | ColecoVision                        |
|           4 | Atari 2600                          |
|           5 | Atari 5200                          |
|           6 | Atari 7800                          |
|           7 | Atari 800                           |
|           8 | Atari Jaguar                        |
|           9 | Atari Jaguar CD                     |
|          10 | Atari Lynx                          |
|          11 | Atari ST                            |
|          12 | Commodore Amiga                     |
|          13 | Commodore 64                        |
|          14 | Commodore VIC-20                    |
|          15 | Microsoft MSX                       |
|          16 | Magnavox Odyssey 2                  |
|          17 | SNK Neo Geo Pocket                  |
|          18 | SNK Neo Geo Pocket Color            |
|          19 | Nintendo Entertainment System       |
|          20 | Nintendo Famicom Disk System        |
|          21 | Super Nintendo Entertainment System |
|          22 | Nintendo 64                         |
|          23 | Nintendo DS                         |
|          24 | Nintendo Game Boy                   |
|          25 | Nintendo Game Boy Color             |
|          26 | Nintendo Game Boy Advance           |
|          27 | Nintendo Pokemon Mini               |
|          28 | Nintendo Satellaview                |
|          29 | Nintendo Sufami Turbo               |
|          30 | Nintendo Virtual Boy                |
|          31 | Sega 32X                            |
|          32 | Sega Game Gear                      |
|          33 | Sega Master System                  |
|          34 | Sega Genesis                        |
|          35 | Sega Pico                           |
|          36 | Sega SG-1000                        |
|          37 | Apple 1                             |
|          38 | Apple II                            |
|          39 | Apple IIGS                          |
|          40 | Apple III                           |
|          41 | Apple Lisa                          |
|          42 | Coleco Adam                         |
|          43 | Sinclair ZX Spectrum                |
|          44 | Tandy TRS-80                        |
|          45 | Amstrad CPC                         |
|          46 | Tiger Game.com                      |
|          47 | Thomson MO5                         |
|          48 | Oric Atmos                          |
|          49 | TurboGrafx-16                       |
|          51 | SAM Coupé                           |
|          52 | GCE Vectrex                         |
|          53 | Watara Supervision                  |
|          54 | Bandai WonderSwan                   |
|          55 | Bandai WonderSwan Color             |
|          56 | CPS-1                               |
|          57 | CPS-2                               |
|          58 | CPS-3                               |
|          59 | PC Engine SuperGrafx                |
+-------------+-------------------------------------+
     */
    private static boolean EXPAND_ZIP = true;
    private static boolean EXPAND_INTERNAL_ZIP = true;
    private static int CONT = 0;

    public static void main(String[] args) {
        try {
            for (String[] ss : IMPORT) {
                CONT = 0;
                System.out.println("=========================");
                System.out.println("Importando: " + ss[0] + " -> " + ss[1] + " -> " + ss[2]);
                File file = new File(ss[1]);
                processFile(file, ss[0], ss[2] != null ? ss[2] : file.getName());
                System.out.println("=========================");
                System.out.println("Arquivos importados: " + CONT);
            }
            System.out.println("========== FIM ==========");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void processFile(File file, String paltformID, String importInfo) throws Exception {
        if (file.isFile()) {
            if (CONT % 100 == 0) {
                System.out.println("Processando arquivo: " + file);
            }
            Client client = ClientBuilder.newBuilder().register(MultiPartFeature.class).build();
            String url = TARGET_URL + paltformID + "/files";
            WebTarget webTarget = client.target(url)
                    .queryParam("type", ArtifactFileTypeEnum.ROM.name())
                    .queryParam("expandZip", String.valueOf(EXPAND_ZIP))
                    .queryParam("expandInternalZip", String.valueOf(EXPAND_INTERNAL_ZIP))
                    .queryParam("importInfo", importInfo);
            MultiPart multiPart = new MultiPart();
            multiPart.setMediaType(MediaType.MULTIPART_FORM_DATA_TYPE);
            FileDataBodyPart fileDataBodyPart = new FileDataBodyPart("file",
                    file, MediaType.APPLICATION_OCTET_STREAM_TYPE);
            multiPart.bodyPart(fileDataBodyPart);

            Response response = webTarget.request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(multiPart, multiPart.getMediaType()));
            int status = response.getStatus();
            if (status == 200 || status == 201) {
                if (CONT % 100 == 0) {
                    System.out.println("--- " + response.getStatus() + ": " + response.getStatusInfo());
                }
                if (DELETE_FILE) {
                    file.delete();
                }
            } else {
                System.err.println("EEE " + response.getStatus() + ": " + response.getStatusInfo() + " -> " + file.getAbsolutePath());
                System.err.println("EEEEE " + response.readEntity(String.class));
            }
            CONT++;
        } else if (file.isDirectory()) {
            File[] fs = file.listFiles();
            for (File f : fs) {
                processFile(f, paltformID, importInfo);
            }
            if (DELETE_FILE) {
                if (file.listFiles() == null || file.listFiles().length == 0) {
                    file.delete();
                }
            }
        }
    }
}
