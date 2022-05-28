package io.test;
import board.Chessboard;
import board.ChessboardFactory;
import board.ChessboardFactoryImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import game.Game;
import game.GameImpl;
import io.JsonSerializer;
import io.JsonSerializerImpl;
import pair.Pair;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static piece.utils.Side.BLACK;
import static piece.utils.Side.WHITE;

import piece.utils.Name;
import piece.utils.Position;
import piece.utils.Side;
import pieces.*;
import user.User;
import user.UserImpl;

import java.io.IOException;

class SerializationTest {
    PieceFactory fact = new PieceFactoryImpl();

    ChessboardFactory ChessboardFact = new ChessboardFactoryImpl();
    static ObjectMapper map = new ObjectMapper();

    @BeforeAll
    static void init(){
        map.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        map.registerModule(new Jdk8Module());
    }
    @Test
    void test() {
        //TODO
        final User user1 = new UserImpl("Andrea");
        final User user2 = new UserImpl("Marco");
        final Game game = new GameImpl(new Pair<>(user1, BLACK), new Pair<>(user2, WHITE));
        final JsonSerializer serializer = new JsonSerializerImpl<>(GameImpl.class);
        System.out.println(serializer.serialize(game));
    }
    @Test
    void serializeRook() throws IOException {
        final Rook rook = (Rook) fact.createPiece(Name.ROOK, Position.createNumericPosition(3, 4), BLACK);
        var json = map.writeValueAsString(rook);
        System.out.println(json);

    }
    @Test
    void serializeRookWithInterface() throws JsonProcessingException {
        final Piece rook = fact.createPiece(Name.ROOK, Position.createNumericPosition(3, 4), BLACK);
        System.out.println(map.writeValueAsString(rook));
    }
    @Test
    void serializeChessboard() throws JsonProcessingException {
        final Chessboard chessboard = ChessboardFact.createNormalCB();
        System.out.println(map.writeValueAsString(chessboard));
    }

    @Test
    void serializeGame() throws JsonProcessingException {
        final Game gameImpl = new GameImpl(new Pair<>(new UserImpl("andrea"), BLACK), new Pair<>(new UserImpl("marco"), WHITE));
        System.out.println(map.writeValueAsString(gameImpl));
    }


    @Test
    void deserializeRook() throws IOException {
        final Piece rook = fact.createPiece(Name.ROOK, Position.createNumericPosition(3, 4), BLACK);
        var json = map.writeValueAsString(rook);
        System.out.println(json);
        Rook rook2 = map.readValue(json, Rook.class);
        assertEquals(rook, rook2);
    }

    @Test
    void deserializeAbstractClass() throws JsonProcessingException {
        final AbstractPiece queen = (AbstractPiece) fact.createPiece(Name.QUEEN, Position.createNumericPosition(3, 4), BLACK);
        var json = map.writeValueAsString(queen);
        System.out.println(json);
        Queen queen2 = map.readValue(json, Queen.class);
        assertEquals(queen, queen2);
    }
    @Test
    void deserializeChessboard() throws IOException {
        final Chessboard chessboard = ChessboardFact.createNormalCB();
        var json = map.writeValueAsString(chessboard);
        System.out.println(json);
        final Chessboard chessboard2 = map.readValue(json, chessboard.getClass());
    }
    @Test
    void deserializeGame() throws JsonProcessingException {
        User user = new UserImpl("andrea");
        User user2 = new UserImpl("giacomo");
        final Game game = new GameImpl(new Pair<>(user, WHITE), new Pair<>(user2, BLACK));
        var json = map.writeValueAsString(game);
        System.out.println(json);
        final Game game2 = map.readValue(json, GameImpl.class);
    }



}
