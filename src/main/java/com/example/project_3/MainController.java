package com.example.project_3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 * Controller class that specifies the attributes and actions for the GUI
 *
 * @author Mervin James, Akshar Patel
 */
public class MainController {
    private static final int MIN_CREDITS = 3;
    private static final int MAX_CREDITS = 24;
    private static final int MIN_CREDITS_INTERNATIONAL = 12;
    private static final float MIN_FINANCIAL_AID = 0;
    private static final float MAX_FINANCIAL_AID = 10000;
    private Roster roster;

    @FXML
    private RadioButton BAMajor;

    @FXML
    private RadioButton BAMajorRadioButton;

    @FXML
    private RadioButton BAMajorTuition;

    @FXML
    private RadioButton CSMajor;

    @FXML
    private RadioButton CSMajorRadioButton;

    @FXML
    private RadioButton CSMajorTuition;

    @FXML
    private RadioButton EEMajor;

    @FXML
    private RadioButton EEMajorRadioButton;

    @FXML
    private RadioButton EEMajorTuition;

    @FXML
    private RadioButton ITMajor;

    @FXML
    private RadioButton ITMajorRadioButton;

    @FXML
    private RadioButton ITMajorTuition;

    @FXML
    private RadioButton MEMajor;

    @FXML
    private RadioButton MEMajorRadioButton;

    @FXML
    private RadioButton MEMajorTuition;

    @FXML
    private Button addStudentButton;

    @FXML
    private MenuItem byName;

    @FXML
    private MenuItem byPaymentDate;

    @FXML
    private Button calculateTuitionButton;

    @FXML
    private RadioButton connecticutRadioButton;

    @FXML
    private TextField creditHoursTextfield;

    @FXML
    private MenuItem currentOrder;

    @FXML
    private MenuItem entireRoster;

    @FXML
    private TextField financialAidAmount;

    @FXML
    private RadioButton internationalRadioButton;

    @FXML
    private ToggleGroup majorSelection;

    @FXML
    private ToggleGroup majorSelectionGroup;

    @FXML
    private ToggleGroup majorSelectionTuition;

    @FXML
    private TextField nameTextField;

    @FXML
    private RadioButton newYorkRadioButton;

    @FXML
    private ToggleGroup nonResidencySelectionGroup;

    @FXML
    private RadioButton nonResidentRadioButton;

    @FXML
    private TextArea outputTextArea;

    @FXML
    private Button pay;

    @FXML
    private TextField paymentAmount;

    @FXML
    private DatePicker paymentDate;

    @FXML
    private Button removeStudentButton;

    @FXML
    private ToggleGroup residencyStatusSelectionGroup;

    @FXML
    private RadioButton residentRadioButton;

    @FXML
    private Button set;

    @FXML
    private MenuItem singleStudent;

    @FXML
    private TextField studentName;

    @FXML
    private TextField studentNameTuition;

    @FXML
    private CheckBox studyAbroadCheckbox;

    @FXML
    private RadioButton tristateRadioButton;

    @FXML
    private ToggleGroup tristateSelectionGroup;

    @FXML
    private TextField tuitionDueTextField;

    /**
     * Initializes the roster to size 4.
     */
    @FXML
    public void initialize() {
        Student[] students = new Student[4];
        this.roster = new Roster(students);
    }

    /**
     * Disables NonResident attributes when Resident button is clicked.
     *
     * @param event an event that occurs when a button is clicked.
     */
    @FXML
    void onResidentButtonClick(ActionEvent event) {
        tristateRadioButton.setDisable(true);
        connecticutRadioButton.setDisable(true);
        newYorkRadioButton.setDisable(true);
        internationalRadioButton.setDisable(true);
        studyAbroadCheckbox.setDisable(true);
        tristateRadioButton.setSelected(false);
        connecticutRadioButton.setSelected(false);
        newYorkRadioButton.setSelected(false);
        internationalRadioButton.setSelected(false);
        studyAbroadCheckbox.setSelected(false);
    }

    /**
     * Enables NonResident attributes when NonResident button is clicked.
     *
     * @param event an event that occurs when a button is clicked.
     */
    @FXML
    void onNonResidentButtonClick(ActionEvent event) {
        tristateRadioButton.setDisable(false);
        internationalRadioButton.setDisable(false);
    }

    /**
     * Enables state buttons and disables study abroad checkbox when
     * TriState button is clicked.
     *
     * @param event an event that occurs when a button is clicked.
     */
    @FXML
    void onTristateButtonClick(ActionEvent event) {
        connecticutRadioButton.setDisable(false);
        newYorkRadioButton.setDisable(false);
        studyAbroadCheckbox.setDisable(true);
        studyAbroadCheckbox.setSelected(false);
    }

    /**
     * Disables state buttons and enables study abroad checkbox when
     * International button is clicked.
     *
     * @param event an event that occurs when a button is clicked.
     */
    @FXML
    void onInternationalButtonClick(ActionEvent event) {
        connecticutRadioButton.setDisable(true);
        newYorkRadioButton.setDisable(true);
        connecticutRadioButton.setSelected(false);
        newYorkRadioButton.setSelected(false);
        studyAbroadCheckbox.setDisable(false);
    }

    /**
     * Adds a student to the roster based on the user's input.
     * This method validates the user's input and creates a new Student.
     * This method then prints whether the user input is valid.
     *
     * @param event an event that occurs when a button is clicked.
     */
    @FXML
    void onAddStudentButtonClick(ActionEvent event) {
        if (nameTextField.getText() == null ||
                nameTextField.getText().trim().isEmpty() ||
                majorSelectionGroup.getSelectedToggle() == null ||
                creditHoursTextfield.getText() == null ||
                residencyStatusSelectionGroup.getSelectedToggle() == null) {
            return;
        }
        String name = nameTextField.getText();
        RadioButton selectedMajorRadioButton =
                (RadioButton) majorSelectionGroup.getSelectedToggle();
        Major major = checkMajor(selectedMajorRadioButton.getText());
        if (creditHoursTextfield.getText().isEmpty()) {
            outputTextArea.appendText("Credit hours missing.\n");
            return;
        }
        int credits;
        try {
            credits = Integer.parseInt(creditHoursTextfield.getText());
        } catch (NumberFormatException e) {
            outputTextArea.appendText("Invalid credit hours.\n");
            return;
        }
        if (credits < 0) {
            outputTextArea.appendText("Credit hours cannot be negative.\n");
            return;
        } else if (credits < MIN_CREDITS) {
            outputTextArea.appendText("Minimum credit hours is 3.\n");
            return;
        } else if (credits > MAX_CREDITS) {
            outputTextArea.appendText("Credit hours exceed the maximum 24" +
                    ".\n");
            return;
        }

        String studentResidencyStatus = getStudentResidencyStatus();

        boolean isAdded = false;
        boolean inputError = false;
        switch (studentResidencyStatus) {
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
                RadioButton selectedTristateRadioButton =
                        (RadioButton) tristateSelectionGroup.getSelectedToggle();
                try {
                    State state =
                            checkState(selectedTristateRadioButton.getText());
                    float discount = 0;
                    if (state == State.NY) {
                        discount = 4000;
                    } else if (state == State.CT) {
                        discount = 5000;
                    }
                    TriState triState = new TriState(name, major, credits,
                            discount, state);
                    isAdded = roster.add(triState);
                } catch (NullPointerException e) {
                    inputError = true;
                    outputTextArea.appendText("Not part of the tri-state " +
                            "area.\n");
                }
            }
            case "AI" -> {
                boolean isStudyAbroad = studyAbroadCheckbox.isSelected();
                if (credits < MIN_CREDITS_INTERNATIONAL) {
                    outputTextArea.appendText("International students must"
                            + " enroll" + " at least 12 credits.\n");
                    return;
                }
                International international = new International(name, major,
                        credits, isStudyAbroad);
                isAdded = roster.add(international);
            }
        }
        if (isAdded) {
            outputTextArea.appendText("Student added.\n");
        }
        else if (!inputError){
            outputTextArea.appendText("Student is already in the roster.\n");
        }
    }

    /**
     * Removes student from roster based on given name and major.
     *
     * @param event an event that occurs when a button is clicked.
     */
    @FXML
    void onRemoveStudentButtonClick(ActionEvent event) {
        residentRadioButton.setSelected(false);
        nonResidentRadioButton.setSelected(false);
        tristateRadioButton.setSelected(false);
        connecticutRadioButton.setSelected(false);
        newYorkRadioButton.setSelected(false);
        internationalRadioButton.setSelected(false);
        studyAbroadCheckbox.setSelected(false);
        creditHoursTextfield.setText(null);
        if (nameTextField.getText() == null ||
                nameTextField.getText().trim().isEmpty() ||
                majorSelectionGroup.getSelectedToggle() == null) {
            outputTextArea.appendText("Missing data in form.\n");
            return;
        }
        String name = nameTextField.getText();
        RadioButton selectedMajorRadioButton =
                (RadioButton) majorSelectionGroup.getSelectedToggle();
        Major major = checkMajor(selectedMajorRadioButton.getText());
        Student student = new Student(name, major);
        boolean isStudentRemoved = roster.remove(student);
        if (!isStudentRemoved) {
            outputTextArea.appendText("Student is not in the roster.\n");
        } else {
            outputTextArea.appendText("Student removed from the roster.\n");
        }
    }

    /**
     * Calculates the tuition for an individual student based on the
     * given name and major.
     *
     * @param event an event that occurs when a button is clicked.
     */
    @FXML
    void onCalculateTuitionButtonClick(ActionEvent event) {
        residentRadioButton.setSelected(false);
        nonResidentRadioButton.setSelected(false);
        tristateRadioButton.setSelected(false);
        connecticutRadioButton.setSelected(false);
        newYorkRadioButton.setSelected(false);
        internationalRadioButton.setSelected(false);
        studyAbroadCheckbox.setSelected(false);
        creditHoursTextfield.setText(null);
        try {
            String name = nameTextField.getText();
            RadioButton selectedMajorRadioButton =
                    (RadioButton) majorSelectionGroup.getSelectedToggle();
            Major major = checkMajor(selectedMajorRadioButton.getText());
            Student student = roster.retrieveStudent(new Student(name, major));
            if (student == null) {
                outputTextArea.appendText("Student is not in the roster.\n");
                return;
            }
            student.tuitionDue();
            tuitionDueTextField.setText(String.valueOf(student.getAmountDue()));
            outputTextArea.appendText("Calculation completed.\n");
        } catch (NullPointerException e) {
            outputTextArea.appendText("Missing data in form.\n");
        }
    }

    /**
     * Updates a student's tuition based on a given payment amount and
     * payment date.
     *
     * @param event an event that occurs when a button is clicked.
     */
    @FXML
    void onPayButtonClick(ActionEvent event) {
        if (studentName.getText() == null ||
                studentName.getText().trim().isEmpty() ||
                majorSelection.getSelectedToggle() == null) {
            return;
        }
        String name = studentName.getText();
        RadioButton selectedMajorRadioButton =
                (RadioButton) majorSelection.getSelectedToggle();
        Major major = checkMajor(selectedMajorRadioButton.getText());

        float payment = 0;
        try {
            payment = Float.parseFloat(paymentAmount.getText());
        } catch (NumberFormatException ignored) {
        }
        if (payment <= 0) {
            outputTextArea.appendText("Invalid amount.\n");
            return;
        }
        Student student = roster.retrieveStudent(new Student(name, major));
        if (student == null) {
            outputTextArea.appendText("Student is not in the roster.\n");
            return;
        }
        float amountDue = student.getAmountDue();
        if (payment > amountDue) {
            outputTextArea.appendText("Amount is greater than amount due.\n");
            return;
        }
        try {
            Date dateOfPayment = new Date((paymentDate.getValue()).toString());
            if (!dateOfPayment.isValid()) {
                outputTextArea.appendText("Payment date invalid.\n");
                return;
            }
            student.payTuition(payment, dateOfPayment);
            outputTextArea.appendText("Payment applied.\n");
        } catch (NullPointerException e) {
            outputTextArea.appendText("Payment date invalid.\n");
        }
    }

    /**
     * Sets the financial aid for a Resident student given a financial aid
     * amount.
     *
     * @param event an event that occurs when a button is clicked.
     */
    @FXML
    void onSetButtonClick(ActionEvent event) {
        if (studentName.getText() == null ||
                studentName.getText().trim().isEmpty()) {
            return;

        } else if (majorSelection.getSelectedToggle() == null) {
            return;
        }

        String name = studentName.getText();
        RadioButton selectedMajorRadioButton =
                (RadioButton) majorSelection.getSelectedToggle();
        Major major = checkMajor(selectedMajorRadioButton.getText());

        float aidAmount = 0;
        try {
            aidAmount = Float.parseFloat(financialAidAmount.getText());
        } catch (NumberFormatException ignored) {
        }

        Student student = roster.retrieveStudent(new Student(name, major));
        if (student == null) {
            outputTextArea.appendText("Student is not in the roster.\n");
            return;
        } else if (student.getCredits() < 12) {
            outputTextArea.appendText("Parttime student doesn't qualify for " +
                    "the award\n");
            return;
        } else if (!(student instanceof Resident)) {
            outputTextArea.appendText("Not a resident student.\n");
            return;
        }
        Resident resident = (Resident) student;
        if (resident.getFinancialAid() != 0) {
            outputTextArea.appendText("Awarded once already.\n");
            return;
        }
        if (aidAmount > MIN_FINANCIAL_AID && aidAmount < MAX_FINANCIAL_AID) {
            resident.setFinancialAid(aidAmount);
            outputTextArea.appendText("Tuition updated.\n");
        } else {
            outputTextArea.appendText("Invalid amount.\n");
        }
    }

    /**
     * Calculates the tuition for every student in the roster.
     *
     * @param event an event that occurs when a button is clicked.
     */
    @FXML
    void onEntireRosterButtonClick(ActionEvent event) {
        roster.calculateAllTuition();
        outputTextArea.appendText("Calculation completed.\n");
    }

    /**
     * Calculates the tuition for a single student given the name and major.
     *
     * @param event an event that occurs when a button is clicked.
     */
    @FXML
    void onSingleStudentButtonClick(ActionEvent event) {
        try {
            String name = studentNameTuition.getText();
            RadioButton selectedMajorRadioButton =
                    (RadioButton) majorSelectionTuition.getSelectedToggle();
            Major major = checkMajor(selectedMajorRadioButton.getText());
            Student student = roster.retrieveStudent(new Student(name, major));
            student.tuitionDue();
            outputTextArea.appendText("Calculation completed.\n");
        } catch (NullPointerException ignored) {
        }
    }

    /**
     * Prints all the students in the roster in the current order.
     *
     * @param event an event that occurs when a button is clicked.
     */
    @FXML
    void onCurrentOrderButtonClick(ActionEvent event) {
        outputTextArea.appendText(roster.toString() + "\n");
    }

    /**
     * Prints all the students in the roster sorted in alphabetical order
     * by name.
     *
     * @param event an event that occurs when a button is clicked.
     */
    @FXML
    void onByNameButtonClick(ActionEvent event) {
        outputTextArea.appendText(roster.toStringByName() + "\n");

    }

    /**
     * Prints the students who have made a payment by ascending payment date.
     *
     * @param event an event that occurs when a button is clicked.
     */
    @FXML
    void onByPaymentDateButtonClick(ActionEvent event) {
        outputTextArea.appendText(roster.toStringByPayment() + "\n");
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
     * Checks the residency status of a student based on radio button
     * selection.
     *
     * @return residency status as a String
     */
    private String getStudentResidencyStatus() {
        RadioButton selectedResidencyRadioButton =
                (RadioButton) residencyStatusSelectionGroup.getSelectedToggle();
        String selectedResidency = selectedResidencyRadioButton.getText();
        if (selectedResidency.equals("Resident")) {
            return "AR";
        }
        if (nonResidencySelectionGroup.getSelectedToggle() == null) {
            return "AN";
        }
        RadioButton selectedNonResidencyRadioButton =
                (RadioButton) nonResidencySelectionGroup.getSelectedToggle();
        String selectedNonResidency =
                selectedNonResidencyRadioButton.getText();
        if (selectedNonResidency.equals("Tristate")) {
            return "AT";
        }
        return "AI";
    }

}
