package user;

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
     * set user as winner.
     */
    void haveWon();

    /**
     * 
     * @return if the user won the game
     */
    boolean isGameWinner();
}
