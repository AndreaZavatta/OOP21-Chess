package io;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

/**
 * 
 * an implementation of JsonFileWriter, this is used to write a List of game to file.
 */
public class JsonFileWriterImpl implements JsonFileWriter {

    private final JsonSerializer jSerializer;
    private final String fileName;
    private final String fs = System.getProperty("file.separator");
    private final String cd =  System.getProperty("user.dir");

    /**
     *
     * @param fileName the name of the file in which to write the object
     */
    public JsonFileWriterImpl(final String fileName) {
            this.fileName = fileName;
            jSerializer = new JsonSerializerImpl();
    }

    @Override
    public void writeFile(final Object obj) throws IOException {
            final FileOutputStream file = new FileOutputStream(cd + fs + fileName);
            write(obj, file);
    }

    private void write(final Object obj, final FileOutputStream file) throws IOException {
        try (var a = new BufferedWriter(new OutputStreamWriter(file, StandardCharsets.UTF_8))) {
            a.write(jSerializer.serialize(obj));
        }
    }


}
