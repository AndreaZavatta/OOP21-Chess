package io;


import com.fasterxml.jackson.core.JsonProcessingException;
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
        return str.isEmpty() ? new ArrayList<>() : deserializeGames(str);
    }

    private List<Game> deserializeGames(String str) throws JsonProcessingException {
        return getMapper().readValue(str, mapper.getTypeFactory().constructCollectionType(List.class, GameImpl.class));
    }


}
