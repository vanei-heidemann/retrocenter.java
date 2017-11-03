package com.javanei.retrocenter.goodtools.parser;

import com.javanei.retrocenter.common.UnknownDatafileFormatException;
import com.javanei.retrocenter.common.UnknownTagException;
import com.javanei.retrocenter.common.util.ReflectionUtil;
import com.javanei.retrocenter.datafile.Parser;
import com.javanei.retrocenter.goodtools.GoodDatafile;
import com.javanei.retrocenter.goodtools.GoodDatafileRom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class GoodParser implements Parser {
    private static final Logger LOG = LoggerFactory.getLogger(GoodParser.class);

    public GoodDatafile parse(File file) throws UnknownDatafileFormatException, IOException {
        LOG.info("parse(" + file + ")");
        try (FileInputStream is = new FileInputStream(file)) {
            return parse(is);
        }
    }

    public GoodDatafile parse(InputStream is) throws UnknownDatafileFormatException, IOException {
        LOG.info("parse(is)");
        GoodDatafile r = new GoodDatafile();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setIgnoringComments(true);
        factory.setValidating(false);

        DocumentBuilder builder;
        Document doc;
        try {
            builder = factory.newDocumentBuilder();
            doc = builder.parse(is);
        } catch (ParserConfigurationException e) {
            LOG.error(e.getMessage(), e);
            throw new UnknownDatafileFormatException();
        } catch (SAXException e) {
            LOG.error(e.getMessage(), e);
            throw new UnknownDatafileFormatException();
        }
        NodeList datafiles = doc.getChildNodes();
        for (int idatafile = 0; idatafile < datafiles.getLength(); idatafile++) {
            Node datafile = datafiles.item(idatafile);
            if (datafile.getNodeName().equalsIgnoreCase("GoodTools")) {
                ReflectionUtil.setValueByAttributes(r, datafile.getAttributes());
                for (int i = 0; i < datafile.getChildNodes().getLength(); i++) {
                    Node n = datafile.getChildNodes().item(i);
                    if (n.getNodeName().equals("#text")) {
                        continue;
                    }
                    switch (n.getNodeName()) {
                        case "author":
                            r.setAuthor(n.getNodeValue().trim());
                            break;
                        case "date":
                            r.setDate(n.getNodeValue().trim());
                            break;
                        case "comment":
                            r.setComment(n.getNodeValue().trim());
                            break;
                        case "game":
                            NodeList nl = n.getChildNodes();
                            for (int j = 0; j < nl.getLength(); j++) {
                                Node n1 = nl.item(j);
                                switch (n1.getNodeName().trim()) {
                                    case "#text":
                                        break;
                                    case "file":
                                        GoodDatafileRom rom = new GoodDatafileRom();
                                        ReflectionUtil.setValueByAttributes(rom, n1.getAttributes());
                                        r.addRom(rom);
                                        break;
                                    default:
                                        throw new UnknownTagException(n1.getNodeName());
                                }
                            }
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
