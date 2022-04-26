package io;

import java.io.Serializable;
/**
 * an interface to deserialize in a generic object.
 *
 * @param <T>
 */
public interface JsonDeserializer<T extends Serializable> {
    /**
     * this method is used to convert a string formatted in json to a generic object.
     * @param str
     * @return T
     */
        T deserialize(String str);
}
