package com.uottawa.project;


import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

public class RatingCalcTest {

     List<Clinic> ClinicList;
     List<Float> ratingsList;
     List<Review> intaialratingsList;

    public void testRatingList(){
        ClinicList = new ArrayList<>();
         ratingsList = new ArrayList<>();
         intaialratingsList = new ArrayList<>();
        Clinic c1 = new Clinic("c1");
        Clinic c2 = new Clinic("c2");
        Clinic c3 = new Clinic("c3");

        ClinicList.add(c1);
        ClinicList.add(c2);
        ClinicList.add(c3);

        Review r1 = new Review("c1", "bob","",4);
        Review r2 = new Review("c1", "bob","",2);
        Review r3 = new Review("c2", "bob","",3);

        intaialratingsList.add(r1);
        intaialratingsList.add(r2);
        intaialratingsList.add(r3);

        ratingsList = Search.calRating(ClinicList,intaialratingsList);

        Assert.assertEquals((double)ratingsList.get(0),3.0);
        Assert.assertEquals((double)ratingsList.get(1),3.0);
        Assert.assertEquals((double)ratingsList.get(2),0.0);
    }

}
