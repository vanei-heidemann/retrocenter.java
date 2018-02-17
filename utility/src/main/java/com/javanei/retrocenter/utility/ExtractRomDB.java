package com.javanei.retrocenter.utility;

import com.javanei.retrocenter.common.util.FileUtil;
import com.javanei.retrocenter.common.util.ZipUtil;

import java.io.File;
import java.util.Map;

public class ExtractRomDB {
    private static final String SOURCE_DIR = "F:/retrocenter";
    private static final String DEST_DIR = "F:/REPO-IMPORT";

    public static void main(String[] args) {
        try {
            File src = new File(SOURCE_DIR);
            File dst = new File(DEST_DIR);
            File[] fs = src.listFiles();
            for (File f : fs) {
                processFile(f, dst);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void processFile(File src, File destDir) throws Exception {
        System.out.println("Processando arquivo: " + src.getAbsolutePath()
                + " : isDirectory=" + src.isDirectory()
                + " : isFile=" + src.isFile()
        );
        if (src.isDirectory()) {
            File[] fs = src.listFiles();
            for (File f : fs) {
                processFile(f, new File(destDir, src.getName()));
            }
        } else if (src.isFile()) {
            if (src.getName().toLowerCase().endsWith(".zip")) {
                String comment = ZipUtil.getComment(src);
                if (comment != null) {
                    Map<String, byte[]> map = ZipUtil.extractToByteArray(src);
                    if (map.size() == 1) {
                        File destFile = new File(destDir, comment);
                        //TODO: salvar
                        System.out.println("Salvando arquivo: " + src.getAbsolutePath() +
                                " para arquivo: " + destFile
                        );
                        FileUtil.writeFile(destFile, map.values().iterator().next());
                    }
                }
            }
        }
    }
}
