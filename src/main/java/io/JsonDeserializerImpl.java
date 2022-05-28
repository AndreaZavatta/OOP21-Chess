package io;


import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Objects;
import static io.JsonUtils.getMapper;
/**
 * 
 *
 * @param <T>
 */
public class JsonDeserializerImpl<T> implements JsonDeserializer<T> {
    private final Class<T> className;
    /**
     * 
     * @param className the name of the class to be deserialized
     */
    public JsonDeserializerImpl(final Class<T> className) {
            this.className = Objects.requireNonNull(className);
    }
    @Override
    public T deserialize(final String str) throws JsonProcessingException {
            return getMapper().readValue(str, className);
    }
}
