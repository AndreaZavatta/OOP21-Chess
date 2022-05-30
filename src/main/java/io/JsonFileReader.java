package io;

import java.io.IOException;

/**
 * 
 * an iterface for reading from files.
 */
public interface JsonFileReader {
        /**
         * a method for reading from files. 
         * @return T
         * @throws IOException if reading to file is not done correctly
         */
        Object readFile() throws IOException;

}
