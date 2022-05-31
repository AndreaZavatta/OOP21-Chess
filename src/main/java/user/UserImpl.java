package user;

import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * 
 * contain information about user during the game.
 *
 */
public class UserImpl implements User {

    private static final long serialVersionUID = 5552078593645290172L;

    private final String name;
    @JsonProperty("gameWinner")
    private boolean isWinner;

    /**
     *
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
        return "UserImpl [name=" + name + "]";
    }
}
