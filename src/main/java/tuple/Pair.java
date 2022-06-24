package tuple;

import java.io.Serializable;

/**
 * 
 * A generic Pair.
 *
 * @param <X> generic first parameter
 * @param <Y> generic second parameter
 */
public class Pair<X, Y> implements Serializable {

    private static final long serialVersionUID = 8065526709210920611L;

    /**
     * first parameter.
     */
    private final X x;
    /**
     * second parameter.
     */
    private final Y y;

    /**
     * Simple constructor.
     * @param x first parameter
     * @param y second parameter
     */
    public Pair(final X x, final Y y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Getter for X.
     * @return X
     */
    public X getX() {
        return this.x;
    }

    /**
     * Getter for Y.
     * @return Y
     */
    public Y getY() {
        return this.y;
    }

    @Override
    public String toString() {
        return "Pair [X=" + x + ", Y=" + y + "]";
    }
}
