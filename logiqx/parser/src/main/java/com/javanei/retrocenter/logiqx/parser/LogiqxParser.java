package com.javanei.retrocenter.logiqx.parser;

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
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class LogiqxParser {
    private static void setValueByReflection(Object to, String attr, Object value) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        String mName = "set" + attr.substring(0, 1).toUpperCase() + attr.substring(1);
        Method m = to.getClass().getMethod(mName, value.getClass());
        m.invoke(to, value);
    }

    private static void setValueByAttribute(Object to, Node attr) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String aName = attr.getNodeName();
        String value = attr.getTextContent().trim();
        try {
            setValueByReflection(to, aName, value);
        } catch (NoSuchMethodException ex) {
            throw new IllegalArgumentException("Unknown Attribute: " + attr.getNodeName());
        }
    }

    private static void setValueByAttributes(Object to, NamedNodeMap attrs) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        if (attrs != null) {
            for (int j = 0; j < attrs.getLength(); j++) {
                Node attr = attrs.item(j);
                setValueByAttribute(to, attr);
            }
        }
    }

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
                    if (n.getNodeName().equals("header")) {
                        if (r.getHeader() != null) {
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
                                    setValueByAttributes(header, n1.getAttributes());
                                    break;
                                default:
                                    try {
                                        setValueByReflection(header, n1.getNodeName().trim(), n1.getTextContent().trim());
                                    } catch (NoSuchMethodException ex) {
                                        throw new IllegalArgumentException("Invalid tag: " + n1.getNodeName());
                                    }
                            }
                        }
                    } else if (n.getNodeName().equals("game")) {
                        LogiqxGame game = new LogiqxGame();
                        setValueByAttributes(game, n.getAttributes());
                        NodeList nl = n.getChildNodes();
                        for (int j = 0; j < nl.getLength(); j++) {
                            Node n1 = nl.item(j);
                            switch (n1.getNodeName().trim()) {
                                case "#text":
                                    break;
                                case "release":
                                    LogiqxRelease release = new LogiqxRelease();
                                    setValueByAttributes(release, n1.getAttributes());
                                    game.addRelease(release);
                                    break;
                                case "biosset":
                                    LogiqxBiosset biosset = new LogiqxBiosset();
                                    setValueByAttributes(biosset, n1.getAttributes());
                                    game.addBiosset(biosset);
                                    break;
                                case "rom":
                                    LogiqxRom rom = new LogiqxRom();
                                    setValueByAttributes(rom, n1.getAttributes());
                                    game.addRom(rom);
                                    break;
                                case "disk":
                                    LogiqxDisk disk = new LogiqxDisk();
                                    setValueByAttributes(disk, n1.getAttributes());
                                    game.addDisk(disk);
                                    break;
                                case "sample":
                                    LogiqxSample sample = new LogiqxSample();
                                    setValueByAttributes(sample, n1.getAttributes());
                                    game.addSample(sample);
                                    break;
                                case "archive":
                                    LogiqxArchive archive = new LogiqxArchive();
                                    setValueByAttributes(archive, n1.getAttributes());
                                    game.addArchive(archive);
                                    break;
                                default:
                                    try {
                                        setValueByReflection(game, n1.getNodeName().trim(), n1.getTextContent().trim());
                                    } catch (NoSuchMethodException ex) {
                                        throw new IllegalArgumentException("Invalid tag: " + n1.getNodeName());
                                    }
                            }
                        }
                        r.addGame(game);
                    } else {
                        //TODO: Criar Exeption
                        throw new Exception("Invalid tag: " + n.getNodeName());
                    }
                }
            }
        }
        return r;
    }
}
