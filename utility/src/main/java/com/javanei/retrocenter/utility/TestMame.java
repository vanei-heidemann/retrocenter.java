package com.javanei.retrocenter.utility;

import com.javanei.retrocenter.catalog.mame.common.MameGame;
import com.javanei.retrocenter.catalog.mame.parser.MameNameParser;
import com.javanei.retrocenter.mame.Mame;
import com.javanei.retrocenter.mame.MameMachine;
import com.javanei.retrocenter.mame.parser.MameParser;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TestMame {
    public static void main(String[] args) {
        Set<String> names = new HashSet<>();
        List<String> compl = new ArrayList<>();
        try {
            System.out.println(new Date() + " - BEGIN");
            File file = new File("F:/Downloads/Emulator/MAME/mame0187.xml");
            MameParser parser = new MameParser();
            Mame mame = parser.parse(file);
            System.out.println(new Date() + " - Parse OK");

            MameNameParser nameParser = new MameNameParser();
            for (MameMachine m : mame.getMachines()) {
                if (m.getIsdevice().equals("yes")) {
                    continue;
                }
                MameGame g = nameParser.parseName(m.getDescription());
                names.add(g.getMainName());
                for (String s : g.getComplements()) {
                    if (!compl.contains(s)) {
                        compl.add(s);
                    }
                }
            }
            Collections.sort(compl);
            for (String s : compl) {
                System.out.println(s);
            }
            System.out.println(new Date() + " === total: " + compl.size());
            //System.out.println(compl);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
