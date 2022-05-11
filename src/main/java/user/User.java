package user;

import piece.utils.Color;

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
    Color getColor();

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
