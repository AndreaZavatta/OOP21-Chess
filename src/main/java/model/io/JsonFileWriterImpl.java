package model.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

/**
 * 
 * An implementation of JsonFileWriter, this is used to write a List of game to file.
 */
public class JsonFileWriterImpl implements JsonFileWriter {

    private final JsonSerializer jSerializer;
    private final String fileName;
    private final String fs = System.getProperty("file.separator");
    private final String uh =  System.getProperty("user.home");

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
            final File folder = new File(uh + fs + "LAMAChess");
            if (!folder.exists() && !folder.mkdirs()) {
                throw new IOException();
            }
            writeObject(obj, folder);
    }

    private void writeObject(final Object obj, final File folder) throws IOException {
            try (FileOutputStream file = new FileOutputStream(folder.getAbsoluteFile() + fs + fileName)) {
                write(obj, file);
            }
    }

    private void write(final Object obj, final FileOutputStream file) throws IOException {
        try (var a = new BufferedWriter(new OutputStreamWriter(file, StandardCharsets.UTF_8))) {
            a.write(jSerializer.serialize(obj));
        }
    }


}
