package io;
import model.game.Game;

import java.io.IOException;
import java.util.List;

/**
 * 
 * An iterface for reading from files.
 */
public interface JsonFileReader {
        /**
         * A method for reading from files.
         *
         * @return T
         * @throws IOException if reading to file is not done correctly
         */
        List<Game> readFile() throws IOException;

}
