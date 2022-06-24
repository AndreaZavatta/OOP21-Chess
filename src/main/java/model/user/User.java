package model.user;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * 
 * user interface for UserImpl.
 *
 */
@JsonDeserialize(as = UserImpl.class)
public interface User {
    /**
     * Getter for the name.
     * @return the user name
     */
    String getName();

    /**
     * set user as winner.
     */
    void haveWon();

    /**
     * Getter for isWinner.
     * @return if the user won the game
     */
    boolean isGameWinner();
}
