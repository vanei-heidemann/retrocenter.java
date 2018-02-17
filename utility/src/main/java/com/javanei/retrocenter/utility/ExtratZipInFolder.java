package com.javanei.retrocenter.utility;

import com.javanei.retrocenter.common.util.ZipUtil;

import java.io.File;

public class ExtratZipInFolder {
    private static final String BASE_DIR = "G:\\importar\\goodset\\GoodGen v3.21\\GenRen";
    private static boolean delete = true;

    public static void main(String[] args) {
        try {
            processFile(new File(BASE_DIR));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void processFile(File src) throws Exception {
        System.out.println("Processando arquivo: " + src.getAbsolutePath()
                + " : isDirectory=" + src.isDirectory()
                + " : isFile=" + src.isFile()
        );
        if (src.isDirectory()) {
            File[] fs = src.listFiles();
            for (File f : fs) {
                processFile(f);
            }
        } else if (src.isFile()) {
            if (src.getName().toLowerCase().endsWith(".zip")) {
                System.out.println("    @@@ Extraindo arquivo: " + src.getAbsolutePath());
                ZipUtil.extract(src, src.getParentFile());
                if (delete) {
                    src.delete();
                }
            }
        }
    }
}
