package com.javanei.retrocenter.clrmamepro.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.javanei.retrocenter.clrmamepro.CMProDatafile;
import com.javanei.retrocenter.clrmamepro.CMProDisk;
import com.javanei.retrocenter.clrmamepro.CMProGame;
import com.javanei.retrocenter.clrmamepro.CMProHeader;
import com.javanei.retrocenter.clrmamepro.CMProResource;
import com.javanei.retrocenter.clrmamepro.CMProRom;

public class CMProParser {
    private static CMProDisk parseDisk(String line) throws Exception {
        String romLine = line.substring(line.indexOf("(") + 1, line.length() - 1).trim();
        CMProDisk r = new CMProDisk();

        int pos = romLine.indexOf("\"") + 1;
        int endpos = romLine.indexOf("\"", pos + 1);
        r.setName(romLine.substring(pos, endpos));
        String[] ss = romLine.substring(endpos + 2).trim().split(" ");
        for (int i = 0; i < ss.length; i++) {
            switch (ss[i]) {
                case "md5":
                    r.setMd5(ss[++i]);
                    break;
                case "sha1":
                    r.setSha1(ss[++i]);
                    break;
                default:
                    //TODO: Criar exception
                    throw new Exception("Unknown tag value: " + ss[i]);
            }
        }
        return r;
    }

    private static CMProRom parseRom(String line) throws Exception {
        String romLine = line.substring(line.indexOf("(") + 1, line.length() - 1).trim();
        CMProRom r = new CMProRom();

        int pos = romLine.indexOf("\"") + 1;
        int endpos = romLine.indexOf("\"", pos + 1);
        r.setName(romLine.substring(pos, endpos));
        String[] ss = romLine.substring(endpos + 2).trim().split(" ");
        for (int i = 0; i < ss.length; i++) {
            switch (ss[i]) {
                case "size":
                    r.setSize(Long.parseLong(ss[++i]));
                    break;
                case "crc":
                    r.setCrc(ss[++i]);
                    break;
                case "md5":
                    r.setMd5(ss[++i]);
                    break;
                case "sha1":
                    r.setSha1(ss[++i]);
                    break;
                case "region":
                    r.setRegion(ss[++i]);
                    break;
                case "flags":
                    r.setFlags(ss[++i]);
                    break;
                case "nodump":
                    // There is no dump info
                    break;
                default:
                    //TODO: Criar exception
                    throw new Exception("Unknown tag value: " + ss[i]);
            }
        }
        return r;
    }

    private static String extractLineValue(String key, String line) {
        return normalizeString(line.substring(key.length()).trim());
    }

    private static String normalizeString(String s) {
        if (s.startsWith("\"") && s.endsWith("\"")) {
            return s.substring(1, s.length() - 1).trim();
        }
        return s.trim();
    }

    public CMProDatafile parse(File file) throws Exception {
        try (FileInputStream is = new FileInputStream(file)) {
            return parse(is);
        }
    }

    public CMProDatafile parse(InputStream is) throws Exception {
        CMProDatafile r = null;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String line = reader.readLine().trim();
            String[] ss = line.split(" ");
            if (!ss[0].trim().equals("clrmamepro")) {
                //TODO: Criar exception
                throw new Exception("Invalid ClrMamePro dat file format");
            }

            CMProHeader header = new CMProHeader();
            r = new CMProDatafile(header);
            line = reader.readLine().trim();
            while (!line.equals(")")) {
                if (line.startsWith("name")) {
                    header.setName(extractLineValue("name", line));
                } else if (line.startsWith("description")) {
                    header.setDescription(extractLineValue("description", line));
                } else if (line.startsWith("category")) {
                    header.setCategory(extractLineValue("category", line));
                } else if (line.startsWith("version")) {
                    header.setVersion(extractLineValue("version", line));
                } else if (line.startsWith("author")) {
                    header.setAuthor(extractLineValue("author", line));
                } else if (line.startsWith("forcemerging")) {
                    //TODO: Ignorando
                } else if (line.startsWith("forcezipping")) {
                    //TODO: Ignorando
                } else {
                    String key = line.substring(0, line.indexOf(" ")).trim();
                    header.addCustomField(key, extractLineValue(key, line));
                }

                line = reader.readLine().trim();
            }

            line = reader.readLine();
            while (line != null) {
                if (line.startsWith("game")) {
                    CMProGame game = new CMProGame();
                    line = reader.readLine().trim();
                    while (!line.equals(")")) {
                        if (line.startsWith("name")) {
                            game.setName(extractLineValue("name", line));
                        } else if (line.startsWith("description")) {
                            game.setDescription(extractLineValue("description", line));
                        } else if (line.startsWith("year")) {
                            game.setYear(extractLineValue("year", line));
                        } else if (line.startsWith("manufacturer")) {
                            game.setManufacturer(extractLineValue("manufacturer", line));
                        } else if (line.startsWith("cloneof")) {
                            game.setCloneof(extractLineValue("cloneof", line));
                        } else if (line.startsWith("romof")) {
                            game.setRomof(extractLineValue("romof", line));
                        } else if (line.startsWith("sampleof")) {
                            game.addSampleOf(extractLineValue("sampleof", line));
                        } else if (line.startsWith("rom")) {
                            game.addRom(parseRom(line));
                        } else if (line.startsWith("disk")) {
                            game.addDisk(parseDisk(line));
                        } else if (line.startsWith("sample")) {
                            game.addSample(extractLineValue("sample", line));
                        }
                        line = reader.readLine().trim();
                    }
                    r.addGame(game);
                } else if (line.startsWith("resource")) {
                    CMProResource resource = new CMProResource();
                    line = reader.readLine().trim();
                    while (!line.equals(")")) {
                        if (line.startsWith("name")) {
                            resource.setName(extractLineValue("name", line));
                        } else if (line.startsWith("description")) {
                            resource.setDescription(extractLineValue("description", line));
                        } else if (line.startsWith("year")) {
                            resource.setYear(extractLineValue("year", line));
                        } else if (line.startsWith("manufacturer")) {
                            resource.setManufacturer(extractLineValue("manufacturer", line));
                        } else if (line.startsWith("rom")) {
                            resource.addRom(parseRom(line));
                        }
                        line = reader.readLine().trim();
                    }
                    r.addResource(resource);
                } else if (!line.isEmpty()) {
                    throw new Exception("Unknown tag value: " + line);
                }

                line = reader.readLine();
                if (line != null) line = line.trim();
            }
        }

        return r;
    }
}
