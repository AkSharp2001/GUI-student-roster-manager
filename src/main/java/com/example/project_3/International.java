package com.example.project_3;

/**
 * A class that defines an International student by name, major, and credits.
 * This class contains a setter method for the International student's
 * study abroad status. The International class also has methods for
 * calculating the International student's amount of tuition due and for
 * generating a String representation of this International student's
 * information.
 *
 * @author Mervin James, Akshar Patel
 */
public class International extends NonResident {
    private static final int STUDY_ABROAD_MAX_CREDITS = 12;
    private static final float ADDITIONAL_FEE = 2650;
    private boolean isStudyAbroad;

    /**
     * Constructs an International object by their name, major, and credits.
     *
     * @param name          the name of the International student.
     * @param major         the major of study the International student is
     *                      pursuing.
     * @param credits       the number of credits the International student
     *                      is attempting.
     * @param isStudyAbroad the study abroad status of the International
     *                      student.
     */
    public International(String name, Major major, int credits,
                         boolean isStudyAbroad) {
        super(name, major, credits);
        this.isStudyAbroad = isStudyAbroad;
    }

    /**
     * Setter method for this International student's study abroad attribute.
     *
     * @param isStudyAbroad the study abroad status of this Student.
     */
    public void setStudyAbroadStatus(boolean isStudyAbroad) {
        this.isStudyAbroad = isStudyAbroad;
        if (isStudyAbroad) {
            if (this.getCredits() > STUDY_ABROAD_MAX_CREDITS) {
                this.setCredits(STUDY_ABROAD_MAX_CREDITS);
            }
        }
        this.setTuition(0);
        this.setTotalPayment(0);
        this.setLastPaymentDate(null);
        tuitionDue();
    }

    /**
     * Calculates the International student's due tuition payment.
     * This method accounts for the tuition differences between part-time and
     * full-time international students, including those who take more than
     * 16 credits.
     */
    @Override
    public void tuitionDue() {
        int numCredits = super.getCredits();
        float amountDue = 0;
        if (isStudyAbroad) {
            amountDue = UNIVERSITY_FEE + ADDITIONAL_FEE;
            super.setAmountDue(amountDue);
            return;
        }
        if (numCredits >= FULL_TIME_BASE_RATE_MAX_CREDITS) {
            amountDue =
                    FULL_TIME_TUITION_FEE + UNIVERSITY_FEE + ADDITIONAL_FEE +
                            RATE_PER_CREDIT_HOUR * (numCredits -
                                    FULL_TIME_BASE_RATE_MAX_CREDITS);
        } else if (numCredits >= FULL_TIME_BASE_RATE_MIN_CREDITS) {
            amountDue =
                    FULL_TIME_TUITION_FEE + UNIVERSITY_FEE + ADDITIONAL_FEE;
        } else {
            amountDue =
                    RATE_PER_CREDIT_HOUR * numCredits +
                            PART_TIME_UNIVERSITY_FEE_MULTIPLIER *
                                    UNIVERSITY_FEE;
        }
        super.setAmountDue(amountDue - this.getTotalPayment());
    }

    /**
     * Generates a String representation of this International student object.
     *
     * @return the String representation of this International student object.
     */
    @Override
    public String toString() {
        return super.toString() + (isStudyAbroad ? ":international:study " +
                "abroad" : ":international");
    }
}
