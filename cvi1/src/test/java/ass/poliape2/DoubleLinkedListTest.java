package ass.poliape2;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.testng.Assert.*;


public class DoubleLinkedListTest {
    private static DoubleLinkedList<Integer> list;

    @BeforeMethod
    public void setUp() {
        list = new DoubleLinkedList<Integer>();
    }


    @Test
    public void testPrependRemoveBack() {
        list.prepend(1);
        Assert.assertEquals(list.count(), 1,"Count 1");
        list.prepend(2);
        Assert.assertEquals(list.count(), 2, "Count 2");
        Assert.assertEquals(list.removeBack(), new Integer(1), "Value 1");
        Assert.assertEquals(list.removeBack(), new Integer(2), "Value2");
        Assert.assertEquals(list.count(), 0);
    }

    @Test
    public void testAppendRemoveFront() {
        list.append(1);
        Assert.assertEquals(list.count(), 1,"Count 1");
        list.append(2);
        Assert.assertEquals(list.count(), 2, "Count 2");
        Assert.assertEquals(list.removeFront(), new Integer(1), "Value 1");
        Assert.assertEquals(list.removeFront(), new Integer(2), "Value2");
        Assert.assertEquals(list.count(), 0);
    }

    @Test
    public void testTakeAll() {
        list.append(1);
        list.append(2);
        List<Integer> consumer = new ArrayList<>(3);
        assertEquals(list.takeAll(consumer),2,"Add");
        assertEquals(consumer.size(),2,"Size");
        assertEquals(consumer.get(0),new Integer(1),"Elem 1");
        assertEquals(consumer.get(1),new Integer(2),"Elem 2");
    }

    @Test
    public void testAppendAll() {
        list.append(0);
        List<Integer> add = new ArrayList<Integer>();
        add.add(1);
        Assert.assertEquals(list.appendAll(add),1,"Add 1");
        add.add(2);
        Assert.assertEquals(list.appendAll(add),2,"Add 2");
        Assert.assertEquals(list.count(),4,"Count");
    }

    @Test
    public void testForwardIterator() {
        list.append(1);
        list.append(2);
        Iterator<Integer> i = list.forwardIterator();
        Assert.assertTrue(i.hasNext(),"Full hasNext");
        Assert.assertEquals(i.next(),new Integer(1),"Next 1");
        Assert.assertTrue(i.hasNext(),"After first hasNext");
        Assert.assertEquals(i.next(),new Integer(2),"Next 2");
        Assert.assertFalse(i.hasNext(),"Last hasNext");
    }

    @Test
    public void testBackwardIterator() {
        list.append(1);
        list.append(2);
        Iterator<Integer> i = list.backwardIterator();
        Assert.assertTrue(i.hasNext(),"Full hasNext");
        Assert.assertEquals(i.next(),new Integer(2),"Next 1");
        Assert.assertTrue(i.hasNext(),"After first hasNext");
        Assert.assertEquals(i.next(),new Integer(1),"Next 2");
        Assert.assertFalse(i.hasNext(), "Last hasNext");

    }

}