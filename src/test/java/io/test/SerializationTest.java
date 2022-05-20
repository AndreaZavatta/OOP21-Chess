package io.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import game.Game;
import game.GameImpl;
import io.JsonSerializer;
import io.JsonSerializerImpl;
import pair.Pair;

import static piece.utils.Side.BLACK;
import static piece.utils.Side.WHITE;
import user.User;
import user.UserImpl;

class SerializationTest {
    private Game game;
    private User user1;
    private User user2;
    private JsonSerializer serializer = null;
    void init() {
        user1 = new UserImpl("Andrea");
        user2 = new UserImpl("Marco");
        game = new GameImpl(new Pair<>(user1, BLACK), new Pair<>(user2, WHITE));
        serializer = new JsonSerializerImpl<GameImpl>(GameImpl.class);
    }
    @Test
    void test() {
        //TODO
        init();
        System.out.println(serializer.serialize(game));
    }

}
