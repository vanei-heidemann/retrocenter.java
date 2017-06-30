package com.javanei.retrocenter.datafile;

import java.io.File;
import java.io.InputStream;

public interface Parser {
    DatafileObject parse(File file) throws Exception;

    DatafileObject parse(InputStream is) throws Exception;
}
