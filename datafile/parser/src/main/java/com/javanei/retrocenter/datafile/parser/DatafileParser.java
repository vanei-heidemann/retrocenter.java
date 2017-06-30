package com.javanei.retrocenter.datafile.parser;

import com.javanei.retrocenter.clrmamepro.parser.CMProParser;
import com.javanei.retrocenter.common.UnknownDatafileFormatException;
import com.javanei.retrocenter.logiqx.parser.LogiqxParser;
import com.javanei.retrocenter.mame.parser.MameParser;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;

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
                    parser = new RetrocenterDatafileParser();
                } else if (s.contains("<mame ")) {
                    parser = new MameParser();
                } else if (s.contains("clrmamepro (")) {
                    parser = new CMProParser();
                } else if (s.contains("logiqx") || s.contains("<datafile")) {
                    parser = new LogiqxParser();
                }
            }
            size = is.read(b);
        }
        if (parser == null) {
            throw new UnknownDatafileFormatException();
        }

        Object result = null;
        if (parser instanceof RetrocenterDatafileParser) {
            result = ((RetrocenterDatafileParser) parser).parse(new ByteArrayInputStream(os.toByteArray()));
        } else if (parser instanceof MameParser) {
            result = ((MameParser) parser).parse(new ByteArrayInputStream(os.toByteArray()));
        } else if (parser instanceof CMProParser) {
            result = ((CMProParser) parser).parse(new ByteArrayInputStream(os.toByteArray()));
        } else if (parser instanceof LogiqxParser) {
            result = ((LogiqxParser) parser).parse(new ByteArrayInputStream(os.toByteArray()));
        }
        return result;
    }
}
