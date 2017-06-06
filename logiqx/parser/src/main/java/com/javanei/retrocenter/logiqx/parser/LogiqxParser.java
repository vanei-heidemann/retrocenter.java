package com.javanei.retrocenter.logiqx.parser;

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

import com.javanei.retrocenter.logiqx.LogiqxDatafile;
import com.javanei.retrocenter.logiqx.LogiqxGame;
import com.javanei.retrocenter.logiqx.LogiqxHeader;

public class LogiqxParser {
    private static void setValueByReflection(Object to, String attr, Object value) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        String mName = "set" + attr.substring(0, 1).toUpperCase() + attr.substring(1);
        Method m = to.getClass().getMethod(mName, value.getClass());
        m.invoke(to, value);
    }

    private static void setValueByAttribute(Object to, Node attr) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String aName = attr.getNodeName();
        String value = attr.getTextContent().trim();
        setValueByReflection(to, aName, value);
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
                                    setValueByReflection(header, n1.getNodeName().trim(), n1.getTextContent().trim());
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
                                    //TODO:
                                    break;
                                case "biosset":
                                    //TODO:
                                    break;
                                case "rom":
                                    //TODO:
                                    break;
                                case "disk":
                                    //TODO:
                                    break;
                                case "sample":
                                    //TODO:
                                    break;
                                case "archive":
                                    //TODO:
                                    break;
                                default:
                                    setValueByReflection(game, n1.getNodeName().trim(), n1.getTextContent().trim());
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
