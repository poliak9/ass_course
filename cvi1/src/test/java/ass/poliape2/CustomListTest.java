package ass.poliape2;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class CustomListTest {

    @Test
    public void TestCount(){
        List<Integer> l = new ArrayList<Integer>(){{
            add(1);
            add(2);
            add(3);
        }};
        Assert.assertEquals(CustomList.count(l),3,"Count 1");
        l.add(4);
        Assert.assertEquals(CustomList.count(l),4,"Count 2");

    }
}
