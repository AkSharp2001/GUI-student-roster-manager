package tuition;

/**
 * A class that defines a Nonresident student by name, major, and credits.
 * This class contains methods for calculating the Nonresident student's
 * amount of tuition due and for generating a String representation of this
 * Nonresident student's information.
 *
 * @author Mervin James, Akshar Patel
 */
public class NonResident extends Student {
    protected static final float FULL_TIME_TUITION_FEE = 29737;
    protected static final float RATE_PER_CREDIT_HOUR = 966;

    /**
     * Constructs a Nonresident object by their name, major, and credits.
     *
     * @param name    the name of the Nonresident student.
     * @param major   the major of study the Nonresident student is pursuing.
     * @param credits the number of credits the Nonresident student is
     *                attempting.
     */
    public NonResident(String name, Major major, int credits) {
        super(name, major, credits);
    }

    /**
     * Calculates the Nonresident student's due tuition payment.
     * This method accounts for the tuition differences between part-time
     * and full-time nonresident students.
     */
    public void tuitionDue() {
        int numCredits = super.getCredits();
        float amountDue = 0;
        if (numCredits >= FULL_TIME_BASE_RATE_MAX_CREDITS) {
            amountDue =
                    FULL_TIME_TUITION_FEE + UNIVERSITY_FEE +
                            RATE_PER_CREDIT_HOUR * (numCredits -
                                    FULL_TIME_BASE_RATE_MAX_CREDITS);
        } else if (numCredits >= FULL_TIME_BASE_RATE_MIN_CREDITS) {
            amountDue = FULL_TIME_TUITION_FEE + UNIVERSITY_FEE;
        } else {
            amountDue =
                    RATE_PER_CREDIT_HOUR * numCredits +
                            PART_TIME_UNIVERSITY_FEE_MULTIPLIER *
                                    UNIVERSITY_FEE;
        }
        super.setAmountDue(amountDue - this.getTotalPayment());
    }

    /**
     * Generates a String representation of this Nonresident student object.
     *
     * @return the String representation of this Nonresident student object.
     */
    @Override
    public String toString() {
        return super.toString() + ":non-resident";
    }
}
