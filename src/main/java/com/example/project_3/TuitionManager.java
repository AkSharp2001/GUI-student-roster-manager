package com.example.project_3;

import java.util.Scanner;

/**
 * A class that runs the program and accepts a user's command line arguments.
 *
 * @author Mervin James, Akshar Patel
 */
public class TuitionManager {
    private static final int MIN_CREDITS = 3;
    private static final int MAX_CREDITS = 24;
    private static final int MIN_CREDITS_INTERNATIONAL = 12;
    private static final float MIN_FINANCIAL_AID = 0;
    private static final float MAX_FINANCIAL_AID = 10000;
    private static final int MIN_NUM_ARGUMENTS_FOR_VALID_COMMAND = 3;
    private static final int MIN_NUM_ARGUMENTS_FOR_CREDIT_HOURS = 4;
    private static final int MIN_NUM_ARGUMENTS_FOR_PAYMENT_AMOUNT = 4;
    private static final int MIN_NUM_ARGUMENTS_FOR_TRISTATE = 5;
    private static final int MIN_NUM_ARGUMENTS_FOR_INTERNATIONAL = 5;
    private static final int MIN_NUM_ARGUMENTS_FOR_PAYMENT_DATE = 5;

    /**
     * Runs the program and accepts a user's command line arguments.
     * The program takes in the user's input and generates the correct
     * output. The program will terminate once the user enters 'Q'.
     */
    public void run() {
        System.out.println("Tuition Manager starts running.");
        Student[] students = new Student[4];
        Roster roster = new Roster(students);
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            String[] splitInput = input.split(",");
            String command = splitInput[0];
            if (command.equals("Q")) {
                break;
            } else if (command.equals("AR") || command.equals("AN") ||
                    command.equals("AT") || command.equals("AI")) {
                addStudent(roster, splitInput);
            } else if (command.equals("R")) {
                removeStudent(roster, splitInput);
            } else if (command.equals("C")) {
                calculateAllTuition(roster);
            } else if (command.equals("T")) {
                payTuition(roster, splitInput);
            } else if (command.equals("S")) {
                setStudyAbroad(roster, splitInput);
            } else if (command.equals("F")) {
                setStudentFinancialAid(roster, splitInput);
            } else if (command.equals("P")) {
                printRoster(roster);
            } else if (command.equals("PN")) {
                printRosterByName(roster);
            } else if (command.equals("PT")) {
                printRosterByPayment(roster);
            } else if (command.equals("")) {
                System.out.println();
            } else {
                System.out.println("Command '" + command + "' not " +
                        "supported!");
            }
        }
        sc.close();
        System.out.println("\nTuition Manager terminated.");
    }

    /**
     * Adds a student to the roster based on the user's input.
     * This method validates the user's input and creates a new Student based
     * on the user's input into the command line. This method then prints
     * whether the user input is valid.
     *
     * @param roster     the roster of students.
     * @param splitInput the user's input from the command line separated
     *                   into fields for the album being added.
     */
    private void addStudent(Roster roster, String[] splitInput) {
        if (splitInput.length < MIN_NUM_ARGUMENTS_FOR_VALID_COMMAND) {
            System.out.println("Missing data in command line.");
            return;
        }
        String command = splitInput[0];
        String name = splitInput[1];
        Major major = checkMajor(splitInput[2]);
        if (major == null) {
            System.out.println(
                    "'" + splitInput[2] + "' is not a valid major.");
            return;
        }
        if (splitInput.length < MIN_NUM_ARGUMENTS_FOR_CREDIT_HOURS) {
            System.out.println("Credit hours missing.");
            return;
        }
        int credits = 0;
        try {
            credits = Integer.parseInt(splitInput[3]);
        } catch (NumberFormatException ex) {
            System.out.println("Invalid credit hours.");
            return;
        }
        if (credits < 0) {
            System.out.println("Credit hours cannot be negative.");
            return;
        } else if (credits < MIN_CREDITS) {
            System.out.println("Minimum credit hours is 3.");
            return;
        } else if (credits > MAX_CREDITS) {
            System.out.println("Credit hours exceed the maximum 24.");
            return;
        }
        boolean isAdded = false;
        switch (command) {
            case "AR" -> {
                Resident resident = new Resident(name, major, credits);
                isAdded = roster.add(resident);
            }
            case "AN" -> {
                NonResident nonResident = new NonResident(name, major,
                        credits);
                isAdded = roster.add(nonResident);
            }
            case "AT" -> {
                if (splitInput.length < MIN_NUM_ARGUMENTS_FOR_TRISTATE) {
                    System.out.println("Missing data in command line.");
                    return;
                }
                State state = checkState(splitInput[4]);
                if (state == null) {
                    System.out.println("Not part of the tri-state area.");
                    return;
                }
                float discount = 0;
                if (state == State.NY) {
                    discount = 4000;
                } else if (state == State.CT) {
                    discount = 5000;
                }
                TriState triState = new TriState(name, major, credits,
                        discount, state);
                isAdded = roster.add(triState);
            }
            case "AI" -> {
                if (splitInput.length < MIN_NUM_ARGUMENTS_FOR_INTERNATIONAL) {
                    System.out.println("Missing data in command line.");
                    return;
                }
                boolean isStudyAbroad = Boolean.parseBoolean(splitInput[4]);
                if (credits < MIN_CREDITS_INTERNATIONAL) {
                    System.out.println("International students must enroll" +
                            " at least 12 credits.");
                    return;
                }
                International international = new International(name, major,
                        credits, isStudyAbroad);
                isAdded = roster.add(international);
            }
        }
        if (isAdded) {
            System.out.println("Student added.");
        }
    }

    /**
     * Checks if a student's given major is a valid major.
     *
     * @param stringMajor the string version of major given by user
     * @return the major of a given student
     */
    private Major checkMajor(String stringMajor) {
        stringMajor = stringMajor.toUpperCase();
        return switch (stringMajor) {
            case "CS" -> Major.CS;
            case "IT" -> Major.IT;
            case "BA" -> Major.BA;
            case "EE" -> Major.EE;
            case "ME" -> Major.ME;
            default -> null;
        };
    }

    /**
     * Checks if a student's given state is a valid state.
     *
     * @param stringState the string version of state given by user
     * @return the state of a given student
     */
    private State checkState(String stringState) {
        stringState = stringState.toUpperCase();
        return switch (stringState) {
            case "NY" -> State.NY;
            case "CT" -> State.CT;
            default -> null;
        };
    }

    /**
     * Removes student from roster based on given name and major.
     *
     * @param roster     the roster of students.
     * @param splitInput the user's input from the command line separated
     *                   into fields for the album being added.
     */
    private void removeStudent(Roster roster, String[] splitInput) {
        if (splitInput.length < MIN_NUM_ARGUMENTS_FOR_VALID_COMMAND) {
            System.out.println("Missing data in command line.");
        }
        String name = splitInput[1];
        Major major = checkMajor(splitInput[2]);
        Student student = new Student(name, major);
        boolean isStudentRemoved = roster.remove(student);
        if (!isStudentRemoved) {
            System.out.println("Student is not in the roster.");
        } else {
            System.out.println("Student removed from the roster.");
        }
    }

    /**
     * Calculates the tuition for each student in the roster.
     *
     * @param roster the roster of students.
     */
    private void calculateAllTuition(Roster roster) {
        roster.calculateAllTuition();
        System.out.println("Calculation completed.");
    }

    /**
     * Sets study abroad status to the status given by the user.
     *
     * @param roster     the roster of students.
     * @param splitInput the user's input from the command line separated
     *                   into fields for the album being added.
     */
    private void setStudyAbroad(Roster roster, String[] splitInput) {
        String name = splitInput[1];
        Major major = checkMajor(splitInput[2]);
        boolean isStudyAbroad = Boolean.parseBoolean(splitInput[3]);
        Student student = roster.retrieveStudent(new Student(name, major));
        if (student == null) {
            System.out.println("Couldn't find the international student.");
        } else {
            ((International) student).setStudyAbroadStatus(isStudyAbroad);
        }
    }

    /**
     * Updates a student's tuition based on a given payment amount and
     * payment date.
     *
     * @param roster     the roster of students.
     * @param splitInput the user's input from the command line separated
     *                   into fields for the album being added.
     */
    private void payTuition(Roster roster, String[] splitInput) {
        if (splitInput.length < MIN_NUM_ARGUMENTS_FOR_VALID_COMMAND) {
            System.out.println("Missing data in command line.");
            return;
        }
        String name = splitInput[1];
        Major major = checkMajor(splitInput[2]);
        if (splitInput.length < MIN_NUM_ARGUMENTS_FOR_PAYMENT_AMOUNT) {
            System.out.println("Payment amount missing.");
            return;
        }
        float payment = Float.parseFloat(splitInput[3]);
        if (payment <= 0) {
            System.out.println("Invalid amount.");
            return;
        }
        Student student = roster.retrieveStudent(new Student(name, major));
        if (student == null) {
            System.out.println("Student not in the roster.");
            return;
        }
        float amountDue = student.getAmountDue();
        if (payment > amountDue) {
            System.out.println("Amount is greater than amount due.");
            return;
        }
        if (splitInput.length < MIN_NUM_ARGUMENTS_FOR_PAYMENT_DATE) {
            System.out.println("Payment date missing.");
            return;
        }
        Date paymentDate = new Date(splitInput[4]);
        if (!paymentDate.isValid()) {
            System.out.println("Payment date invalid.");
            return;
        }
        System.out.println("Payment applied.");
        student.payTuition(payment, paymentDate);
    }

    /**
     * Sets the financial aid for a Resident student given a financial aid
     * amount.
     *
     * @param roster     the roster of students.
     * @param splitInput the user's input from the command line separated
     *                   into fields for the album being added.
     */
    private void setStudentFinancialAid(Roster roster, String[] splitInput) {
        if (splitInput.length < MIN_NUM_ARGUMENTS_FOR_VALID_COMMAND) {
            System.out.println("Missing data in command line.");
            return;
        }
        String name = splitInput[1];
        Major major = checkMajor(splitInput[2]);
        if (splitInput.length < MIN_NUM_ARGUMENTS_FOR_PAYMENT_AMOUNT) {
            System.out.println("Missing the amount.");
            return;
        }
        float aidAmount = Float.parseFloat(splitInput[3]);
        Student student = roster.retrieveStudent(new Student(name, major));
        if (student == null) {
            System.out.println("Student not in the roster.");
            return;
        } else if (student.getCredits() < 12) {
            System.out.println("Parttime student doesn't qualify for the " +
                    "award.");
            return;
        } else if (!(student instanceof Resident)) {
            System.out.println("Not a resident student.");
            return;
        }
        Resident resident = (Resident) student;
        if (resident.getFinancialAid() != 0) {
            System.out.println("Awarded once already.");
            return;
        }
        if (aidAmount > MIN_FINANCIAL_AID &&
                aidAmount < MAX_FINANCIAL_AID) {
            resident.setFinancialAid(aidAmount);
            System.out.println("Tuition updated.");
        } else {
            System.out.println("Invalid amount.");
        }
    }

    /**
     * Prints all the students in the roster.
     *
     * @param roster the roster of students.
     */
    private void printRoster(Roster roster) {
        System.out.println(roster.toString());
    }

    /**
     * Prints all the students in the roster sorted in alphabetical order
     * by name.
     *
     * @param roster the roster of students.
     */
    private void printRosterByName(Roster roster) {
        System.out.println(roster.toStringByName());
    }

    /**
     * Prints the students who have made a payment by ascending payment date.
     *
     * @param roster the roster of students.
     */
    private void printRosterByPayment(Roster roster) {
        System.out.println(roster.toStringByPayment());
    }

}
