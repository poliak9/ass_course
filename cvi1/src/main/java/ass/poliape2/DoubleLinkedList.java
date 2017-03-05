package ass.poliape2;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoubleLinkedList<T> implements CustomList<T> {
    private Node first;
    private Node last;
    private int count;

    private void inc() {
        count++;
    }

    private void dec() {
        if (count > 0) {
            count--;
        }
    }

    public void append(T value) {
        if (first == null) {
            first = last = new Node(value);
        } else {
            last.setNext(new Node(value));
            last.getNext().setPrevious(last);
            last = last.getNext();
        }
        inc();
    }

    public void prepend(T value) {
        if (first == null) {
            first = last = new Node(value);
        } else {
            Node newFirst = new Node(value);
            newFirst.setNext(first);
            first.setPrevious(newFirst);
            first = newFirst;
        }
        inc();
    }

    public T removeFront() {
        if (first == null) return null;
        T res = first.getValue();
        if (first.getNext() != null) {
            first.getNext().setPrevious(null);
        }
        first = first.getNext();
        dec();
        return res;

    }

    public T removeBack() {
        if (last == null) return null;
        T res = last.getValue();
        if (last.getPrevious() != null) {
            last.getPrevious().setNext(null);
        }
        last = last.getPrevious();
        dec();
        return res;
    }

    public int takeAll(Collection<? super T> collection) {
        int count = 0;
        for (T current : this) {
            collection.add(current);
            count++;
        }
        return count;
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
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
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

    @Override
    public Iterator<T> iterator() {
        return forwardIterator();
    }

    private class Node {
        final T value;
        Node previous;
        Node next;

        Node(T value) {
            this.value = value;
        }

        T getValue() {
            return value;
        }

        Node getPrevious() {
            return previous;
        }

        void setPrevious(Node previous) {
            this.previous = previous;
        }

        Node getNext() {
            return next;
        }

        void setNext(Node next) {
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
        public boolean hasNext() {
            return next != null;
        }
    }
}
