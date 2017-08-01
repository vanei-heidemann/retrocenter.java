package com.javanei.retrocenter.server.teste;

import com.javanei.retrocenter.gamedb.launchbox.common.LBoxDatafile;
import com.javanei.retrocenter.gamedb.launchbox.common.LBoxPlatform;
import com.javanei.retrocenter.gamedb.launchbox.parser.LaunchBoxParser;
import com.javanei.retrocenter.platform.common.Platform;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SetupDefaultPlatforms {
    private static final String TARGET_URL = "http://localhost:8080/retrocenter";

    public static void main(String[] args) {
        try {
            createDefaultPlatforms();
            //importLBoxPlatforms();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void createDefaultPlatforms() throws Exception {
        List<Platform> l = new ArrayList<>();
        l.add(new Platform("Multi Platforms", "Multi", "Multi"));
        l.add(new Platform("MAME", "MAME", "MAME",
                new String[]{"M.A.M.E.", "Multiple Arcade Machine Emulator"}));
        l.add(new Platform("ColecoVision", "Coleco", "Coleco",
                new String[]{"Coleco", "Coleco Vision"}));
        l.add(new Platform("Atari 2600", "Atari 2600", "2600",
                new String[]{"2600", "Atari"}));
        l.add(new Platform("Atari 5200", "Atari 5200", "5200",
                new String[]{"5200", "Atari 5200 SuperSystem", "5200 SuperSystem", "Atari SuperSystem", "SuperSystem"}));
        l.add(new Platform("Atari 7800", "Atari 7800", "7800",
                new String[]{"7800", "Atari 7800 ProSystem", "7800 ProSystem", "Atari ProSystem", "ProSystem"}));
        l.add(new Platform("Atari 800", "A800", "A800",
                new String[]{"Atari 8-bit"}));
        l.add(new Platform("Atari Jaguar", "Jaguar", "Jaguar",
                new String[]{"Jaguar"}));
        l.add(new Platform("Atari Jaguar CD", "Jaguar CD", "JaguarCD",
                new String[]{"Jaguar CD", "Jag CD"}));
        l.add(new Platform("Atari Lynx", "Lynx", "Lynx",
                new String[]{"Lynx"}));
        l.add(new Platform("Atari ST", "Atari ST", "AST"));
        l.add(new Platform("Commodore Amiga", "Amiga", "Amiga",
                new String[]{"Amiga"}));
        l.add(new Platform("Commodore 64", "Commodore 64", "C64",
                new String[]{"C64", "C=64", "CBM 64", "VIC-64", "C 64", "CBM64", "VIC 64", "VIC64", "Comm 64",
                        "Comm. 64", "Comm64", "Comodore 64", "Commodore"}));
        l.add(new Platform("Commodore VIC-20", "VIC-20", "VIC20",
                new String[]{"VIC-20"}));
        l.add(new Platform("Microsoft MSX", "MSX", "MSX",
                new String[]{"MSX", "MSX 2", "MSX2", "MSX 2+", "MSX2+", "Microsoft MSX2", "MSX Laserdisc",
                        "Microsoft MSX2", "Microsoft MSX2+"}));
        l.add(new Platform("Magnavox Odyssey 2", "Odyssey 2", "Odyssey2",
                new String[]{"Magnavox Odyssey²", "Magnavox Odyssey ²", "Odyssey 2", "Odyssey²", "Odyssey ²"}));
        l.add(new Platform("SNK Neo Geo Pocket", "NeoGeo Pocket", "NGP",
                new String[]{"NeoGeo Pocket", "Neo Geo Pocket", "NGP", "SNK Neo-Geo Pocket"}));
        l.add(new Platform("SNK Neo Geo Pocket Color", "NeoGeo Pocket Color", "NGP",
                new String[]{"NeoGeo Pocket Color", "NeoGeo Color", "NGPC", "SNK Neo-Geo Pocket Color",
                        "Neo Geo Pocket Color"}));
        l.add(new Platform("Nintendo Entertainment System", "Nintendo", "NES",
                new String[]{"NES", "Nintendo", "Famicom", "FC", "Nintendo NES",
                        "Nintendo Entertainment System (NES)"}));
        l.add(new Platform("Nintendo Famicom Disk System", "Famicom Disk System", "FamicomDisk",
                new String[]{"Famicom Disk System", "Famicon Disk System", "Nintendo Famicon Disk System"}));
        l.add(new Platform("Super Nintendo Entertainment System", "Super Nintendo", "SNES",
                new String[]{"Super Nintendo", "SNES", "Super Nintendo (SNES)", "Super NES", "Super Famicom", "SFC",
                        "Super Comboy", "Comboy", "Nintendo SNES", "Nintendo Super Famicom"}));
        l.add(new Platform("Nintendo 64", "Nintendo 64", "N64",
                new String[]{"N64", "Nintendo N64"}));
        l.add(new Platform("Nintendo DS", "Nintendo DS", "NDS",
                new String[]{"Nintendo DSi", "NDS", "NDSi", "DSi", "DS"}));
        l.add(new Platform("Nintendo Game Boy", "GameBoy", "GB",
                new String[]{"Game Boy", "GameBoy", "NGB", "GB", "Nintendo GameBoy", "Nintendo GB",
                        "Super Game Boy", "Super GameBoy", "Nintendo Super Gameboy", "Nintendo Super Game Boy"}));
        l.add(new Platform("Nintendo Game Boy Color", "GameBoy Color", "GB",
                new String[]{"Game Boy Color", "GameBoy Color", "GBC", "Nintendo GameBoy Color",
                        "Nintendo GBC"}));
        l.add(new Platform("Nintendo Game Boy Advance", "GameBoy Advance", "GBA",
                new String[]{"Game Boy Advance", "GameBoy Advance", "GBA", "Nintendo GameBoy Advance",
                        "Nintendo GBA"}));
        l.add(new Platform("Nintendo Pokemon Mini", "Pokemon Mini", "PokemonMini",
                new String[]{"Pokemon Mini"}));
        l.add(new Platform("Nintendo Satellaview", "Satellaview", "Satellaview",
                new String[]{"Satellaview"}));
        l.add(new Platform("Nintendo Sufami Turbo", "Sufami Turbo", "SufamiTurbo",
                new String[]{"Sufami Turbo"}));
        l.add(new Platform("Nintendo Virtual Boy", "Virtual Boy", "NVB",
                new String[]{"Virtual Boy", "VirtualBoy", "Nintendo VirtualBoy", "NVB"}));
        l.add(new Platform("Sega 32X", "Sega 32X", "32X",
                new String[]{"32X"}));
        l.add(new Platform("Sega Game Gear", "Game Gear", "GG",
                new String[]{"Game Gear", "GameGear", "Sega GameGear"}));
        l.add(new Platform("Sega Master System", "Master System", "SMS",
                new String[]{"Master System", "SMS", "MasterSystem", "Sega MasterSystem"}));
        l.add(new Platform("Sega Genesis", "Genesis", "GEN",
                new String[]{"Genesis", "Mega Drive", "MegaDrive", "Sega MegaDrive", "Sega Mega Drive"}));
        l.add(new Platform("Sega Pico", "Pico", "Pico"));
        l.add(new Platform("Sega SG-1000", "SG-1000", "SG1000",
                new String[]{"SG-1000", "Sega Game 1000", "SG1000", "Sega SG1000"}));
        l.add(new Platform("Apple 1", "Apple 1", "Apple1",
                new String[]{"Apple I"}));
        l.add(new Platform("Apple II", "Apple II", "Apple2",
                new String[]{"Apple 2"}));
        l.add(new Platform("Apple IIGS", "Apple IIGS", "Apple2GS",
                new String[]{"Apple II-GS", "Apple II GS"}));
        l.add(new Platform("Apple III", "Apple III", "Apple3",
                new String[]{"Apple 3"}));
        l.add(new Platform("Apple Lisa", "Lisa", "Lisa"));
        l.add(new Platform("Coleco Adam", "Adam", "Adam",
                new String[]{"ADAM"}));

        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target(TARGET_URL + "/platforms/");
        for (Platform p : l) {
            Response response = webTarget.request(MediaType.APPLICATION_JSON)
                    .post(Entity.entity(p, MediaType.APPLICATION_JSON_TYPE));
            System.out.println("==> " + response.readEntity(String.class));
        }
    }

    private static void importLBoxPlatforms() throws Exception {
        LaunchBoxParser parser = new LaunchBoxParser();
        LBoxDatafile datafile = parser.parse(new File("F:/LaunchBox"));
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target(TARGET_URL + "/platforms/");
        for (LBoxPlatform platform : datafile.getPlatforms()) {
            Platform p = new Platform(platform.getName());
            p.setShortName(platform.getName());
            p.setAlternateNames(platform.getAlternateNames());
            Response response = webTarget.request(MediaType.APPLICATION_JSON)
                    .post(Entity.entity(p, MediaType.APPLICATION_JSON_TYPE));
            System.out.println("==> " + response.readEntity(String.class));
        }
    }
}
