package pair;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Triple<T, U, V> {

    private final ObjectProperty<T> x;
    private final ObjectProperty<U> y;
    private final ObjectProperty<V> z;

    public Triple(T first, U second, V third) {
        this.x = new SimpleObjectProperty<>(first);
        this.y = new SimpleObjectProperty<>(second);
        this.z = new SimpleObjectProperty<>(third);
    }

    public T getFirst() { return x.get(); }
    public U getSecond() { return y.get(); }
    public V getThird() { return z.get(); }

    public ObjectProperty<T> xProperty(){
        return x;
    }
    public ObjectProperty<U> yProperty(){
        return y;
    }
    public ObjectProperty<V> zProperty(){
        return z;
    }
    @Override
    public String toString(){
        return x.get() + " " + y.get() + " " + z.get();
    }
}
