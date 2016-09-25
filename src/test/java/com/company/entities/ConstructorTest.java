package com.company.entities;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Дмитрий on 25.09.2016.
 */
public class ConstructorTest {

    @Rule
    public final ExpectedException exp = ExpectedException.none();

    @Test
    public void testDriverLicenseCategories() throws Exception {
        List categories = new ArrayList();
        categories.add("A");
        DriverLicense dr = new DriverLicense(new Date(), categories, new Date());
        Assert.assertNotNull(dr);
    }

    @Test
    public void testDriverLicenseCategoriesEnum() throws Exception {
        List categories = new ArrayList();
        DriverLicense dr = new DriverLicense(new Date(), DriverLicense.Category.C, new Date());
    //    System.out.println(dr.toString());
        Assert.assertNotNull(dr);
    }

    @Test
    public void testDriverLicenseFalseCategories() throws Exception {
        List categories = new ArrayList();
        categories.add("fsddsfsd");
        exp.expect(IllegalArgumentException.class);
        DriverLicense dr = new DriverLicense(new Date(), categories, new Date());
        System.out.println(dr.toString());
    }

    @Test
    public void testDriverLicenseEmptyCategories() throws Exception {
        List categories = new ArrayList();
        categories.add(new String());
        exp.expect(IllegalArgumentException.class);
        DriverLicense dr = new DriverLicense(new Date(), categories, new Date());
        System.out.println(dr.toString());
    }

    @Test
    public void testDriverLicenseSetCategories() throws Exception {
        List categories = new ArrayList();
        categories.add("fsddsfsd");
        DriverLicense dr = new DriverLicense(new Date(), DriverLicense.Category.C, new Date());
        exp.expect(IllegalArgumentException.class);
        dr.setCategories(categories);
        System.out.println(dr.toString());
    }

    @Test
    public void testDatePeriod() throws Exception {
        List categories = new ArrayList();

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 1950);
        cal.set(Calendar.MONTH, 10);
        cal.set(Calendar.DAY_OF_MONTH, 30);
        Date expire = cal.getTime();

        cal.set(Calendar.YEAR, 1990);
        Date from = cal.getTime();

        DriverLicense dr = new DriverLicense(expire, categories, from);
        Assert.assertEquals(dr.getFromDate().compareTo(dr.getExpires()), -1);
    }

    @Test
    public void testDateBorders() throws Exception {
        List categories = new ArrayList();

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 3);
        cal.set(Calendar.MONTH, 1);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date expire = cal.getTime();

        cal.set(Calendar.YEAR, 12000);
        Date from = cal.getTime();

        exp.expect(IllegalArgumentException.class);
        DriverLicense dr = new DriverLicense(expire, categories, from);
        System.out.println(dr.toString());
    }
    /* Test added only for DriverLicence method. Tests for other methods will be similar.
    There are no data verification for any constructor or "set" method so there are no reason to write tests for every method */
}