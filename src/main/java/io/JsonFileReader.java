package io;

import java.io.IOException;

/**
 * 
 * an iterface for reading from files.
 * @param <T>
 */
public interface JsonFileReader<T> {
        /**
         * a method for reading from files. 
         * @return T
         * @throws IOException if reading to file is not done correctly
         */
        T readFile() throws IOException;

}
