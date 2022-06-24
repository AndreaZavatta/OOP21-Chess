package model.tuple;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
/**
 * 
 * A generic Triple.
 *
 * @param <T> generic first parameter
 * @param <U> generic second parameter
 * @param <V> generic third parameter
 */
public class Triple<T, U, V> {
    /**
     * The ObjectProperty that represent the generic first parameter.
     */
    private final ObjectProperty<T> x;
    /**
     * The ObjectProperty that represent the generic second parameter.
     */
    private final ObjectProperty<U> y;
    /**
     * The ObjectProperty that represent the generic third parameter.
     */
    private final ObjectProperty<V> z;

    /**
     * 
     * @param first generic parameter 
     * @param second generic parameter
     * @param third generic parameter
     */
    public Triple(final T first, final U second, final V third) {
        this.x = new SimpleObjectProperty<>(first);
        this.y = new SimpleObjectProperty<>(second);
        this.z = new SimpleObjectProperty<>(third);
    }


    /**
     * Get the first element of the Triple.
     *
     * @return The first element of the list.
     */
    public T getFirst() {
        return x.get();
    }

    /**
     * Returns the second value of the Triple.
     *
     * @return The value of the second element of the pair.
     */
    public U getSecond() {
        return y.get();
    }

    /**
     * Get the third element of the Triple.
     *
     * @return The value of the third element of the tuple.
     */
    public V getThird() {
        return z.get(); 
    }


    /**
     * Returns the property used to represent the x of this Triple.
     *
     * @return An ObjectProperty of T
     */
    public ObjectProperty<T> xProperty() {
        return x;
    }

    /**
     * Returns the property used to represent the y of this Triple.
     *
     * @return A property that represents the y of this Triple.
     */
    public ObjectProperty<U> yProperty() {
        return y;
    }

    /**
     * Returns the property used to represent the z of this Triple.
     *
     * @return A property that represents the z of this triple.
     */
    public ObjectProperty<V> zProperty() {
        return z;
    }
    @Override
    public String toString() {
        return x.get() + " " + y.get() + " " + z.get();
    }
}
