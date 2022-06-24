package model.io;


import com.fasterxml.jackson.core.JsonProcessingException;
import model.game.Game;
import model.game.GameImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static model.io.JsonUtils.getMapper;
import static io.vertx.core.json.Json.mapper;


/**
 * An implementation of deserializer, this is used to deserialize a list of game.
 */
public class JsonDeserializerImpl implements JsonDeserializer {

    @Override
    public List<Game> deserialize(final String str) throws IOException {
        return str.isEmpty() ? new ArrayList<>() : deserializeGames(str);
    }

    private List<Game> deserializeGames(final String str) throws JsonProcessingException {
        return getMapper().readValue(str, mapper.getTypeFactory().constructCollectionType(List.class, GameImpl.class));
    }


}
