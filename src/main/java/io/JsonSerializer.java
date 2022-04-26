package io;

import java.io.Serializable;
/**
 * an interface to serialize an object.
 *
 * @param <T>
 */
public interface JsonSerializer<T extends Serializable> {
    /**
     * this method is used to convert an object in a string formatted in Json.
     * @param obj
     * @return String
     */
        String serialize(Object obj);
}
