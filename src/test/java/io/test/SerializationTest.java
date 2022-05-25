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
    @Test
    void test() {
        //TODO
        final User user1 = new UserImpl("Andrea");
        final User user2 = new UserImpl("Marco");
        final Game game = new GameImpl(new Pair<>(user1, BLACK), new Pair<>(user2, WHITE));
        final JsonSerializer serializer = new JsonSerializerImpl<>(GameImpl.class);
        System.out.println(serializer.serialize(game));
    }

}
