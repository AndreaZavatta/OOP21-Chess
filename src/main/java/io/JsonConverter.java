package io;
import java.io.Serializable;
/**
 * the JsonConverter interface allows you to convert a Json into a generic Object and vice versa.
 *
 * @param <T>
 */
public interface JsonConverter<T extends Serializable> {
/**
 * reconstruct a generic object from a json formatted string.
 * @param jsonString - the string formatted in json 
 * @return T - the generic T
 */
        T deserialize(String jsonString);
/**
 * creates a URL-encoded text string by serializing from a generic object.
 * @param obj - the generic Object
 * @return String - the string formatted in json
 */
        String serialize(Object obj) throws IllegalArgumentException;

}
