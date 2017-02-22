package ass.poliape2;

import java.util.Collection;
import java.util.Iterator;

/**
 * Created by petrp on 02/22/17.
 */
public interface CustomList<T> {
    boolean append(T value);

    boolean prepend(T value);

    T removeFront();

    T removeBack();

    int appendAll(Collection<? extends T> collection);

    int takeAll(Collection<? super T> collection);

    int count();

    Iterator<T> forwardIterator();

    Iterator<T> backwardIterator();
}
