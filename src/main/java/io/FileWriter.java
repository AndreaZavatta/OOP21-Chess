package io;

import java.io.IOException;
import java.io.Serializable;
/**
 * 
 * a class to save an object in a file.
 * @param <T>
 */
public interface FileWriter<T extends Serializable> {

    /**
     * a method for writing to a file.
     * @param obj the object to be written to file
     * @throws IOException if writing to file is not done correctly
     */
        void writeFile(T obj) throws IOException;

}
