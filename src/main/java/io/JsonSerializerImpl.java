package io;
import com.fasterxml.jackson.core.JsonProcessingException;

import static io.JsonUtils.getMapper;
/**
 * 
 * an implementation of JsonSerializer, this is used to serialize a generic object.
 */
public class JsonSerializerImpl implements JsonSerializer {
    @Override
    public String serialize(final Object obj) throws JsonProcessingException {
        return getMapper().writeValueAsString(obj);
    }
}
