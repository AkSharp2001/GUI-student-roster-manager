package com.example.project_3;

import java.util.Calendar;
import java.util.StringTokenizer;

/**
 * A class that defines a Date object by year, month, and day.
 * The Date class also has methods to check if a Date is valid, to compare
 * Date objects, and to generate a String representation of a Date object.
 * There are also getter methods defined for the day, month, and year
 * attributes.
 *
 * @author Akshar Patel, Mervin James
 */
public class Date implements Comparable<Date> {
    private static final int CURRENT_YEAR = 2021;
    private static final int NUM_DAYS_FEBRUARY_NON_LEAP_YEAR = 28;
    private static final int DAYS_IN_LITTLE_MONTH = 30;
    private static final int DAYS_IN_LARGE_MONTH = 31;
    private static final int JANUARY = 1;
    private static final int FEBRUARY = 2;
    private static final int MARCH = 3;
    private static final int APRIL = 4;
    private static final int MAY = 5;
    private static final int JUNE = 6;
    private static final int JULY = 7;
    private static final int AUGUST = 8;
    private static final int SEPTEMBER = 9;
    private static final int OCTOBER = 10;
    private static final int NOVEMBER = 11;
    private static final int DECEMBER = 12;
    private final int year;
    private final int month;
    private final int day;

    /**
     * Constructor for the Date class which takes in a date of type String.
     * This constructor uses a StringTokenizer to break the date into tokens,
     * and then parses each token into an Integer.
     *
     * @param date the String representation of the date of an album
     */
    public Date(String date) {
        StringTokenizer st = new StringTokenizer(date, "-");
        year = Integer.parseInt(st.nextToken());
        month = Integer.parseInt(st.nextToken());
        day = Integer.parseInt(st.nextToken());
    }

    /**
     * Default constructor for the Date class, which represents today's date.
     * This constructor generates a Date object with today's month, day, and
     * year.
     */
    public Date() {
        Calendar calendar = Calendar.getInstance();
        month = calendar.get(Calendar.MONTH) + 1;
        day = calendar.get(Calendar.DATE);
        year = calendar.get(Calendar.YEAR);
    } //create an object with today’s date (see Calendar class)

    /**
     * Determines if this Date object has valid attributes.
     *
     * @return true if this Date object has a valid date in the current year
     */
    public boolean isValid() {
        if (this.year != CURRENT_YEAR) {
            return false;
        }
        if (this.compareTo(new Date()) >= 0) {
            return false;
        }
        if (this.month < JANUARY || this.day < 1) {
            return false;
        }
        if (this.month == JANUARY || this.month == MARCH || this.month == MAY ||
                this.month == JULY || this.month == AUGUST ||
                this.month == OCTOBER || this.month == DECEMBER) {
            return this.day <= DAYS_IN_LARGE_MONTH;
        } else if (this.month == APRIL || this.month == JUNE ||
                this.month == SEPTEMBER || this.month == NOVEMBER) {
            return this.day <= DAYS_IN_LITTLE_MONTH;
        } else if (this.month == FEBRUARY) {
            return this.day <= NUM_DAYS_FEBRUARY_NON_LEAP_YEAR;
        }
        return false;
    }

    /**
     * Compares this Date and another Date to determine Date order.
     *
     * @param date the Date object that this Date object is compared to.
     * @return -1 if this Date precedes the Date being compared to, 1 if this
     * Date postdates the Date being compared to, and 0 if both dates have
     * the equivalent attributes.
     */
    @Override
    public int compareTo(Date date) {
        if (this.equals(date)) {
            return 0;
        }
        if (this.year < date.year) {
            return -1;
        }
        if (this.year == date.year) {
            if (this.month == date.month) {
                if (this.day < date.day) {
                    return -1;
                }
            } else if (this.month < date.month) {
                return -1;
            }
        }
        return 1;
    }

    /**
     * Determines if this Date and another object have equivalent attributes.
     *
     * @param obj the object that this Date object is being compared to.
     * @return true if both objects are Date objects with the same
     * attributes, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Date) {
            Date date = (Date) obj;
            return (date.month == this.month && date.day == this.day &&
                    date.year == this.year);
        }
        return false;
    }

    /**
     * Generates a String representation of this Date object.
     *
     * @return the String representation of this Date object.
     */
    @Override
    public String toString() {
        return month + "/" + day + "/" + year;
    }
}
