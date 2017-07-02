package com.javanei.retrocenter.datafile;

import com.javanei.retrocenter.common.UnknownDatafileFormatException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public interface Parser {
    DatafileObject parse(File file) throws UnknownDatafileFormatException, IOException;

    DatafileObject parse(InputStream is) throws UnknownDatafileFormatException, IOException;
}
