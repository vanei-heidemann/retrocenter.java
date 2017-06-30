package com.javanei.retrocenter.logiqx.parser;

import com.javanei.retrocenter.common.UnknownTagException;
import com.javanei.retrocenter.common.util.ReflectionUtil;
import com.javanei.retrocenter.datafile.Parser;
import com.javanei.retrocenter.logiqx.LogiqxArchive;
import com.javanei.retrocenter.logiqx.LogiqxBiosset;
import com.javanei.retrocenter.logiqx.LogiqxDatafile;
import com.javanei.retrocenter.logiqx.LogiqxDisk;
import com.javanei.retrocenter.logiqx.LogiqxGame;
import com.javanei.retrocenter.logiqx.LogiqxHeader;
import com.javanei.retrocenter.logiqx.LogiqxRelease;
import com.javanei.retrocenter.logiqx.LogiqxRom;
import com.javanei.retrocenter.logiqx.LogiqxSample;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class LogiqxParser implements Parser {
    public LogiqxDatafile parse(File file) throws Exception {
        try (FileInputStream is = new FileInputStream(file)) {
            return parse(is);
        }
    }

    public LogiqxDatafile parse(InputStream is) throws Exception {
        LogiqxDatafile r = new LogiqxDatafile();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setIgnoringComments(true);
        factory.setValidating(false);

        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(is);
        NodeList datafiles = doc.getChildNodes();
        for (int idatafile = 0; idatafile < datafiles.getLength(); idatafile++) {
            Node datafile = datafiles.item(idatafile);
            if (datafile.getNodeName().equals("datafile")) {
                for (int i = 0; i < datafile.getChildNodes().getLength(); i++) {
                    Node n = datafile.getChildNodes().item(i);
                    if (n.getNodeName().equals("#text")) {
                        continue;
                    }
                    switch (n.getNodeName()) {
                        case "header":
                            if (r.getHeader() != null) {
                                //TODO: Criar exception
                                throw new IllegalArgumentException("Duplicated header tag");
                            }
                            LogiqxHeader header = new LogiqxHeader();
                            r.setHeader(header);
                            NodeList nl = n.getChildNodes();
                            for (int j = 0; j < nl.getLength(); j++) {
                                Node n1 = nl.item(j);
                                switch (n1.getNodeName()) {
                                    case "#text":
                                        break;
                                    case "clrmamepro":
                                    case "romcenter":
                                        ReflectionUtil.setValueByAttributes(header, n1.getAttributes());
                                        break;
                                    default:
                                        ReflectionUtil.setValueByNodeContent(header, n1);
                                }
                            }
                            break;
                        case "game":
                            LogiqxGame game = new LogiqxGame();
                            ReflectionUtil.setValueByAttributes(game, n.getAttributes());
                            nl = n.getChildNodes();
                            for (int j = 0; j < nl.getLength(); j++) {
                                Node n1 = nl.item(j);
                                switch (n1.getNodeName().trim()) {
                                    case "#text":
                                        break;
                                    case "release":
                                        LogiqxRelease release = new LogiqxRelease();
                                        ReflectionUtil.setValueByAttributes(release, n1.getAttributes());
                                        game.addRelease(release);
                                        break;
                                    case "biosset":
                                        LogiqxBiosset biosset = new LogiqxBiosset();
                                        ReflectionUtil.setValueByAttributes(biosset, n1.getAttributes());
                                        game.addBiosset(biosset);
                                        break;
                                    case "rom":
                                        LogiqxRom rom = new LogiqxRom();
                                        ReflectionUtil.setValueByAttributes(rom, n1.getAttributes());
                                        game.addRom(rom);
                                        break;
                                    case "disk":
                                        LogiqxDisk disk = new LogiqxDisk();
                                        ReflectionUtil.setValueByAttributes(disk, n1.getAttributes());
                                        game.addDisk(disk);
                                        break;
                                    case "sample":
                                        LogiqxSample sample = new LogiqxSample();
                                        ReflectionUtil.setValueByAttributes(sample, n1.getAttributes());
                                        game.addSample(sample);
                                        break;
                                    case "archive":
                                        LogiqxArchive archive = new LogiqxArchive();
                                        ReflectionUtil.setValueByAttributes(archive, n1.getAttributes());
                                        game.addArchive(archive);
                                        break;
                                    default:
                                        ReflectionUtil.setValueByNodeContent(game, n1);
                                }
                            }
                            r.addGame(game);
                            break;
                        default:
                            throw new UnknownTagException(n.getNodeName());
                    }
                }
            }
        }
        return r;
    }
}
