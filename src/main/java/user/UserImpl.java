package user;

import piece.utils.Side;

/**
 * 
 * contain information about user during the game.
 *
 */
public class UserImpl implements User {
    private final String name;
    private final Side userColor;
    private boolean isWinner;

    /**
     * 
     * @param name
     * @param userColor
     */
    public UserImpl(final String name, final Side userColor) {
        super();
        this.name = name;
        this.userColor = userColor;
        this.isWinner = false;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Side getColor() {
        return this.userColor;
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
        return "UserImpl [name=" + name + ", userColor=" + userColor + "]";
    }
}
