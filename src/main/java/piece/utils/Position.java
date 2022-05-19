package piece.utils;

import java.util.Objects;

/**
 * 
 * A standard Position class, with getters, hashCode, equals, and toString.

 */
public class Position {

    private final int x;
    private final int y;
    /**
     * 
     * @param x 
     * @param y
     */
    public Position(final int x, final int y) {
        super();
        this.x = x;
        this.y = y;
    }
    /**
     * 
     * @return the x variable.
     */
    public int getX() {
        return x;
    }
    /**
     * 
     * @return the y variable.
     */
    public int getY() {
        return y;
    }
    /**
     * 
     * @return
     */
    public char getCharX() {
        return this.convertFromNumberToLetter(x);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
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
        final Position other = (Position) obj;
        return x == other.x && y == other.y;
    }

    @Override
    public String toString() {
        return convertFromNumberToLetter(x) + String.valueOf(8 - y);
    }

    private char convertFromNumberToLetter(final int x) {
        final char a = 'a';
        return (char) (a + x);
    }
}
