package model.user;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * 
 * contain information about user during the game.
 *
 */
public class UserImpl implements User {

    /**
     * 
     * name of user.
     */
    private final String name;
    /**
     * true if is the winner.
     */
    @JsonProperty("gameWinner")
    private boolean isWinner;

    /**
     * @param name name of user
     */

    public UserImpl(@JsonProperty("name") final String name) {
        super();
        this.name = name;
        this.isWinner = false;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void haveWon() {
        this.isWinner = true;
    }

    @Override
    public boolean isGameWinner() {
        return this.isWinner;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserImpl other = (UserImpl) obj;
        return Objects.equals(name, other.name);
    }
}
