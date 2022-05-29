package io;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
/**
 * 
 *
 * @param <T>
 */
public class FileWriterImpl<T> implements FileWriter<T> {

    private final JsonSerializer jSerializer;
    private final String fileName;
    private final String fs = System.getProperty("file.separator");
    private final String cd =  System.getProperty("user.dir");
    /**
     * 
     * @param fileName the name of the file in which to write the object
     * @param className the name of the class to be serialized
     */
    public FileWriterImpl(final String fileName, final Class<T> className) {
            this.fileName = fileName;
            jSerializer = new JsonSerializerImpl();
    }

    @Override
    public void writeFile(final T obj) throws IOException {
            final FileOutputStream file = new FileOutputStream(cd + fs + fileName);
            try (var a = new BufferedWriter(new OutputStreamWriter(file, StandardCharsets.UTF_8))) {
                a.write(jSerializer.serialize(obj));
            }
    }

}
