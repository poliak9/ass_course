package ass.poliape2;

public class ValueHolder<T> {

    private T value;

    public ValueHolder(T value) {
        this.value = value;

    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
