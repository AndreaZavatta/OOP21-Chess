package model.io;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * An interface to serialize an object.
 *
 */
public interface JsonSerializer {
    /**
     * This method is used to convert an object in a string formatted in Json.
     * @param obj the object that needs to be converted in json string
     * @return String that represent the object
     * @throws JsonProcessingException if serialization fail
     */
        String serialize(Object obj) throws JsonProcessingException;
}
