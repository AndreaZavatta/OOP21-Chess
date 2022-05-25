package io;

import java.io.Serializable;
import java.util.Objects;
import com.google.gson.Gson;
/**
 * 
 *
 * @param <T>
 */
public class JsonDeserializerImpl<T extends Serializable> implements JsonDeserializer<T> {
    private final Gson gson = new Gson();
    private final Class<T> className;
    /**
     * 
     * @param className
     */
    public JsonDeserializerImpl(final Class<T> className) {
            this.className = Objects.requireNonNull(className);
    }
    @Override
    public T deserialize(final String str) {
            return gson.fromJson(Objects.requireNonNull(str), className);
    }
}
