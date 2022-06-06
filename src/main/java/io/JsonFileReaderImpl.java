package io;

import game.GameImpl;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

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
     */
    public JsonFileReaderImpl(final String fileName) {
            this.fileName = fileName;
            jDeserializer = new JsonDeserializerImpl();
    }


    @Override
    public List<GameImpl> readFile() throws IOException {
            final File file = new File(cd + fs + fileName);
            return jDeserializer.deserialize(composeString(file));
    }


    private String composeString(File file) throws IOException {
        // pass the path to the file as a parameter
        Scanner sc = new Scanner(file);
        StringBuilder str = new StringBuilder();

        while (sc.hasNextLine()) {
            str.append(sc.nextLine());
        }
        return str.toString();

    }

}
