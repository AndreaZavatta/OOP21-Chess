package io;

import java.io.IOException;
import java.io.Serializable;
/**
 * 
 * 
 * @param <T>
 */
public class FileReaderImpl<T extends Serializable> extends AbstractFileManager<T> implements FileReader<T> {

    @Override
    public T readFile() throws IOException, ClassNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }

}
