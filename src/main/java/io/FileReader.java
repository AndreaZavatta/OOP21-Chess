package io;

import java.io.IOException;
import java.io.Serializable;
/**
 * 
 * an iterface for reading from files.
 * @param <T>
 */
public interface FileReader<T extends Serializable> {
        /**
         * a method for reading from files. 
         * @return T
         * @throws IOException
         * @throws ClassNotFoundException
         */
        T readFile() throws IOException, ClassNotFoundException;

}
