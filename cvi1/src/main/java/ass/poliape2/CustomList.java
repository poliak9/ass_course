package ass.poliape2;

import java.util.Collection;
import java.util.Iterator;

/**
 * Created by petrp on 02/22/17.
 */
public interface CustomList<T> {
    void append(T value);

    void prepend(T value);

    T removeFront();

    T removeBack();

    int appendAll(Collection<? extends T> collection);

    int takeAll(Collection<? super T> collection);

    static int count(Collection<?> collection) {
        return collection.size();
    }

    Iterator<T> forwardIterator();

    Iterator<T> backwardIterator();
}
