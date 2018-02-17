package com.javanei.retrocenter.utility;

import com.javanei.retrocenter.gamedb.launchbox.common.LBoxDatafile;
import com.javanei.retrocenter.gamedb.launchbox.common.LBoxGame;
import com.javanei.retrocenter.gamedb.launchbox.common.LBoxPlatform;
import com.javanei.retrocenter.gamedb.launchbox.common.LBoxRegion;
import com.javanei.retrocenter.gamedb.launchbox.parser.LaunchBoxParser;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckLaunchBoxRomsInFolder {
    private static final String LAUNCHBOX_DIR = "F:/LaunchBox";
    private static final String SRC_ROMS_DIR = "F:\\tmp\\Atari 2600\\roms";
    private static final String DST_ROMS_DIR = "F:\\tmp\\Atari 2600\\found";
    private static final String PLATFORM_NAME = "Atari 2600";

    public static void main(String[] args) {
        try {
            LaunchBoxParser parser = new LaunchBoxParser();
            LBoxDatafile datafile = parser.parse(new File(LAUNCHBOX_DIR));
            LBoxPlatform platform = null;
            for (LBoxPlatform p : datafile.getPlatforms()) {
                if (p.getName().equals(PLATFORM_NAME)) {
                    platform = p;
                    break;
                }
            }
            if (platform == null) {
                throw new Exception("Platform " + PLATFORM_NAME + " not found");
            }

            //System.out.println(platform);
            //System.out.println("REGIONS=" + datafile.getRegions());

            File[] fs = new File(SRC_ROMS_DIR).listFiles();
            if (fs == null) {
                throw new Exception("Folder not found: " + SRC_ROMS_DIR);
            }
            System.out.println(" --> Files in folder: " + fs.length);

            Map<String, File> files = new HashMap<>();
            Map<String, LBoxGame> games = new HashMap<>();

            for (File f : fs) {
                if (f.getName().toLowerCase().endsWith(".zip")) {
                    String gameName = f.getName().substring(0, f.getName().length() - 4).toLowerCase().trim();
                    files.put(gameName, f);
                    //System.out.println("FILE= " + gameName + " -> " + f);
                }
            }
            System.out.println(" --> Games in folder: " + files.size());

            for (LBoxGame game : datafile.getGames()) {
                if (game.getPlatform().getName().equals(platform.getName())) {
                    games.put(game.getName().trim().toLowerCase(), game);
                    //System.out.println("GAME= " + game.getName() + " -> " + game);
                }
            }
            System.out.println(" --> Games in platform: " + games.size());

            List<File> foundFiles = new ArrayList<>();
            List<LBoxGame> foundGames = new ArrayList<>();

            for (String gameName : games.keySet()) {
                LBoxGame game = games.get(gameName);
                File file = files.get(gameName);
                if (file == null) {
                    for (LBoxRegion region : game.getRegions()) {
                        file = files.get(gameName + " (" + region.getName().toLowerCase() + ")");
                        if (file == null && region.getName().equals("North America")) {
                            file = files.get(gameName + " (USA)".toLowerCase());
                        }
                    }
                }
                if (file != null) {
                    //System.out.println(file + " -> " + game);
                    foundFiles.add(file);
                    foundGames.add(game);
                    files.remove(gameName);
                    FileOutputStream out = new FileOutputStream(new File(DST_ROMS_DIR, file.getName()));
                    Files.copy(file.toPath(), out);

                    file.delete();
                }
            }

            System.out.println(" --> Found games: " + foundGames.size());
            System.out.println(" --> Found files: " + foundFiles.size());

/*
            System.out.println("GAMES NOT FOUND ================");
            for (LBoxGame game : foundGames) {
                games.remove(game.getName().toLowerCase().trim());
            }
            for (LBoxGame game : games.values()) {
                System.out.println(game.getName());
            }
*/
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
