package com.uottawa.project;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
public class ReviewTest {
    Review r=new Review("Tim","Tom","Bad",2);
    public void testClinic(){
        Assert.assertEquals(r.getClinic(),"Tim");
    }
    public void testPatient(){
        Assert.assertEquals(r.getPatient(),"Tom");
    }
    public void testComment(){
        Assert.assertEquals(r.getComment(),"Bad");
    }
    public void testRating(){
        Assert.assertEquals(r.getRating(),2);
    }
}
