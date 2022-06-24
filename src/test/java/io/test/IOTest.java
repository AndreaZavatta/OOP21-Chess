package io.test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static model.piece.utils.Side.BLACK;
import static model.piece.utils.Side.WHITE;
import static org.junit.jupiter.api.Assertions.fail;
import model.board.Chessboard;
import model.board.ChessboardFactory;
import model.board.ChessboardFactoryImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.user.User;
import model.user.UserImpl;
import model.io.JsonDeserializer;
import model.io.JsonDeserializerImpl;
import model.io.JsonFileReader;
import model.io.JsonFileReaderImpl;
import model.io.JsonFileWriter;
import model.io.JsonFileWriterImpl;
import model.io.JsonSerializer;
import model.io.JsonSerializerImpl;
import model.io.JsonUtils;
import org.junit.jupiter.api.Test;
import model.game.Game;
import model.game.GameImpl;
import model.tuple.Pair;
import model.piece.utils.Name;
import model.piece.utils.Position;
import model.pieces.Piece;
import model.pieces.PieceFactory;
import model.pieces.PieceFactoryImpl;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

class IOTest {
    private final String fs = System.getProperty("file.separator");
    private final String cd =  System.getProperty("user.home");
    private final PieceFactory fact = new PieceFactoryImpl();
    private final JsonSerializer js = new JsonSerializerImpl();
    private final ChessboardFactory chessboardFact = new ChessboardFactoryImpl();
    private final ObjectMapper map = JsonUtils.getMapper();
    private final JsonFileWriter fw = new JsonFileWriterImpl("prova.txt");
    @Test
    void testDeserializationPiece() {
        try {
            final Piece rook = fact.createPiece(Name.ROOK, Position.createNumericPosition(3, 4), BLACK);
            final Piece rook2 = map.readValue(map.writeValueAsString(rook), Piece.class);
            assertEquals(rook, rook2);
        } catch (JsonProcessingException ex) {
            fail();
        }
    }

    @Test
    void testSerializationPiece() {
        try {
            final Piece rook = fact.createPiece(Name.ROOK, Position.createNumericPosition(3, 4), BLACK);
            final String str = map.writeValueAsString(rook);
            final Piece rook2 = map.readValue(str, Piece.class);
            assertEquals(rook, rook2);
        } catch (JsonProcessingException ex) {
            fail();
        }
    }
    @Test
    void testDate() {
        try {
            final LocalDate date = LocalDate.of(2001, 3, 31);
            final String str = map.writeValueAsString(date);
            final LocalDate date2 = map.readValue(str, LocalDate.class);
            assertEquals(date, date2);
        } catch (JsonProcessingException e) {
            fail();
        }
    }

    @Test
    void testDeserializationChessboard() {
        try {
            final Chessboard chessboard = chessboardFact.createNormalCB();
            final String str = map.writeValueAsString(chessboard);
            final Chessboard chessboard2 = map.readValue(str, Chessboard.class);
            assertEquals(chessboard, chessboard2);
        } catch (JsonProcessingException ex) {
            fail();
        }
    }
    @Test
    void testSerializationChessboard() {
        try {
            final Chessboard chessboard = chessboardFact.createNormalCB();
            final String str = map.writeValueAsString(chessboard);
            final Chessboard chessboard2 = map.readValue(str, Chessboard.class);
            assertEquals(chessboard, chessboard2);
        } catch (IOException ex) {
            fail();
        }

    }

    @Test
    void testDeserializationGame() {
        try {
            final Game game = new GameImpl(new Pair<>(new UserImpl("Andrea"), BLACK), new Pair<>(new UserImpl("marco"), WHITE));
            final String str = map.writeValueAsString(game);
            final Game game2 = map.readValue(str, Game.class);
            assertEquals(game.getPiecesList(), game2.getPiecesList());
        } catch (JsonProcessingException ex) {
            fail();
        }
    }

    @Test
    void testSerializationGame() {
        try {
            final User user = new UserImpl("andrea");
            final User user2 = new UserImpl("giacomo");
            final Game game = new GameImpl(new Pair<>(user, WHITE), new Pair<>(user2, BLACK));
            final String json = map.writeValueAsString(game);
            final Game game2 = map.readValue(json, GameImpl.class);
            final String json2 = map.writeValueAsString(game2);
            assertEquals(json, json2);
        } catch (IOException ex) {
            fail();
        }
    }

    @Test
    void testDeserializer() {
        try {
            final JsonDeserializer jsonDeserializer = new JsonDeserializerImpl();
            final List<Game> game = List.of(getGame("andrea", "giacomo"));
            final String json = js.serialize(game);
            final List<Game> game2 = jsonDeserializer.deserialize(json);
            assertEquals(game.get(0).getPiecesList(), game2.get(0).getPiecesList());
        } catch (IOException ex) {
            fail();
        }
    }

    private Game getGame(final String firstName, final String secondName) {
        final User user1 = new UserImpl(firstName);
        final User user2 = new UserImpl(secondName);
        return new GameImpl(new Pair<>(user1, WHITE), new Pair<>(user2, BLACK));
    }
    private List<Game> getGames() {
        return List.of(getGame("andrea", "giacomo"), getGame("stefano", "giorgio"));
    }


    @Test
    void testReadListFile() {
        try {
            final List<Game> listGame = getGames();
            fw.writeFile(listGame);
            final JsonFileReader fr = new JsonFileReaderImpl("prova.txt");
            final List<Game> listGame2 = fr.readFile();
            final List<List<Piece>> listPiece = listGame.stream().map(Game::getPiecesList).collect(Collectors.toList());
            final List<List<Piece>> listPiece2 = listGame2.stream().map(Game::getPiecesList).collect(Collectors.toList());
            assertEquals(listPiece, listPiece2);
            if (!new File(cd + fs + "LAMAChess" + fs + "prova.txt").delete()) {
                fail();
            }
        } catch (IOException e) {
            fail();
        }
    }
}
