package user;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 
 * contain information about user during the game.
 *
 */
public class UserImpl implements User, Serializable {

    private static final long serialVersionUID = 5552078593645290172L;

    private final String name;
    @JsonProperty("gameWinner")
    private boolean isWinner;

    /**
     * 
     * @param name
     */
    public UserImpl(final String name) {
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
        return "UserImpl [name=" + name + "]";
    }
}
