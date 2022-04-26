package io;

import java.io.IOException;
import java.io.Serializable;
/**
 * 
 *
 * @param <T>
 */
public class FileWriterImpl<T extends Serializable> extends AbstractFileManager<T> implements FileWriter<T> {

    @Override
    public void writeFile(final T obj) throws IOException {
        // TODO Auto-generated method stub
    }

}
