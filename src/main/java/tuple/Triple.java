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
     * 
     * @return T
     */
    public T getFirst() {
        return x.get();
    }
    /**
     * 
     * @return U
     */
    public U getSecond() {
        return y.get();
    }
    /**
     * 
     * @return V
     */
    public V getThird() {
        return z.get(); 
    }

    /**
     * this method is used in the controller for recognize data.
     * @return ObjectProperty that represent x element
     */
    public ObjectProperty<T> xProperty() {
        return x;
    }
    /**
     * this method is used in the controller for recognize data.
     * @return ObjectProperty that represent y element
     */
    public ObjectProperty<U> yProperty() {
        return y;
    }
    /**
     * this method is used in the controller for recognize data.
     * @return ObjectProperty that represent z element
     */
    public ObjectProperty<V> zProperty() {
        return z;
    }
    @Override
    public String toString() {
        return x.get() + " " + y.get() + " " + z.get();
    }
}
