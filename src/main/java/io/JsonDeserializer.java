package io;

import model.game.Game;

import java.io.IOException;
import java.util.List;

/**
 * an interface to deserialize in a generic object.
 *
 */
public interface JsonDeserializer {
    /**
     * this method is used to convert a string formatted in json to a generic object.
     *
     * @param str the string formatted in json to convert
     * @return the List of {@link Game Game.class} that represent the string
     * @throws IOException If the deserialization fails 
     */
        List<Game> deserialize(String str) throws IOException;
}
