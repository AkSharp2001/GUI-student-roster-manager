package com.example.project_3;

import java.text.DecimalFormat;

/**
 * A class that defines a Student by their profile and tuition information.
 * The Student class is the superclass for all the different types of
 * students in the roster, and has a template method called tuitionDue()
 * which is expected to be overridden in all subclasses. The Student class
 * also contains getter and setter methods for the Student's tuition, total
 * payment amount, tuition amount due, and number of credits attributes. This
 * class also contains getter methods for the Student's profile attribute,
 * in addition to a method for paying tuition. The Student class
 * also has methods to compare with other Student objects and to generate a
 * String representation of this Student's information.
 *
 * @author Mervin James, Akshar Patel
 */
public class Student {
    protected static final float UNIVERSITY_FEE = 3268;
    protected static final int FULL_TIME_BASE_RATE_MAX_CREDITS = 16;
    protected static final int FULL_TIME_BASE_RATE_MIN_CREDITS = 12;
    protected static final float PART_TIME_UNIVERSITY_FEE_MULTIPLIER = 0.8f;
    private final Profile profile;
    private int credits;
    private float tuition;
    private float totalPayment;
    private Date lastPaymentDate;
    private float amountDue;

    /**
     * Constructs a Student object by their name and major.
     *
     * @param name  the name of the student.
     * @param major the major of study the student is pursuing.
     */
    public Student(String name, Major major) {
        Profile profile = new Profile(name, major);
        this.profile = profile;
        this.credits = 0;
        this.tuition = 0;
        this.totalPayment = 0;
        this.lastPaymentDate = null;
        this.amountDue = 0;
    }

    /**
     * Constructs a Student by their name, major, and number of credits.
     *
     * @param name    the name of the student.
     * @param major   the major of study the student is pursuing.
     * @param credits the number of credits the student is attempting.
     */
    public Student(String name, Major major, int credits) {
        this.profile = new Profile(name, major);
        this.credits = credits;
        this.tuition = 0;
        this.totalPayment = 0;
        this.lastPaymentDate = null;
        this.amountDue = 0;
    }

    /**
     * Template method used to calculate the student's due tuition payment.
     * This method is expected to be overridden in all subclasses of Student.
     */
    public void tuitionDue() {
    }

    /**
     * Generates a String representation of this Student object.
     *
     * @return the String representation of this Student object.
     */
    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("###,##0.00");
        return profile + ":" + credits + " credit hours" +
                ":" + "tuition due:" + df.format(amountDue) + ":" + "total " +
                "payment:" + df.format(totalPayment) + ":" + "last payment " +
                "date: " + (lastPaymentDate == null ? "--/--/--" :
                lastPaymentDate.toString());
    }

    /**
     * Determines if this Student and another object are equivalent.
     * If the object is a Student, the method compares the student's
     * profile attribute to the object's.
     *
     * @param obj the object that this Student is being compared to.
     * @return true if both objects are Student objects with equivalent
     * profile attributes, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Student) {
            Student student = (Student) obj;
            return student.profile.equals(this.profile);
        }
        return false;
    }

    /**
     * Getter method for this Student's profile attribute.
     *
     * @return this Student's profile attribute.
     */
    public Profile getProfile() {
        return profile;
    }

    /**
     * Getter method for this Student's credit hours attribute.
     *
     * @return this Student's credits attribute.
     */
    public int getCredits() {
        return this.credits;
    }

    /**
     * Setter method for this Student's credits attribute.
     *
     * @param credits the number of credit hours that will set the credits
     *                attribute of this Student.
     */
    public void setCredits(int credits) {
        this.credits = credits;
    }

    /**
     * Getter method for this Student's tuition attribute.
     *
     * @return this Student's tuition attribute.
     */
    public float getTuition() {
        return this.tuition;
    }

    /**
     * Setter method for this Student's tuition attribute.
     *
     * @param tuition the tuition amount that this Student's tuition
     *                attribute will be set to.
     */
    public void setTuition(float tuition) {
        this.tuition = tuition;
    }

    /**
     * Getter method for this Student's total payment attribute.
     *
     * @return this Student's totalPayment attribute.
     */
    public float getTotalPayment() {
        return this.totalPayment;
    }

    /**
     * Setter method for this Student's totalPayment attribute.
     *
     * @param totalPayment this total payment amount that this Student's
     *                     totalPayment attribute will be set to.
     */
    public void setTotalPayment(float totalPayment) {
        this.totalPayment = totalPayment;
    }

    /**
     * Getter method for this Student's date of last payment.
     *
     * @return this Student's lastPaymentDate attribute.
     */
    public Date getLastPaymentDate() {
        return lastPaymentDate;
    }

    /**
     * Setter method for this Student's lastPaymentDate attribute.
     *
     * @param lastPaymentDate the date of the Student's latest payment.
     */
    public void setLastPaymentDate(Date lastPaymentDate) {
        this.lastPaymentDate = lastPaymentDate;
    }

    /**
     * Method to update the student's tuition payment information.
     * This method updates the student's total payment, amount due, and last
     * payment date information.
     *
     * @param payment     the amount the student has paid towards their
     *                    tuition.
     * @param paymentDate the date of the student's latest payment.
     */
    public void payTuition(float payment, Date paymentDate) {
        this.totalPayment += payment;
        this.amountDue -= payment;
        this.lastPaymentDate = paymentDate;
    }

    /**
     * Getter method for the amount due for the Student.
     *
     * @return the Student's amountDue attribute.
     */
    public float getAmountDue() {
        return this.amountDue;
    }

    /**
     * Setter method for this Student's amountDue attribute.
     *
     * @param amountDue the due tuition amount for the Student.
     */
    public void setAmountDue(float amountDue) {
        this.amountDue = amountDue;
    }

}
