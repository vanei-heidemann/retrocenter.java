package com.javanei.retrocenter.utility;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.LinkedList;
import java.util.List;

public class AcertaDiretorios {
    private static final File BASE_DIR = new File("F:/RETROCENTER-REPO");
    private static final File BASE_DEST_DIR = new File("F:/RETROCENTER-REPO");

    public static void main(String[] args) {
        try {
            List<File> files = new LinkedList<>();
            processDir(BASE_DIR, files);
            System.out.println("== " + files.size());

            for (File f : files) {
                File file = mkdir(f);
                System.out.println(f + " -> " + file);
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                Path src = f.toPath();
                Path dst = file.toPath();
                Files.move(src, dst, StandardCopyOption.ATOMIC_MOVE,
                        StandardCopyOption.REPLACE_EXISTING);
            }

            File[] fs = BASE_DIR.listFiles();
            if (fs != null) {
                for (File f : fs) {
                    if (f.isDirectory() && !f.getName().equals(BASE_DEST_DIR.getName())) {
                        System.out.println(f);
                        if (f.listFiles() == null || f.listFiles().length == 0) {
                            f.delete();
                        }
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static File mkdir(File f) {
        File r = new File(new File(
                new File(
                        new File(
                                new File(
                                        new File(
                                                new File(BASE_DEST_DIR, "REPO"),
                                                f.getName().substring(0, 1) + "0000000"),
                                        f.getName().substring(8, 9) + "0000000"),
                                f.getName().substring(16, 17) + "0000000"),
                        f.getName().substring(24, 25) + "0000000"),
                f.getName().substring(32, 33) + "0000000"),
                f.getName());
        return r;
    }

    private static void processDir(File dir, List<File> files) throws Exception {
        System.out.println("Processando diretorio: " + dir);
        File[] fs = dir.listFiles();
        if (fs != null) {
            for (File f : fs) {
                if (f.isDirectory()) {
                    if (!f.getName().equals(BASE_DEST_DIR.getName())) {
                        processDir(f, files);
                    }
                } else if (f.isFile() && f.getName().toLowerCase().endsWith(".zip")) {
                    files.add(f);
                }
            }
        }
    }
}
