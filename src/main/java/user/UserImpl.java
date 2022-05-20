package user;

/**
 * 
 * contain information about user during the game.
 *
 */
public class UserImpl implements User {
    private final String name;
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
