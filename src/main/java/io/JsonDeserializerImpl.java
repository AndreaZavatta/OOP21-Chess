package io;


import com.fasterxml.jackson.core.JsonProcessingException;
import game.Game;
import game.GameImpl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static io.JsonUtils.getMapper;
import static io.vertx.core.json.Json.mapper;

/**
 * 
 *{@inheritDoc}.
 */
public class JsonDeserializerImpl implements JsonDeserializer {

    @Override
    public List<Game> deserialize(final String str) throws IOException {
        if(str.isEmpty()){
            return new ArrayList<>();
        }
        System.out.println("convert");
        return getMapper().readValue(str, mapper.getTypeFactory().constructCollectionType(List.class, GameImpl.class));
    }


}
