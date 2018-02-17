package com.javanei.retrocenter.utility;

import com.javanei.retrocenter.common.util.FileUtil;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ComparaDuplicadoPorCRC {
    public static void main(String[] args) {
        try {
            File[] a26 = new File("G:\\importar\\goodset\\Good2600 v3.14\\2600Ren").listFiles();
            Map<Long, File> filesA26 = new HashMap<>();
            for (File f : a26) {
                filesA26.put(FileUtil.getCRC(f), f);
            }
            System.out.println(filesA26.size());

            File[] bin = new File("G:\\importar\\goodset\\Good2600 v3.14\\2600Ren.old").listFiles();
            for (File f : bin) {
                Long crc = FileUtil.getCRC(f);
                if (filesA26.get(crc) == null) {
                    System.out.println("Nao existe: " + f.getAbsolutePath());
                    FileUtil.copyFile(f, new File("G:\\importar\\goodset\\Good2600 v3.14", f.getName()));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
