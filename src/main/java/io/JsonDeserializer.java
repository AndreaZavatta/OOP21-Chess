package io;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * an interface to deserialize in a generic object.
 *
 */
public interface JsonDeserializer {
    /**
     * this method is used to convert a string formatted in json to a generic object.
     * @param str the string formatted in json to convert
     * @return T
     */
        Object deserialize(String str) throws JsonProcessingException;
}
