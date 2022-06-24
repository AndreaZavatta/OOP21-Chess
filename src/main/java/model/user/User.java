package model.user;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * 
 * User interface for UserImpl.
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
     * Set user as winner.
     */
    void haveWon();

    /**
     * Getter for isWinner.
     * @return if the user won the game
     */
    boolean isGameWinner();
}
