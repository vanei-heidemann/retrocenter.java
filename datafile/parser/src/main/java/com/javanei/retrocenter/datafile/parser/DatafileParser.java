package com.javanei.retrocenter.datafile.parser;

import com.javanei.retrocenter.clrmamepro.parser.CMProParser;
import com.javanei.retrocenter.common.UnknownDatafileFormatException;
import com.javanei.retrocenter.common.util.ReflectionUtil;
import com.javanei.retrocenter.datafile.Artifact;
import com.javanei.retrocenter.datafile.ArtifactFile;
import com.javanei.retrocenter.datafile.Datafile;
import com.javanei.retrocenter.datafile.Release;
import com.javanei.retrocenter.logiqx.parser.LogiqxParser;
import com.javanei.retrocenter.mame.parser.MameParser;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DatafileParser {
    public Object parse(File file) throws Exception {
        try (FileInputStream is = new FileInputStream(file)) {
            return parse(is);
        }
    }

    public Object parse(InputStream is) throws Exception {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        byte[] b = new byte[8192];
        int size = is.read(b);
        String s = new String(b, 0, size, Charset.forName("UTF-8"));
        Object parser = null;
        while (size > 0) {
            os.write(b, 0, size);
            if (parser == null) {
                if (s.contains("<retrocenter")) {
                    parser = this;
                } else if (s.contains("<mame ")) {
                    parser = MameParser.class.newInstance();
                } else if (s.contains("clrmamepro (")) {
                    parser = CMProParser.class.newInstance();
                } else if (s.contains("logiqx") || s.contains("<datafile")) {
                    parser = LogiqxParser.class.newInstance();
                }
            }
            size = is.read(b);
        }
        if (parser == null) {
            throw new UnknownDatafileFormatException();
        }

        Object result = null;
        if (parser == this) {
            result = parse(os.toByteArray());
        } else if (parser instanceof MameParser) {
            result = ((MameParser) parser).parse(new ByteArrayInputStream(os.toByteArray()));
        } else if (parser instanceof CMProParser) {
            result = ((CMProParser) parser).parse(new ByteArrayInputStream(os.toByteArray()));
        } else if (parser instanceof LogiqxParser) {
            result = ((LogiqxParser) parser).parse(new ByteArrayInputStream(os.toByteArray()));
        }
        return result;
    }

    private Datafile parse(byte[] bytes) throws Exception {
        Datafile r = new Datafile();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setIgnoringComments(true);
        factory.setValidating(false);

        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new ByteArrayInputStream(bytes));
        NodeList datafiles = doc.getChildNodes();
        for (int idatafile = 0; idatafile < datafiles.getLength(); idatafile++) {
            Node datafile = datafiles.item(idatafile);
            if (datafile.getNodeName().equals("retrocenter")) {
                ReflectionUtil.setValueByAttributes(r, datafile.getAttributes());
                for (int i = 0; i < datafile.getChildNodes().getLength(); i++) {
                    Node n = datafile.getChildNodes().item(i);
                    if (n.getNodeName().equals("#text")) {
                        continue;
                    }
                    switch (n.getNodeName()) {
                        case "artifact":
                            Artifact game = new Artifact();
                            ReflectionUtil.setValueByAttributes(game, n.getAttributes());
                            NodeList nl = n.getChildNodes();
                            for (int j = 0; j < nl.getLength(); j++) {
                                Node n1 = nl.item(j);
                                switch (n1.getNodeName().trim()) {
                                    case "#text":
                                        break;
                                    case "release":
                                        Release release = new Release();
                                        ReflectionUtil.setValueByAttributes(release, n1.getAttributes());
                                        game.addRelease(release);
                                        break;
                                    case "file":
                                        ArtifactFile rom = new ArtifactFile();
                                        ReflectionUtil.setValueByAttributes(rom, n1.getAttributes());
                                        NodeList nfile = n1.getChildNodes();
                                        for (int k = 0; k < nfile.getLength(); k++) {
                                            Node k1 = nfile.item(k);
                                            switch (k1.getNodeName().trim()) {
                                                case "#text":
                                                    break;
                                                default:
                                                    ReflectionUtil.setValueByNodeContent(rom, k1);
                                            }
                                        }
                                        game.addFile(rom);
                                        break;
                                    default:
                                        ReflectionUtil.setValueByNodeContent(game, n1);
                                }
                            }
                            r.addArtifact(game);
                            break;
                        default:
                            ReflectionUtil.setValueByNodeContent(r, n);
                    }
                }
            }
        }
        return r;
    }
}
