package com.uottawa.project;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
public class SingleHourTest {
    SingleHour s=new SingleHour(1,5,5);
    public void testDay(){
        Assert.assertEquals(s.day,1);
    }
    public void testStart(){
        Assert.assertEquals(s.start,5);
    }
    public void testEnd(){
        Assert.assertEquals(s.end,5);
    }
}
