package game.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import game.Game;
import game.GameImpl;
import tuple.Pair;
import model.piece.utils.Name;
import model.piece.utils.Position;
import model.piece.utils.Side;
import model.pieces.PieceFactory;
import model.pieces.PieceFactoryImpl;
import user.User;
import user.UserImpl;

class GameTest {

    private final PieceFactory pieceFct = new PieceFactoryImpl();

    private Game createGame() {
        final Pair<User, Side> player1 = new Pair<>(new UserImpl("Mario"), Side.WHITE);
        final Pair<User, Side> player2 = new Pair<>(new UserImpl("Gigi"), Side.BLACK);
        return new GameImpl(player1, player2);
    }

    @Test
    void startGame() {
        final Game match = createGame();

        assertFalse(match.isGameFinished());
        assertTrue(match.getWinner().isEmpty());
    }

    @Test
    void gameEnded() throws IOException {
        final Game match = createGame();

        match.nextMove(Position.createNewPosition("g2"), 
                    Position.createNewPosition("g4"));
        match.nextMove(Position.createNewPosition("e7"),
                    Position.createNewPosition("e5"));
        match.nextMove(Position.createNewPosition("f2"), 
                    Position.createNewPosition("f4"));

        assertFalse(match.isGameFinished());

        match.nextMove(Position.createNewPosition("d8"),
                    Position.createNewPosition("h4"));

        assertTrue(match.isGameFinished());
        assertTrue(match.getWinner().isPresent());
        assertEquals(Side.BLACK, match.getWinner().get().getY());
    }

    @Test
    void emptyPosition() {
        final Game match = createGame();

        assertThrows(IllegalArgumentException.class,
                () -> match.nextMove(Position.createNewPosition("e4"), 
                        Position.createNewPosition("e5")));
    }

    @Test
    void enemyPieceSelected() {
        final Game match = createGame();

        assertThrows(IllegalArgumentException.class,
                () -> match.nextMove(Position.createNewPosition("b7"), 
                        Position.createNewPosition("b6")));
    }

    @Test
    void notValidPositionSelected() {
        final Game match = createGame();

        assertThrows(IllegalArgumentException.class,
                () -> match.nextMove(Position.createNewPosition("b7"), 
                        Position.createNewPosition("b4")));
    }

    @Test
    void draw() {
        //TODO
    }

    @Test
    void promotion() throws IOException {
        final Game match = createGame();

        match.nextMove(Position.createNewPosition("b2"), 
                Position.createNewPosition("b4"));
        match.nextMove(Position.createNewPosition("d7"),
                    Position.createNewPosition("d5"));
        match.nextMove(Position.createNewPosition("b4"), 
                    Position.createNewPosition("b5"));
        match.nextMove(Position.createNewPosition("d5"), 
                Position.createNewPosition("d4"));
        match.nextMove(Position.createNewPosition("b5"), 
                Position.createNewPosition("b6"));
        match.nextMove(Position.createNewPosition("d4"), 
                Position.createNewPosition("d3"));
        match.nextMove(Position.createNewPosition("b6"), 
                Position.createNewPosition("a7"));
        match.nextMove(Position.createNewPosition("d3"), 
                Position.createNewPosition("c2"));
        match.nextMove(Position.createNewPosition("a7"), 
                Position.createNewPosition("b8"));

        match.promotion(Name.QUEEN);
        assertEquals(pieceFct.createPiece(Name.QUEEN, Position.createNewPosition("b8"), Side.WHITE), 
                    match.getPiecesList().stream()
                                .filter(x -> x.getPosition().equals(Position.createNewPosition("b8")))
                                .findFirst()
                                .get());
    }
}
