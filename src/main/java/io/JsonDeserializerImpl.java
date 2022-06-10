package io;


import game.Game;
import game.GameImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static io.JsonUtils.getMapper;
import static io.vertx.core.json.Json.mapper;

/**
 * 
 *{@inheritDoc}.
 */
public class JsonDeserializerImpl implements JsonDeserializer {

    @Override
    public List<Game> deserialize(final String str) throws IOException {
        if (str.isEmpty()) {
            return new ArrayList<>();
        }
        return getMapper().readValue(str, mapper.getTypeFactory().constructCollectionType(List.class, GameImpl.class));
    }


}
