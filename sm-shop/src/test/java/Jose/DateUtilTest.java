//Jose Oviedo - 9 casos
package com.salesmanager.test.shop.Jose;

import com.salesmanager.shop.application.ShopApplication;
import com.salesmanager.shop.utils.DateUtil;
import com.salesmanager.test.shop.common.ServicesTestSupport;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@SpringBootTest(classes = ShopApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class DateUtilTest extends ServicesTestSupport{
    @Test
    public void getPresentYearTest(){
        String year = DateUtil.getPresentYear();
        boolean flag = year.equals("2020");
        assertTrue(flag);
    }

    @Test
    public void formatLongDateTest(){
        //31-12-1969 18:00:00 -0600
        Date d1 = new Date(0);
        String longDate = DateUtil.formatLongDate(d1);
        String expected = "31 dic 1969 18:00:00 -0600";
        boolean flag = longDate.contains(expected);
        assertTrue(flag);
    }

    @Test
    public void formatYearTest(){
        //31-12-1969 18:00:00 -0600
        Date d1 = new Date(0);
        String year = DateUtil.formatYear(d1);
        String expected = "1969";
        boolean flag = expected.equals(year);
        assertTrue(flag);
    }

    @Test
    public void dateBeforeEqualsDateWithEquals() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String s1 = "10/10/2020";
        Date d1 = sdf.parse(s1);
        String s2 = "10/10/2020";
        Date d2 = sdf.parse(s2);
        boolean flag = DateUtil.dateBeforeEqualsDate(d1, d2);
        assertTrue(flag);
    }

    @Test
    public void dateBeforeEqualsDateWithBefore() throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String s1 = "01/07/2020";
        Date d1 = sdf.parse(s1);
        String s2 = "10/10/2020";
        Date d2 = sdf.parse(s2);
        boolean flag = DateUtil.dateBeforeEqualsDate(d1, d2);
        assertTrue(flag);
    }

    @Test
    public void dateBeforeEqualsDateWithAfter() throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String s1 = "11/12/2020";
        Date d1 = sdf.parse(s1);
        String s2 = "10/10/2020";
        Date d2 = sdf.parse(s2);
        boolean flag = DateUtil.dateBeforeEqualsDate(d1, d2);
        assertFalse(flag);
    }

    @Test
    public void formatDateMonthStringTest(){
        Date d1 = new Date(100000);
        String s1 = DateUtil.formatDateMonthString(d1);
        System.out.println(s1);
        boolean flag = s1.equals("1969-12-31");
        assertTrue(flag);
    }

    @Test
    public void getDateValid() throws Exception {
        Date d1 = new Date(21600000);
        String s1 = "1970-1-1";
        Date d2 = DateUtil.getDate(s1);
        boolean flag = d1.equals(d2);
        assertTrue(flag);
    }

    @Test(expected = Exception.class)
    public void getDateInvalid() throws Exception {
        Date d1 = new Date(21600000);
        String s1 = "INCORRECT FORMAT";
        Date d2 = DateUtil.getDate(s1);
    }


}
