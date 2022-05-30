package io;

import java.io.IOException;

/**
 * 
 * a class to save an object in a file.
 * @param <T>
 */
public interface JsonFileWriter<T> {

    /**
     * a method for writing to a file.
     * @param obj the object to be written to file
     * @throws IOException if writing to file is not done correctly
     */
        void writeFile(Object obj) throws IOException;
}
