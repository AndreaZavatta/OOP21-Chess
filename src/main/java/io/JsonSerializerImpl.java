package io;

import java.io.Serializable;
import java.util.Objects;
import com.google.gson.Gson;
/**
 * 
 *
 * @param <T>
 */
public class JsonSerializerImpl<T extends Serializable> implements JsonSerializer {
    private Gson gson = new Gson();
    private final Class<T> className;
    /**
     * 
     * @param className
     */
    public JsonSerializerImpl(final Class<T> className) {
            this.className = Objects.requireNonNull(className);
    }
    @Override
    public String serialize(final Object obj) {
            if (!(obj.getClass().equals(className))) {
                    throw new IllegalArgumentException();
            }
            return gson.toJson(obj);
    }
}
