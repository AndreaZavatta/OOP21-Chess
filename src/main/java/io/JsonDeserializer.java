package io;

import com.fasterxml.jackson.core.JsonProcessingException;
import game.Game;

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
     * @return T
     */
        List<Game> deserialize(String str) throws JsonProcessingException;
}
