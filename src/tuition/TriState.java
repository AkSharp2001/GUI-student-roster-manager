package tuition;

/**
 * A class that defines TriState student by profile and discount information.
 * This class contains methods for calculating the TriState student's
 * amount of tuition due and for generating a String representation of this
 * TriState student's information.
 *
 * @author Mervin James, Akshar Patel
 */
public class TriState extends NonResident {
    private final float discount;
    private final State state;

    /**
     * Constructs a Tristate object by name, major, credits, discount, state.
     *
     * @param name     the name of the Tristate student.
     * @param major    the major of study of the Tristate student.
     * @param credits  the number of credits the Tristate student is
     *                 attempting.
     * @param discount the discount the Tristate student receives due to
     *                 residing in the Tristate area.
     * @param state    the state the Tristate student is residing in.
     */
    public TriState(String name, Major major, int credits, float discount,
                    State state) {
        super(name, major, credits);
        this.discount = discount;
        this.state = state;
    }

    /**
     * Calculates the Tristate student's due tuition payment.
     * This method accounts for the tuition differences between part-time and
     * full-time Tristate students, including those who take more than 16
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
                            discount;
        } else if (numCredits >= FULL_TIME_BASE_RATE_MIN_CREDITS) {
            amountDue = FULL_TIME_TUITION_FEE + UNIVERSITY_FEE - discount;
        } else {
            amountDue =
                    RATE_PER_CREDIT_HOUR * numCredits +
                            PART_TIME_UNIVERSITY_FEE_MULTIPLIER *
                                    UNIVERSITY_FEE;
        }
        super.setAmountDue(amountDue - this.getTotalPayment());
    }

    /**
     * Generates a String representation of this Tristate student object.
     *
     * @return the String representation of this Tristate student object.
     */
    @Override
    public String toString() {
        return super.toString() + "(tri-state):" + state;
    }

}
