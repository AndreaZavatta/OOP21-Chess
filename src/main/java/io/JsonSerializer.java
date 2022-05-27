package io;

/**
 * an interface to serialize an object.
 *
 */
public interface JsonSerializer {
    /**
     * this method is used to convert an object in a string formatted in Json.
     * @param obj the object that needs to be converted in json string
     * @return String
     */
        String serialize(Object obj);
}
