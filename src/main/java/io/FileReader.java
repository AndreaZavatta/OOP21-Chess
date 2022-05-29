package io;

import java.io.IOException;
import java.io.Serializable;
/**
 * 
 * an iterface for reading from files.
 * @param <T>
 */
public interface FileReader<T> {
        /**
         * a method for reading from files. 
         * @return T
         * @throws IOException if reading to file is not done correctly
         */
        T readFile() throws IOException;

}
