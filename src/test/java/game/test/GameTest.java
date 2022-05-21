package game.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import game.Game;
import game.GameImpl;
import pair.Pair;
import piece.utils.Position;
import piece.utils.Side;
import user.User;
import user.UserImpl;

class GameTest {

    private Game createGame() {
        final Pair<User, Side> player1 = new Pair<>(new UserImpl("Mario"), Side.WHITE);
        final Pair<User, Side> player2 = new Pair<>(new UserImpl("Gigi"), Side.WHITE);
        return new GameImpl(player1, player2);
    }

    @Test
    void startGame() {
        final Game match = createGame();

        assertFalse(match.isGameFinished());
        assertTrue(match.getWinner().isEmpty());
    }

    @Test
    void gameEnded() {
        final Game match = createGame();

        match.nextMove(new Position(6, 6), 
                        new Position(6, 4));
        match.nextMove(new Position(4, 1),
                        new Position(4, 3));
        match.nextMove(new Position(5, 6), 
                        new Position(5, 4));

        assertFalse(match.isGameFinished());

        match.nextMove(new Position(3, 0),
                        new Position(7, 4));

        assertTrue(match.isGameFinished());
        assertEquals(Side.BLACK, match.getWinner().get().getY());
    }
}
