package ass.poliape2;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.testng.Assert.*;


public class DoubleLinkedListTest {
    private DoubleLinkedList<Integer> list;

    @BeforeMethod
    public void setUp() {
        list = new DoubleLinkedList<>();
    }


    @Test
    public void testPrependRemoveBack() {
        list.prepend(1);
        assertEquals(list.count(), 1, "Count 1");
        list.prepend(2);
        assertEquals(list.count(), 2, "Count 2");
        assertEquals(list.removeBack(), new Integer(1), "Value 1");
        assertEquals(list.removeBack(), new Integer(2), "Value2");
        assertEquals(list.count(), 0);
    }

    @Test
    public void testAppendRemoveFront() {
        list.append(1);
        assertEquals(list.count(), 1, "Count 1");
        list.append(2);
        assertEquals(list.count(), 2, "Count 2");
        assertEquals(list.removeFront(), new Integer(1), "Value 1");
        assertEquals(list.removeFront(), new Integer(2), "Value2");
        assertEquals(list.count(), 0);
    }

    @Test
    public void testTakeAll() {
        list.append(1);
        list.append(2);
        List<Integer> consumer = new ArrayList<>(3);
        assertEquals(list.takeAll(consumer), 2, "Add");
        assertEquals(consumer.size(), 2, "Size");
        assertEquals(consumer.get(0), new Integer(1), "Elem 1");
        assertEquals(consumer.get(1), new Integer(2), "Elem 2");
    }

    @Test
    public void testAppendAll() {
        list.append(0);
        List<Integer> add = new ArrayList<>();
        add.add(1);
        assertEquals(list.appendAll(add), 1, "Add 1");
        add.add(2);
        assertEquals(list.appendAll(add), 2, "Add 2");
        assertEquals(list.count(), 4, "Count");
    }

    @Test
    public void testForwardIterator() {
        list.append(1);
        list.append(2);
        checkIterator(list.forwardIterator(), new Integer[]{1, 2});
    }

    @Test
    public void testBackwardIterator() {
        list.append(1);
        list.append(2);
        checkIterator(list.backwardIterator(), new Integer[]{2, 1});
    }

    private <T> void checkIterator(Iterator<T> iterator, T[] values) {
        int i;
        for (i = 0; i < values.length; i++) {
            assertTrue(iterator.hasNext(), "Iterator doesn't have expected number of elements");
            assertEquals(iterator.next(), values[i], String.format("Element %d isn't as expected.", i));
        }
        assertFalse(iterator.hasNext(), "Iterator has more elements than expected array of values");
    }

}