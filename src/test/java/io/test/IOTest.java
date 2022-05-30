package io.test;
import board.Chessboard;
import board.ChessboardFactory;
import board.ChessboardFactoryImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import io.*;
import model.pieces.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import game.Game;
import game.GameImpl;
import pair.Pair;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static model.piece.utils.Side.BLACK;
import static model.piece.utils.Side.WHITE;
import static org.junit.jupiter.api.Assertions.fail;

import model.piece.utils.Name;
import model.piece.utils.Position;
import user.User;
import user.UserImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class IOTest {
    PieceFactory fact = new PieceFactoryImpl();
    JsonSerializer js = new JsonSerializerImpl();
    ChessboardFactory ChessboardFact = new ChessboardFactoryImpl();
    static ObjectMapper map = new ObjectMapper();

    @BeforeAll
    static void init(){
        map.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false).registerModule(new Jdk8Module())
                .registerModule(new ParameterNamesModule());
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
        Piece rook2 = map.readValue(json, rook.getClass());
        assertEquals(rook, rook2);
    }

    @Test
    void deserializeAbstractClass() throws JsonProcessingException {
        final AbstractPiece queen = (AbstractPiece) fact.createPiece(Name.QUEEN, Position.createNumericPosition(3, 4), BLACK);
        var json = map.writeValueAsString(queen);
        System.out.println(json);
        Piece queen2 = map.readValue(json, queen.getClass());
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
        var json2 = map.writeValueAsString(game2);
        System.out.println(json2);
    }
    @Test
    void testSerializer() throws JsonProcessingException{
        JsonSerializer jSerializer = new JsonSerializerImpl();
        User user = new UserImpl("andrea");
        User user2 = new UserImpl("giacomo");
        final Game game = new GameImpl(new Pair<>(user, WHITE), new Pair<>(user2, BLACK));
        var json = jSerializer.serialize(game);
        System.out.println(json);
    }
    @Test
    void testDeserializer() throws JsonProcessingException {
        JsonSerializer jSerializer = new JsonSerializerImpl();
        JsonDeserializer<GameImpl> jsonDeserializer = new JsonDeserializerImpl<GameImpl>(GameImpl.class);
        User user = new UserImpl("andrea");
        User user2 = new UserImpl("giacomo");
        final Game game = new GameImpl(new Pair<>(user, WHITE), new Pair<>(user2, BLACK));
        var json = jSerializer.serialize(game);
        var game2 = jsonDeserializer.deserialize(json);
        System.out.println(game);
        System.out.println(game2);
    }
    void writeGameSupport() throws IOException {
        User user = new UserImpl("andrea");
        User user2 = new UserImpl("giacomo");
        final Game game = new GameImpl(new Pair<>(user, WHITE), new Pair<>(user2, BLACK));
        JsonFileWriter<Game> fw = new JsonFileWriterImpl<>("database.txt", GameImpl.class);
        fw.writeFile(game);
    }

    void writeListSupport() throws IOException {

    }

    private List<Game> getGames() {
        User user1 = new UserImpl("andrea");
        User user2 = new UserImpl("giacomo");
        final Game game = new GameImpl(new Pair<>(user1, WHITE), new Pair<>(user2, BLACK));

        User user3 = new UserImpl("stefano");
        User user4 = new UserImpl("giorgio");
        final Game game2 = new GameImpl(new Pair<>(user3, WHITE), new Pair<>(user4, BLACK));
        return List.of(game, game2);
    }


    @Test
    void testFileReaderObj() {
        try {
            JsonFileReader<Game> fr = new JsonFileReaderImpl<>("database.txt", GameImpl.class);
            Game game = (Game) fr.readFile();
            System.out.println(game);
        }catch(IOException ignored){
            fail();
        }
    }

    @Test
    void testFileReaderList() {
        try {
            List<Game> list = getGames();
            JsonFileWriter<GameImpl> fw = new JsonFileWriterImpl<GameImpl>("database.txt", GameImpl.class);
            fw.writeFile(list);
            JsonFileReader<Game> fr = new JsonFileReaderImpl<>("database.txt", ArrayList.class);
            List<?> games = (List<?>) fr.readFile();
            assertEquals(js.serialize(games), js.serialize(list));
        }catch (IOException ignored){
            fail();
        }
    }





}
