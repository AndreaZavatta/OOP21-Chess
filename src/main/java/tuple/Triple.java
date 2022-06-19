package tuple;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
/**
 * 
 * A generic Triple.
 *
 * @param <T>
 * @param <U>
 * @param <V>
 */
public class Triple<T, U, V> {

    private final ObjectProperty<T> x;
    private final ObjectProperty<U> y;
    private final ObjectProperty<V> z;

    /**
     * 
     * @param first
     * @param second
     * @param third
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
     * @return An ObjectProperty<T>
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
