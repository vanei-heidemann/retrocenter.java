package com.javanei.retrocenter.utility;

import com.javanei.retrocenter.common.util.ZipUtil;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ZipDirPart {
    /*
    private static final String SRC_BASE_DIR = "F:\\REPO-IMPORT\\AST\\roms";
    private static final String DST_BASE_DIR = "F:\\REPO-IMPORT";
    private static final String DST_FILENAME_PREFIX = "11.AST-";
    private static final int START_COUNT = 6;
    private static final int COUNT_LIMIT = 1000;
    */
    private static final String SRC_BASE_DIR = "F:\\REPO-IMPORT\\GEN\\roms";
    private static final String DST_BASE_DIR = "F:\\REPO-IMPORT";
    private static final String DST_FILENAME_PREFIX = "34.GEN-";
    private static final int START_COUNT = 1;
    private static final int COUNT_LIMIT = 1000;
    private static final long MAX_ZIPFILE_SIZE = 800000000;

    public static void main(String[] args) {
        try {
            int sufix = START_COUNT;
            File[] fs = new File(SRC_BASE_DIR).listFiles();
            Map<Integer, List<File>> mapFiles = new HashMap<>();
            long fileSize = 0;
            System.out.println("Total de arquivos: " + fs.length);
            for (File f : fs) {
                if (!f.isFile()) continue;
                List<File> files = mapFiles.get(sufix);
                if (files == null) {
                    files = new LinkedList<>();
                    files.add(f);
                    mapFiles.put(sufix, files);
                    fileSize = f.length();
                } else {
                    files.add(f);
                    fileSize += f.length();
                    if (files.size() >= COUNT_LIMIT || fileSize > MAX_ZIPFILE_SIZE) {
                        sufix++;
                    }
                }
            }
            for (Integer key : mapFiles.keySet()) {
                List<File> files = mapFiles.get(key);

                File zipFile = new File(DST_BASE_DIR);
                zipFile = new File(zipFile, DST_FILENAME_PREFIX + (key < 10 ? "0" : "")
                        + key + ".zip");
                System.out.println("Criando arquivo: " + zipFile + " -> " + files.size() + " -> " + files.get(files.size() - 1));
                ZipUtil.createZipFile(zipFile, files);
            }
            System.out.println("========== FIM ===========");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
