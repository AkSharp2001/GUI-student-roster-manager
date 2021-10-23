package com.example.project_3;

/**
 * A class that can add, remove, retrieve, and sort Students in a roster.
 * The Roster class also has a method to calculate the tuition due of all
 * students in the roster, methods to generate String representations of the
 * Roster's students by a particular order, a method to grow the roster
 * array, and a method to find students within the roster.
 *
 * @author Mervin James, Akshar Patel
 */
public class Roster {
    private static final int NOT_FOUND = -1;
    private Student[] roster;
    private int size; //keep track of the number of students in the roster

    /**
     * Constructs an empty roster from an empty array of Students.
     *
     * @param roster the empty array of Student objects.
     */
    public Roster(Student[] roster) {
        this.roster = roster;
        this.size = 0;
    }

    /**
     * Helper method that finds a Student in the roster, if they exist.
     *
     * @param student the student attempted to being found.
     * @return the index of the Student inside the roster, otherwise -1 if
     * not found.
     */
    private int find(Student student) {
        for (int i = 0; i < size; i++) {
            if (roster[i].equals(student)) {
                return i;
            }
        }
        return NOT_FOUND;
    }

    /**
     * Helper method that increases the capacity of the roster array.
     * This method is called when there are no more empty spaces for
     * Students inside the roster, and increases the roster array's
     * capacity by 4.
     */
    private void grow() {
        Student[] newRoster = new Student[size + 4];
        for (int i = 0; i < size; i++) {
            newRoster[i] = roster[i];
        }
        roster = newRoster;
    }

    /**
     * Adds a Student to the roster, if they aren't already included.
     *
     * @param student the Student being added to the roster.
     * @return true if the Student is added to the roster, otherwise false
     * if the Student is already in the roster to begin with.
     */
    public boolean add(Student student) {
        if (find(student) != -1) {
            System.out.println("Student is already in the roster.");
            return false;
        }
        roster[size++] = student;
        if (size == roster.length) {
            grow();
        }
        return true;
    }

    /**
     * Removes a Student from the roster, if they exist inside.
     *
     * @param student the Student to be removed.
     * @return true if the Student is removed, otherwise false if the
     * Student does not exist in the roster to begin with.
     */
    public boolean remove(Student student) {
        int studentIndex = find(student);
        if (studentIndex == -1) {
            return false;
        }
        Student[] newRoster = new Student[size];
        boolean isSkipped = false;  //marks when the deleted student has
        // been skipped
        for (int i = 0; i < size; i++) {
            if (i == studentIndex) {
                isSkipped = true;
            }
            if (isSkipped) {
                newRoster[i] = roster[i + 1];
                continue;
            }
            newRoster[i] = roster[i];
        }
        size--;
        roster = newRoster;
        return true;
    }

    /**
     * Calculates the tuition due of all Students in the roster.
     */
    public void calculateAllTuition() {
        for (int i = 0; i < size; i++) {
            roster[i].tuitionDue();
        }
    }

    /**
     * Retrieves a Student whose Profile matches the incoming Student's.
     * This method expects an incoming Student whose Profile information
     * can be used to find and retrieve a Student from within the roster
     * with matching information in their Profile, if they exist. This
     * method's use case is for identifying the correct Student in the
     * roster when given a placeholder Student whose only meaningful
     * information is in their Profile attribute.
     *
     * @param student the Student whose Profile information the method uses
     *                to search the roster.
     * @return the Student in the roster that has matching Profile
     * information to the incoming Student.
     */
    public Student retrieveStudent(Student student) {
        int studentIndex = find(student);
        if (studentIndex == NOT_FOUND) {
            return null;
        }
        return roster[studentIndex];
    }

    /**
     * Generates a list of Students in the roster ordered by name.
     *
     * @return the String representation of the Students in the roster
     * ordered by name.
     */
    public String toStringByName() {
        if (size == 0) {
            return "Student roster is empty!";
        }
        sortByName();
        String rosterList = "* list of students ordered by name **\n";
        for (int i = 0; i < size; i++) {
            rosterList += roster[i].toString() + "\n";
        }
        rosterList += "* end of roster **";
        return rosterList;
    }

    /**
     * Generates a list of the roster's Students ordered by last payment date.
     * This method returns a list of Students who have nonempty last
     * payment dates.
     *
     * @return the String representation of the Students in the roster
     * ordered by last payment date.
     */
    public String toStringByPayment() {
        if (size == 0) {
            return "Student roster is empty!";
        }
        int numStudents = sortByPayment();
        String rosterList =
                "* list of students made payments ordered by payment date " +
                        "**\n";
        for (int i = size - numStudents; i < size; i++) {
            rosterList += roster[i].toString() + "\n";
        }
        rosterList += "* end of roster **";
        return rosterList;
    }

    /**
     * Helper method that sorts the roster's Students by name.
     */
    private void sortByName() {
        for (int i = 0; i < size - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < size; j++) {
                if (roster[j].getProfile()
                        .compareTo(roster[minIndex].getProfile()) ==
                        -1) {
                    minIndex = j;
                }
            }
            Student tempStudent = roster[minIndex];
            roster[minIndex] = roster[i];
            roster[i] = tempStudent;
        }
    }

    /**
     * Helper method that sorts the roster's Students by last payment date.
     *
     * @return the number of students with nonempty last payment dates.
     */
    private int sortByPayment() {
        int numStudents = 0;
        for (int i = 0; i < size; i++) {
            if (roster[i].getLastPaymentDate() != null) {
                numStudents++;
            }
        }
        Student[] madePayments = new Student[numStudents];
        Student[] didNotMakePayments = new Student[size - numStudents];
        int k = 0;
        int l = 0;
        for (int i = 0; i < size; i++) {
            if (roster[i].getLastPaymentDate() != null) {
                madePayments[k++] = roster[i];
            } else {
                didNotMakePayments[l++] = roster[i];
            }
        }
        for (int i = 0; i < numStudents - 1; i++) {
            int minIndex = i;
            if (madePayments[i].getLastPaymentDate() != null) {
                for (int j = i + 1; j < numStudents; j++) {
                    if (madePayments[j].getLastPaymentDate()
                            .compareTo(madePayments[minIndex].getLastPaymentDate()) ==
                            -1) {
                        minIndex = j;
                    }
                }
                Student tempStudent = madePayments[minIndex];
                madePayments[minIndex] = madePayments[i];
                madePayments[i] = tempStudent;
            }
        }
        int j = 0;
        for (int i = 0; i < size - numStudents; i++) {
            roster[j++] = didNotMakePayments[i];
        }
        for (int i = 0; i < numStudents; i++) {
            roster[j++] = madePayments[i];
        }
        return numStudents;
    }

    /**
     * Generates a String representation of this Roster object.
     *
     * @return the String representation of this Roster object, consisting
     * of the information of each Student in the Roster.
     */
    @Override
    public String toString() {
        if (size == 0) {
            return "Student roster is empty!";
        }
        String rosterList = "* list of students in the roster **\n";
        for (int i = 0; i < size; i++) {
            rosterList += roster[i].toString() + "\n";
        }
        rosterList += "* end of roster **";
        return rosterList;
    }
}
