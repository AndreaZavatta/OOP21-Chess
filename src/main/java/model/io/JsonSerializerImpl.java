package model.io;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * 
 * An implementation of JsonSerializer, this is used to serialize a generic object.
 */
public class JsonSerializerImpl implements JsonSerializer {
    @Override
    public String serialize(final Object obj) throws JsonProcessingException {
        return JsonUtils.getMapper().writeValueAsString(obj);
    }
}
