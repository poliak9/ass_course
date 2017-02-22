package ass.poliape2;

import java.util.Collection;
import java.util.Iterator;
import java.util.function.Consumer;

public class DoubleLinkedList<T> implements CustomList<T> {
    private Node first;
    private Node last;
    private int count;

    public boolean append(T value) {
        return false;
    }

    public boolean prepend(T value) {
        return false;
    }

    public T removeFront() {
        if (first == null) return null;
        T res = first.getValue();
        if (first.getNext() != null) {
            first.getNext().setPrevious(null);
        }
        first = first.getNext();
        return res;

    }

    public T removeBack() {
        if (last == null) return null;
        T res = last.getValue();
        if (last.getPrevious() != null) {
            last.getPrevious().setNext(null);
        }
        last = last.getPrevious();
        return res;
    }

    public int takeAll(Collection<? super T> collection) {
        Node current = first;
        int given = 0;
        while (current != null) {
            collection.add(current.getValue());
            given++;
        }
        return given;
    }


    public int appendAll(Collection<? extends T> collection) {
        int count = 0;
        for (T item : collection) {
            append(item);
            count++;
        }
        return count;
    }

    public int count() {
        return count;
    }

    public Iterator<T> forwardIterator() {
        return new CustomIterator(first) {
            @Override
            public T next() {
                current = next;
                next = next.getNext();
                return current.getValue();
            }
        };
    }

    public Iterator<T> backwardIterator() {
        return new CustomIterator(last) {
            public T next() {
                current = next;
                next = next.getPrevious();
                return current.getValue();
            }
        };
    }

    private class Node {
        final T value;
        Node previous;
        Node next;

        public Node(T value) {
            this.value = value;
        }

        public T getValue() {
            return value;
        }

        public Node getPrevious() {
            return previous;
        }

        public void setPrevious(Node previous) {
            this.previous = previous;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    private abstract class CustomIterator implements Iterator<T> {
        Node current = null;
        Node next;

        CustomIterator(Node start) {
            next = start;
        }

        @Override
        public void remove() {
            if (current == null) {
                throw new IllegalStateException();
            }
            throw new UnsupportedOperationException();
        }

        @Override
        public void forEachRemaining(Consumer<? super T> action) {
            while (hasNext()) {
                action.accept(next());
            }
        }

        @Override
        public boolean hasNext() {
            return false;
        }
    }
}
