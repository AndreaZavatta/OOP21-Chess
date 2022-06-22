package io;

import model.game.Game;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * 
 * an implementation of JsonFileReader, this is used to read a list of game from file.
 */
public class JsonFileReaderImpl implements JsonFileReader {
    private final JsonDeserializer jDeserializer;
    private final String fileName;
    private final String fs = System.getProperty("file.separator");
    private final String uh =  System.getProperty("user.home");

    /**
     * 
     * @param fileName the name of the file in which to save the object
     */
    public JsonFileReaderImpl(final String fileName) {
            this.fileName = fileName;
            jDeserializer = new JsonDeserializerImpl();
    }


    @Override
    public List<Game> readFile() throws IOException {
            final File file = new File(uh + fs + "LAMAChess" + fs + fileName);
            return jDeserializer.deserialize(fromFileToString(file));
    }


    private String fromFileToString(final File file) throws IOException {
        return file.exists() ? getContentFromFile(file) : "";
    }

    private String getContentFromFile(final File file) throws FileNotFoundException {
        try (Scanner sc = new Scanner(file)) {
            final StringBuilder str = new StringBuilder();
            while (sc.hasNextLine()) {
                str.append(sc.nextLine());
            }
            return str.toString();
        }
    }
}
