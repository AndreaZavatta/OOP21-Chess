package game;

import java.util.Optional;

import board.Chessboard;
import board.ChessboardFactory;
import board.ChessboardFactoryImpl;
import board.EndGame;
import board.EndGameImpl;
import piece.utils.Position;
import user.User;

/**
 * 
 * The main class of the game,
 * it manage a match user vs user.
 *
 */
public class GameImpl implements Game {
    private User player1;
    private User player2;
    private boolean isFinished;
    private final Chessboard chessboard;
    private final EndGame controller;

    /**
     * 
     * @param player1
     * @param player2
     */
    public GameImpl(final User player1, final User player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.isFinished = false; 
        this.chessboard = new ChessboardFactoryImpl().createNormalCB();
        this.controller = new EndGameImpl(false);
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
        // TODO Auto-generated method stub
        return false;
    }

}
