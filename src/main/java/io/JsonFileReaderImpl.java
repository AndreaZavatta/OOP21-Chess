package io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * 
 * 

 */
public class JsonFileReaderImpl implements JsonFileReader {
    private final JsonDeserializer jDeserializer;
    private final String fileName;
    private final String fs = System.getProperty("file.separator");
    private final String cd =  System.getProperty("user.dir");

    /**
     * 
     * @param fileName the name of the file in which to save the object
     * @param className the name of the class to be deserialized
     */
    public JsonFileReaderImpl(final String fileName, final Class<?> className) {
            this.fileName = fileName;
            jDeserializer = new JsonDeserializerImpl(className);
    }


    @Override
    public Object readFile() throws IOException {
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
