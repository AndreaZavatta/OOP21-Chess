package io;


import com.fasterxml.jackson.core.JsonProcessingException;
import game.GameImpl;

import java.util.List;
import java.util.Objects;
import static io.JsonUtils.getMapper;
import static io.vertx.core.json.Json.mapper;

/**
 * 
 *

 */
public class JsonDeserializerImpl implements JsonDeserializer {

    @Override
    public List<GameImpl> deserialize(final String str) throws JsonProcessingException {
            return getMapper().readValue(str, mapper.getTypeFactory().constructCollectionType(List.class, GameImpl.class));
    }
}
