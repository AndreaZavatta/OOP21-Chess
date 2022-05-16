package user;

import piece.utils.Side;

/**
 * 
 * user interface for UserImpl.
 *
 */
public interface User {
    /**
     * 
     * @return the user name
     */
    String getName();

    /**
     * 
     * @return the color of user pieces
     */
    Side getColor();

    /**
     * set user as winner.
     */
    void haveWon();

    /**
     * 
     * @return if the user won the game
     */
    boolean isGameWinner();
}
