package game;

import java.util.Optional;

import board.Chessboard;
import board.ChessboardFactoryImpl;
import board.EndGame;
import board.EndGameImpl;
import pair.Pair;
import piece.utils.Position;
import piece.utils.Side;
import user.User;

/**
 * 
 * The main class of the game,
 * it manage a match user vs user.
 *
 */
public class GameImpl implements Game {
    private final Pair<User, Side> player1;
    private final Pair<User, Side> player2;
    private boolean isFinished;
    private final Chessboard chessboard;
    private final EndGame controller;
    private final Turn turnManager;

    /**
     * 
     * @param player1
     * @param player2
     */
    public GameImpl(final Pair<User, Side> player1, final Pair<User, Side> player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.isFinished = false; 
        this.chessboard = new ChessboardFactoryImpl().createNormalCB();
        this.controller = new EndGameImpl();
        this.turnManager = new TurnImpl();
    }

    @Override
    public void nextMove(final Position firstPos, final Position finalPos) {
        // TODO Auto-generated method stub

    }

    @Override
    public Optional<User> getWinner() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isGameFinished() {
        return this.isFinished;
    }

}
