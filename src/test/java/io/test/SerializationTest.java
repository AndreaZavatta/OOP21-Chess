package io.test;
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
    private JsonSerializer serializer = null;
    void init() {
        User user1 = new UserImpl("Andrea");
        User user2 = new UserImpl("Marco");
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
