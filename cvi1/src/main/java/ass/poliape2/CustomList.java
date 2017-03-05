package ass.poliape2;

import java.util.Collection;
import java.util.Iterator;

public interface CustomList<T> extends Iterable<T> {
    void append(T value);

    void prepend(T value);

    T removeFront();

    T removeBack();

    int appendAll(Collection<? extends T> collection);

    int takeAll(Collection<? super T> collection);

    int count();

    static int count(Collection<?> collection) {
        return collection.size();
    }

    Iterator<T> forwardIterator();

    Iterator<T> backwardIterator();
}
