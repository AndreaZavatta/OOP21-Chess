package io;

import java.io.Serializable;
/**
 * @param <T>
 * the generic type T must implement Serializable in order to be serialized and deserialized.
 * 
 *
 */
public class JsonConverterImpl<T extends Serializable> implements JsonConverter<T> {

    @Override
    public T deserialize(final String jsonString) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String serialize(final Object obj) throws IllegalArgumentException {
        // TODO Auto-generated method stub
        return null;
    }

}
