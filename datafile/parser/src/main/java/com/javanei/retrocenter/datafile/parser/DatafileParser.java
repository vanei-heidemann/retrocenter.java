package com.javanei.retrocenter.datafile.parser;

import com.javanei.retrocenter.clrmamepro.parser.CMProParser;
import com.javanei.retrocenter.common.UnknownDatafileFormatException;
import com.javanei.retrocenter.datafile.DatafileObject;
import com.javanei.retrocenter.datafile.Parser;
import com.javanei.retrocenter.logiqx.parser.LogiqxParser;
import com.javanei.retrocenter.mame.parser.MameParser;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;

public class DatafileParser implements Parser {
    public DatafileObject parse(File file) throws Exception {
        try (FileInputStream is = new FileInputStream(file)) {
            return parse(is);
        }
    }

    public DatafileObject parse(InputStream is) throws Exception {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        byte[] b = new byte[8192];
        int size = is.read(b);
        String s = new String(b, 0, size, Charset.forName("UTF-8"));
        Parser parser = null;
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

        return parser.parse(new ByteArrayInputStream(os.toByteArray()));
    }
}
