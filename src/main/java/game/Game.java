package game;

import java.util.List;
import java.util.Optional;

import pair.Pair;
import piece.utils.Position;
import piece.utils.Side;
import pieces.Piece;
import user.User;

/**
 * 
 * interface for GameImpl.
 *
 */
public interface Game {

    /**
     * 
     * @param firstPos
     * @param finalPos
     */
    void nextMove(Position firstPos, Position finalPos);

    /**
     * 
     * @return the winner one
     */
    Optional<Pair<User, Side>> getWinner();

    /**
     * 
     * @return if the game is finished
     */
    boolean isGameFinished();

    /**
     * 
     * @return list of pieces
     */
    List<Piece> getPiecesList();
}
