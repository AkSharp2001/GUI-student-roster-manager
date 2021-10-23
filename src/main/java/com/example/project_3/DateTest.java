//package com.example.project_3;
//
//import static org.junit.Assert.*;
//
///**
// * Class that conducts a JUnit test on isValid() from the Date class.
// *
// * @author Mervin James, Akshar Patel
// */
//public class DateTest {
//
//    /**
//     * Conducts a JUnit test on isValid() from the Date class.
//     */
//    @org.junit.Test
//    public void isValid() {
//        //test case #1: a date with a year before 2021 should be invalid.
//        Date date = new Date("12/31/2020");
//        assertFalse(date.isValid());
//
//        //test case #2: a date with a year equal to 2021 should be valid.
//        date = new Date("1/1/2021");
//        assertTrue(date.isValid());
//
//        //test case #3: a date after the current date should be invalid.
//        date = new Date("12/1/2021");
//        assertFalse(date.isValid());
//
//        //test case #4: a date with a negative value for day should be
//        // invalid.
//        date = new Date("3/-1/2021");
//        assertFalse(date.isValid());
//
//        //test case #5: a date with a value for day of 0 should be invalid.
//        date = new Date("3/0/2021");
//        assertFalse(date.isValid());
//
//        //test case #6: a date with a negative value for month should be
//        // invalid.
//        date = new Date("-1/3/2021");
//        assertFalse(date.isValid());
//
//        //test case #7: a date with a value for month of 0 should be invalid.
//        date = new Date("0/3/2021");
//        assertFalse(date.isValid());
//
//        //test case #8: a date with a value for month greater than 12 should
//        // be invalid.
//        date = new Date("13/3/2021");
//        assertFalse(date.isValid());
//
//        //test case #9: a date with valid year, month in January, and
//        // invalid day should be invalid.
//        date = new Date("1/32/2021");
//        assertFalse(date.isValid());
//
//        //test case #10: a date with valid year, month in January, and
//        // day less than or equal to 31 should be valid.
//        date = new Date("1/31/2021");
//        assertTrue(date.isValid());
//
//        //test case #11: a date with valid year, month in January, and
//        // invalid day should be invalid.
//        date = new Date("1/32/2021");
//        assertFalse(date.isValid());
//
//        //test case #12: a date with valid year, month in January, and
//        // day less than or equal to 31 should be valid.
//        date = new Date("1/31/2021");
//        assertTrue(date.isValid());
//
//        //test case #13: a date with valid year, month in February, and
//        // invalid day should be invalid.
//        date = new Date("2/29/2021");
//        assertFalse(date.isValid());
//
//        //test case #14: a date with valid year, month in February, and
//        // day less than or equal to 28 should be valid.
//        date = new Date("2/28/2021");
//        assertTrue(date.isValid());
//
//        //test case #15: a date with valid year, month in March, and
//        // invalid day should be invalid.
//        date = new Date("3/32/2021");
//        assertFalse(date.isValid());
//
//        //test case #16: a date with valid year, month in March, and
//        // day less than or equal to 31 should be valid.
//        date = new Date("3/31/2021");
//        assertTrue(date.isValid());
//
//        //test case #17: a date with valid year, month in April, and
//        // invalid day should be invalid.
//        date = new Date("4/31/2021");
//        assertFalse(date.isValid());
//
//        //test case #18: a date with valid year, month in April, and
//        // day less than or equal to 30 should be valid.
//        date = new Date("4/30/2021");
//        assertTrue(date.isValid());
//
//        //test case #19: a date with valid year, month in May, and
//        // invalid day should be invalid.
//        date = new Date("5/32/2021");
//        assertFalse(date.isValid());
//
//        //test case #20: a date with valid year, month in May, and
//        // day less than or equal to 31 should be valid.
//        date = new Date("5/31/2021");
//        assertTrue(date.isValid());
//
//        //test case #21: a date with valid year, month in June, and
//        // invalid day should be invalid.
//        date = new Date("6/31/2021");
//        assertFalse(date.isValid());
//
//        //test case #22: a date with valid year, month in June, and
//        // day less than or equal to 30 should be valid.
//        date = new Date("6/30/2021");
//        assertTrue(date.isValid());
//
//        //test case #23: a date with valid year, month in July, and
//        // invalid day should be invalid.
//        date = new Date("7/32/2021");
//        assertFalse(date.isValid());
//
//        //test case #24: a date with valid year, month in July, and
//        // day less than or equal to 31 should be valid.
//        date = new Date("7/31/2021");
//        assertTrue(date.isValid());
//
//        //test case #25: a date with valid year, month in August, and
//        // invalid day should be invalid.
//        date = new Date("8/32/2021");
//        assertFalse(date.isValid());
//
//        //test case #26: a date with valid year, month in August, and
//        // day less than or equal to 31 should be valid.
//        date = new Date("8/31/2021");
//        assertTrue(date.isValid());
//
//        //test case #27: a date with valid year, month in September, and
//        // invalid day should be invalid.
//        date = new Date("9/31/2021");
//        assertFalse(date.isValid());
//
//        //test case #28: a date with valid year, month in September, and
//        // day less than or equal to 30 should be valid.
//        date = new Date("9/30/2021");
//        assertTrue(date.isValid());
//
//        //test case #29: a date with valid year, month in October, and
//        // invalid day should be invalid.
//        date = new Date("10/32/2021");
//        assertFalse(date.isValid());
//
//        //test case #30: a date preceding the current date and
//        // with valid year, month in October, and day less than or equal to
//        // 31 should be valid.
//        date = new Date("10/9/2021");
//        assertTrue(date.isValid());
//
//        //test case #31: a date with the current date and with valid year,
//        // month in October, and day less than or equal to 31 should be valid.
//        date = new Date();
//        assertFalse(date.isValid());
//
//        //test case #32: a date with a year after 2021 should be invalid.
//        date = new Date("1/1/2022");
//        assertFalse(date.isValid());
//    }
//}