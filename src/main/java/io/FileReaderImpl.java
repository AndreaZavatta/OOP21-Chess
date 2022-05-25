package io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
/**
 * 
 * 
 * @param <T>
 */
public class FileReaderImpl<T extends Serializable> implements FileReader<T> {
    private final JsonDeserializer<T> jDeserializer;
    private final String fileName;
    private final String fs = System.getProperty("file.separator");
    private final String cd =  System.getProperty("user.dir");

    /**
     * 
     * @param fileName
     * @param className
     */
    public FileReaderImpl(final String fileName, final Class<T> className) {
            this.fileName = fileName;
            jDeserializer = new JsonDeserializerImpl<>(className);
    }


    @Override
    public T readFile() throws IOException, ClassNotFoundException {
            final FileInputStream file = new FileInputStream(cd + fs + fileName);
            try (BufferedReader in = new BufferedReader(new InputStreamReader(file, StandardCharsets.UTF_8))) {
                    return jDeserializer.deserialize(composeString(in));
            }
    }


    private String composeString(final BufferedReader in) throws IOException {
            final StringBuilder str = new StringBuilder();
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                str.append(line);
            }
            return str.toString();
    }

}
