//package com.example.project_3;
//
//import org.junit.Test;
//
//import static org.junit.Assert.*;
//
///**
// * Class that conducts a JUnit test on tuitionDue() from International class.
// *
// * @author Mervin James, Akshar Patel
// */
//public class InternationalTest {
//
//    /**
//     * Conducts a JUnit test on tuitionDue() from the International class.
//     */
//    @org.junit.Test
//    public void tuitionDue() {
//        //test case #1: amount of tuition due for an International student
//        // who is studying abroad should be $5,918.
//        Student iStudent = new International("Alex Jones", Major.CS, 15,
//                true);
//        iStudent.tuitionDue();
//        assertEquals(5918f, iStudent.getAmountDue(), 0f);
//
//        //test case #2: amount of tuition due for a full-time International
//        // student taking more than 16 credits should be $(35,655 + 966*x),
//        // where x is the number of credits over 16. In this test case, x
//        // is 2.
//        iStudent = new International("Alex Jones", Major.CS, 18,
//                false);
//        iStudent.tuitionDue();
//        assertEquals((35655f + 966 * 2), iStudent.getAmountDue(), 0f);
//
//        //test case #3: amount of tuition due for a full-time International
//        // student taking at least 12 and less than 16 credits is $35,655.
//        iStudent = new International("Alex Jones", Major.CS, 15,
//                false);
//        iStudent.tuitionDue();
//        assertEquals((35655f), iStudent.getAmountDue(), 0f);
//
//        //test case #4: amount of tuition due for a part-time International
//        // student taking x total credits should be $(2614.40 + 966*x). In
//        // this test case, x is 9
//        iStudent = new International("Alex Jones", Major.CS, 9,
//                false);
//        iStudent.tuitionDue();
//        assertEquals((2614.4f + 966 * 9), iStudent.getAmountDue(), 0f);
//    }
//}