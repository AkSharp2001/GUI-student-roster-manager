package com.example.project_3;

import java.text.DecimalFormat;

/**
 * A class that defines a Resident student by name, major, and credits.
 * This class contains methods for calculating the Resident student's
 * amount of tuition due and for generating a String representation of this
 * Resident student's information. This class also contains a getter and
 * setter method for this Resident student's financialAid attribute.
 *
 * @author Mervin James, Akshar Patel
 */
public class Resident extends Student {
    private static final float FULL_TIME_TUITION_FEE = 12536;
    private static final float RATE_PER_CREDIT_HOUR = 404;
    private float financialAid;

    /**
     * Constructs a Resident object by their name, major, and credits.
     *
     * @param name    the name of the Resident student.
     * @param major   the major of study of the Resident student.
     * @param credits the number of credits the Resident student is
     *                attempting.
     */
    public Resident(String name, Major major, int credits) {
        super(name, major, credits);
    }

    /**
     * Calculates the Resident student's due tuition payment.
     * This method accounts for the tuition differences between part-time and
     * full-time Resident students, including those who take more than 16
     * credits.
     */
    @Override
    public void tuitionDue() {
        int numCredits = super.getCredits();
        float amountDue = 0;
        if (numCredits >= FULL_TIME_BASE_RATE_MAX_CREDITS) {
            amountDue =
                    FULL_TIME_TUITION_FEE + UNIVERSITY_FEE +
                            RATE_PER_CREDIT_HOUR * (numCredits -
                                    FULL_TIME_BASE_RATE_MAX_CREDITS) -
                            financialAid;
        } else if (numCredits >= FULL_TIME_BASE_RATE_MIN_CREDITS) {
            amountDue = FULL_TIME_TUITION_FEE + UNIVERSITY_FEE - financialAid;
        } else {
            amountDue =
                    RATE_PER_CREDIT_HOUR * numCredits +
                            PART_TIME_UNIVERSITY_FEE_MULTIPLIER *
                                    UNIVERSITY_FEE - financialAid;
        }
        super.setAmountDue(amountDue - this.getTotalPayment());
    }

    /**
     * Generates a String representation of this Resident student object.
     *
     * @return the String representation of this Resident student object.
     */
    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("$###,##0.00");
        return super.toString() + ":resident" + ((financialAid > 0) ?
                ":financial aid " + df.format(getFinancialAid()) : "");
    }

    /**
     * Getter method for this Resident Student's financial aid attribute.
     *
     * @return this Resident student's financialAid attribute.
     */
    public float getFinancialAid() {
        return this.financialAid;
    }

    /**
     * Setter method for this Resident student's financialAid attribute.
     *
     * @param financialAid the amount of financial aid for this Resident
     *                     student.
     */
    public void setFinancialAid(float financialAid) {
        this.financialAid = financialAid;
        this.setAmountDue(this.getAmountDue() - financialAid);
    }

}
