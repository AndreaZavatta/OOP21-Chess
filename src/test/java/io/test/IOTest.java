package io.test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static model.piece.utils.Side.BLACK;
import static model.piece.utils.Side.WHITE;
import static org.junit.jupiter.api.Assertions.fail;
import board.Chessboard;
import board.ChessboardFactory;
import board.ChessboardFactoryImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.*;
import model.pieces.*;
import org.junit.jupiter.api.Test;
import game.Game;
import game.GameImpl;
import pair.Pair;
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
    ChessboardFactory chessboardFact = new ChessboardFactoryImpl();
    ObjectMapper map = JsonUtils.getMapper();
    JsonFileWriter fw = new JsonFileWriterImpl("database.txt");
    @Test
    void serializeRook() throws IOException {
        final Rook rook = (Rook) fact.createPiece(Name.ROOK, Position.createNumericPosition(3, 4), BLACK);
        final String json = map.writeValueAsString(rook);
        System.out.println(json);

    }
    @Test
    void serializeRookWithInterface() throws JsonProcessingException {
        final Piece rook = fact.createPiece(Name.ROOK, Position.createNumericPosition(3, 4), BLACK);
        System.out.println(map.writeValueAsString(rook));
    }
    @Test
    void serializeChessboard() throws JsonProcessingException {
        final Chessboard chessboard = chessboardFact.createNormalCB();
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
        final String json = map.writeValueAsString(rook);
        final Piece rook2 = map.readValue(json, rook.getClass());
        assertEquals(rook, rook2);
    }

    @Test
    void deserializeAbstractClass() throws JsonProcessingException {
        final AbstractPiece queen = (AbstractPiece) fact.createPiece(Name.QUEEN, Position.createNumericPosition(3, 4), BLACK);
        final String json = map.writeValueAsString(queen);
        System.out.println(json);
        final Piece queen2 = map.readValue(json, queen.getClass());
        assertEquals(queen, queen2);
    }
    @Test
    void deserializeChessboard() throws IOException {
        final Chessboard chessboard = chessboardFact.createNormalCB();
        final String json = map.writeValueAsString(chessboard);
        final Chessboard chessboard2 = map.readValue(json, chessboard.getClass());
        assertEquals(chessboard, chessboard2);
    }
    @Test
    void deserializeGame() throws JsonProcessingException {
        final User user = new UserImpl("andrea");
        final User user2 = new UserImpl("giacomo");
        final Game game = new GameImpl(new Pair<>(user, WHITE), new Pair<>(user2, BLACK));
        final String json = map.writeValueAsString(game);
        final Game game2 = map.readValue(json, GameImpl.class);
        final String json2 = map.writeValueAsString(game2);
        assertEquals(json, json2);
    }
    @Test
    void testSerializer() throws JsonProcessingException{
        final String json = js.serialize(getGame("andrea", "giacomo"));
        System.out.println(json);
    }
    @Test
    void testDeserializer() throws JsonProcessingException {
        final JsonDeserializer jsonDeserializer = new JsonDeserializerImpl(GameImpl.class);
        final Game game = getGame("andrea", "giacomo");
        final String json = js.serialize(game);
        final Game game2 = (GameImpl) jsonDeserializer.deserialize(json);
        assertEquals(game.getPiecesList(), game2.getPiecesList());
    }

    private Game getGame(final String firstName, final String secondName){
        final User user1 = new UserImpl(firstName);
        final User user2 = new UserImpl(secondName);
        return new GameImpl(new Pair<>(user1, WHITE), new Pair<>(user2, BLACK));
    }
    private List<Game> getGames() {
        return List.of(getGame("andrea", "giacomo"), getGame("stefano", "giorgio"));
    }


    @Test
    void testFileReaderObj() {
        try {
            final Game game = getGame("andrea", "giacomo");
            fw.writeFile(game);
            final JsonFileReader fr = new JsonFileReaderImpl("database.txt", GameImpl.class);
            final Game game2 = (Game) fr.readFile();
            assertEquals(game.getPiecesList(), game2.getPiecesList());
        }catch(IOException ignored){
            fail();
        }
    }

    @Test
    void testFileReaderList() {
        try {
            final List<Game> list = getGames();
            fw.writeFile(list);
            final JsonFileReader fr = new JsonFileReaderImpl("database.txt", ArrayList.class);
            final List<?> games = (List<?>) fr.readFile();
            assertEquals(js.serialize(games), js.serialize(list));
        }catch (IOException ignored){
            fail();
        }
    }





}
