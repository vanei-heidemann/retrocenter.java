package com.javanei.retrocenter.utility;

import com.javanei.retrocenter.common.DatafileCatalogEnum;
import com.javanei.retrocenter.common.util.FileUtil;
import com.javanei.retrocenter.common.util.StringUtil;
import com.javanei.retrocenter.logiqx.LogiqxDatafile;
import com.javanei.retrocenter.logiqx.LogiqxGame;
import com.javanei.retrocenter.logiqx.LogiqxHeader;
import com.javanei.retrocenter.logiqx.LogiqxRom;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class CriaGoodSetCatalog {
    private static final File BASE_DIR = new File("G:\\importar\\goodset\\GoodSPC v2.01\\SPCRen");
    private static final String AUTHOR = "Vanei";
    private static final String NAME = "GoodSPC";
    private static final String VERSION = "2.01";
    private static final String COMMENT = "";
    private static final String DATE = "2017-11-05";
    private static final String DESCRIPTION = NAME + " " + VERSION;

    public static void main(String[] args) {
        try {
            List<File> files = new LinkedList<>();
            processDir(BASE_DIR, files);

            LogiqxDatafile datafile = new LogiqxDatafile();
            LogiqxHeader header = new LogiqxHeader(NAME, DatafileCatalogEnum.GoodSet.name(), VERSION, DESCRIPTION,
                    DatafileCatalogEnum.GoodSet.name(), DATE, AUTHOR,
                    null /*email*/, null /*homepage*/, null /*url*/, COMMENT);
            datafile.setHeader(header);

            for (File f : files) {
                String name = f.getName();
                if (name.contains(".") && name.length() - name.lastIndexOf(".") <= 4) {
                    name = name.substring(0, name.lastIndexOf("."));
                }
                byte[] fileContent = FileUtil.readFile(f);

                LogiqxGame game = new LogiqxGame();
                game.setName(name);
                game.setDescription(name);
                datafile.addGame(game);
                LogiqxRom logiqxRom = new LogiqxRom();
                logiqxRom.setName(f.getName());
                logiqxRom.setCrc(StringUtil.toStringCRC(FileUtil.getCRC(fileContent)));
                logiqxRom.setMd5(StringUtil.toHexString(FileUtil.getMD5(fileContent)));
                logiqxRom.setSha1(StringUtil.toHexString(FileUtil.getSHA1(fileContent)));
                logiqxRom.setSize(String.valueOf(f.length()));
                game.addRom(logiqxRom);
                if (datafile.getGames().size() % 100 == 0) {
                    System.out.println("==== Games processados: " + datafile.getGames().size());
                }
            }
            System.out.println(datafile.toFile());
            System.out.println("================");
            System.out.println("Files: " + files.size());
            System.out.println("ROMs: " + datafile.getGames().size());
            System.out.println("================");

            File save = new File(BASE_DIR.getParentFile(), NAME + "_" + VERSION + ".dat");
            FileUtil.writeFile(save, datafile.toFile().getBytes());
            //save = new File(BASE_DIR.getParentFile(), DESCRIPTION + ".xml");
            //FileUtil.writeFile(save, datafile.toDatafile().toFile().getBytes());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void processDir(File baseDir, List<File> files) {
        File[] fs = baseDir.listFiles();
        if (fs != null) {
            for (File f : fs) {
                if (f.isFile()) {
                    files.add(f);
                } else if (f.isDirectory()) {
                    processDir(f, files);
                }
            }
        }
    }
}
