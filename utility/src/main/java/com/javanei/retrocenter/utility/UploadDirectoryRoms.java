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
    private static final boolean DELETE_FILE = true;
    private static final String[][] IMPORT = new String[][]{
            {"5", "G:\\importar\\no-intro\\Nintendo\\Nintendo - Super Nintendo Entertainment System - No-Intro (20141221-212957)", "Nintendo - Super Nintendo Entertainment System - No-Intro (20141221-212957)"}
            , {"22", "G:\\importar\\no-intro\\Nintendo\\Nintendo - Nintendo 64 - No-Intro (20140703-111811)", "Nintendo - Nintendo 64 - No-Intro (20140703-111811)"}
            , {"26", "G:\\importar\\no-intro\\Nintendo\\Nintendo - Game Boy Advance - No-Intro (20150101-185803)", "Nintendo - Game Boy Advance - No-Intro (20150101-185803)"}
            , {"26", "G:\\importar\\no-intro\\Nintendo\\Nintendo - Game Boy Advance (e-Cards) - No-Intro (20140901-211411)", "Nintendo - Game Boy Advance (e-Cards) - No-Intro (20140901-211411)"}
/*
            {"5", "G:\\importar\\no-intro\\Atari\\Atari - 5200 - No-Intro (20130303-220600)", "Atari - 5200 - No-Intro (20130303-220600)"}
            , {"6", "G:\\importar\\no-intro\\Atari\\Atari - 7800 - No-Intro (20140326-040142)", "Atari - 7800 - No-Intro (20140326-040142)"}
            , {"8", "G:\\importar\\no-intro\\Atari\\Atari - Jaguar - No-Intro (20130217-102044)", "Atari - Jaguar - No-Intro (20130217-102044)"}
            , {"10", "G:\\importar\\no-intro\\Atari\\Atari - Lynx - No-Intro (20130303-222635)", "Atari - Lynx - No-Intro (20130303-222635)"}
            , {"11", "G:\\importar\\no-intro\\Atari\\Atari - ST - No-Intro (20141101-034609)", "Atari - ST - No-Intro (20141101-034609)"}
            , {"54", "G:\\importar\\no-intro\\Bandai\\Bandai - WonderSwan - No-Intro (20140803-052611)", "Bandai - WonderSwan - No-Intro (20140803-052611)"}
            , {"55", "G:\\importar\\no-intro\\Bandai\\Bandai - WonderSwan Color - No-Intro (20140107-045955)", "Bandai - WonderSwan Color - No-Intro (20140107-045955)"}
            , {"3", "G:\\importar\\no-intro\\Coleco\\Coleco - ColecoVision - No-Intro (20140803-055913)", "Coleco - ColecoVision - No-Intro (20140803-055913)"}
            , {"52", "G:\\importar\\no-intro\\GCE\\GCE - Vectrex - No-Intro (20081109-035804)", "GCE - Vectrex - No-Intro (20081109-035804)"}
            , {"15", "G:\\importar\\no-intro\\Microsoft\\Microsoft - MSX - No-Intro (20140821-082335)", "Microsoft - MSX - No-Intro (20140821-082335)"}
            , {"15", "G:\\importar\\no-intro\\Microsoft\\Microsoft - MSX 2 - No-Intro (20130225-030822)", "Microsoft - MSX 2 - No-Intro (20130225-030822)"}
            , {"49", "G:\\importar\\no-intro\\NEC\\NEC - PC Engine - TurboGrafx 16 - No-Intro (20140803-081637)", "NEC - PC Engine - TurboGrafx 16 - No-Intro (20140803-081637)"}
            , {"59", "G:\\importar\\no-intro\\NEC\\NEC - Super Grafx - No-Intro (20110307-113453)", "NEC - Super Grafx - No-Intro (20110307-113453)"}
            , {"31", "G:\\importar\\no-intro\\Sega\\Sega - 32X - No-Intro (20130217-102211)", "Sega - 32X - No-Intro (20130217-102211)"}
            , {"32", "G:\\importar\\no-intro\\Sega\\Sega - Game Gear - No-Intro (20141028-143709)", "Sega - Game Gear - No-Intro (20141028-143709)"}
            , {"33", "G:\\importar\\no-intro\\Sega\\Sega - Master System - Mark III - No-Intro (20141028-150443)", "Sega - Master System - Mark III - No-Intro (20141028-150443)"}
            , {"34", "G:\\importar\\no-intro\\Sega\\Sega - Mega Drive - Genesis - No-Intro (20141209-191853)", "Sega - Mega Drive - Genesis - No-Intro (20141209-191853)"}
            , {"35", "G:\\importar\\no-intro\\Sega\\Sega - PICO - No-Intro (20141213-123705)", "Sega - PICO - No-Intro (20141213-123705)"}
            , {"36", "G:\\importar\\no-intro\\Sega\\Sega - SG-1000 - No-Intro (20140508-002745)", "Sega - SG-1000 - No-Intro (20140508-002745)"}
            , {"17", "G:\\importar\\no-intro\\SNK\\SNK - Neo Geo Pocket - No-Intro (20120228-094313)", "SNK - Neo Geo Pocket - No-Intro (20120228-094313)"}
            , {"18", "G:\\importar\\no-intro\\SNK\\SNK - Neo Geo Pocket Color - No-Intro (20120801-232127)", "SNK - Neo Geo Pocket Color - No-Intro (20120801-232127)"}
            , {"46", "G:\\importar\\no-intro\\Tiger\\Tiger - Game.com No-Intro (20081125-110950)", "Tiger - Game.com No-Intro (20081125-110950)"}
            , {"53", "G:\\importar\\no-intro\\Watara\\Watara - Supervision - No-Intro (20130102-044026)", "Watara - Supervision - No-Intro (20130102-044026)"}
            , {"20", "G:\\importar\\no-intro\\Nintendo\\Nintendo - Famicom Disk System - No-Intro (20140803-044742)", "Nintendo - Famicom Disk System - No-Intro (20140803-044742)"}
            , {"19", "G:\\importar\\no-intro\\Nintendo\\Nintendo - Nintendo Entertainment System - No-Intro (20141025-064058)", "Nintendo - Nintendo Entertainment System - No-Intro (20141025-064058)"}
            , {"24", "G:\\importar\\no-intro\\Nintendo\\Nintendo - Game Boy - No-Intro (20150122-143021)", "Nintendo - Game Boy - No-Intro (20150122-143021)"}
            , {"25", "G:\\importar\\no-intro\\Nintendo\\Nintendo - Game Boy Color - No-Intro (20141219-094251)", "Nintendo - Game Boy Color - No-Intro (20141219-094251)"}
            , {"30", "G:\\importar\\no-intro\\Nintendo\\Nintendo - Virtual Boy - No-Intro (20140803-080208)", "Nintendo - Virtual Boy - No-Intro (20140803-080208)"}
*/
            //,{"xx", "xx", "xx"}
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
                processFile(new File(ss[1]), ss[0], ss[2]);
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
