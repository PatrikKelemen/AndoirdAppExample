package com.uottawa.project;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

public class SearchClinicTest {

    List<Clinic> ClinicList;
    List<Clinic> newClinicList;
    public void testSearchClinic() {
        ClinicList = new ArrayList<>();
        Clinic c1 = new Clinic("c1");
        Clinic c2 = new Clinic("c2");
        Clinic c3 = new Clinic("c3");

        ClinicList.add(c1);
        ClinicList.add(c2);
        ClinicList.add(c3);

        newClinicList = Search.searchClinic(ClinicList,"c2");
        Assert.assertEquals(newClinicList.get(0),c2);
    }
}
