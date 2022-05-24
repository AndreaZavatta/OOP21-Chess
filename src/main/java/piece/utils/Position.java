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
     * @param x the x value.
     * @param y the y value.
     */
    public Position(final int x, final int y) {
        super();
        this.x = x;
        this.y = y;
    }
//    /**
//     * 
//     * @param x
//     * @param y
//     * @return a
//     */
//    public static Position createNewPosition(final String position) {
//        return new Position(x, y);
//    }
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
     * @return the letter corresponding to the position.
     */
    public char getCharX() {
        return this.convertFromNumberToLetter();
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
        return convertFromNumberToLetter() + String.valueOf(8 - y);
    }

    private char convertFromNumberToLetter() {
        final char a = 'a';
        return (char) (a + x);
    }
    /**
     * 
     * @param position
     * @return new position
     */
    public static Position createNewPosition(final String position) {
        return new Position(fromCharToInt(position.charAt(0)), -Character.getNumericValue(position.charAt(1)) + 8);
    }

    private static int fromCharToInt(final char firstLetter) {
        return (int) firstLetter - (int) 'a';
    }
}
