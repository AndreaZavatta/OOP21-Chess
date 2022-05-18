package game;

import java.util.Optional;

import piece.utils.Position;
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
    Optional<User> getWinner();

    /**
     * 
     * @return if the game is finished
     */
    boolean isGameFinished();
}
