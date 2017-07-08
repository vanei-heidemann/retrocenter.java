package com.javanei.retrocenter.hyperlist.parser;

import com.javanei.retrocenter.common.UnknownDatafileFormatException;
import com.javanei.retrocenter.common.UnknownTagException;
import com.javanei.retrocenter.common.util.ReflectionUtil;
import com.javanei.retrocenter.datafile.Parser;
import com.javanei.retrocenter.hyperlist.HyperListGame;
import com.javanei.retrocenter.hyperlist.HyperListHeader;
import com.javanei.retrocenter.hyperlist.HyperListMenu;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class HyperListParser implements Parser {
    private static final Logger LOG = LoggerFactory.getLogger(HyperListParser.class);

    @Override
    public HyperListMenu parse(File file) throws UnknownDatafileFormatException, IOException {
        LOG.info("parse(" + file + ")");
        try (FileInputStream is = new FileInputStream(file)) {
            return parse(is);
        }
    }

    @Override
    public HyperListMenu parse(InputStream is) throws UnknownDatafileFormatException, IOException {
        LOG.info("parse(is)");
        HyperListMenu r = new HyperListMenu();

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
            if (datafile.getNodeName().equals("menu")) {
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
                            HyperListHeader header = new HyperListHeader();
                            r.setHeader(header);
                            NodeList nl = n.getChildNodes();
                            for (int j = 0; j < nl.getLength(); j++) {
                                Node n1 = nl.item(j);
                                switch (n1.getNodeName()) {
                                    case "#text":
                                        break;
                                    default:
                                        ReflectionUtil.setValueByNodeContent(header, n1);
                                }
                            }
                            break;
                        case "game":
                            HyperListGame game = new HyperListGame();
                            ReflectionUtil.setValueByAttributes(game, n.getAttributes());
                            nl = n.getChildNodes();
                            for (int j = 0; j < nl.getLength(); j++) {
                                Node n1 = nl.item(j);
                                switch (n1.getNodeName().trim()) {
                                    case "#text":
                                        break;
                                    case "enabled":
                                        // Ignora
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
