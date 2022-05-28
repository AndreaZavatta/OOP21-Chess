package pair;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 
 * A generic Pair.
 *
 * @param <X>
 * @param <Y>
 */
public class Pair<X extends Serializable, Y extends Serializable> implements Serializable {

    private static final long serialVersionUID = 8065526709210920611L;

    private final X x;
    private final Y y;

    /**
     * 
     * @param x
     * @param y
     */
    public Pair(@JsonProperty("x") final X x,
                @JsonProperty("y") final Y y) {
        this.x = x;
        this.y = y;
    }

    /**
     * 
     * @return X
     */
    public X getX() {
        return this.x;
    }

    /**
     * 
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
