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

    @FXML
    public void initialize() {
        Student[] students = new Student[4];
        this.roster = new Roster(students);
    }

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

    @FXML
    void onNonResidentButtonClick(ActionEvent event) {
        tristateRadioButton.setDisable(false);
        internationalRadioButton.setDisable(false);
    }

    @FXML
    void onTristateButtonClick(ActionEvent event) {
        connecticutRadioButton.setDisable(false);
        newYorkRadioButton.setDisable(false);
        studyAbroadCheckbox.setDisable(true);
        studyAbroadCheckbox.setSelected(false);
    }

    @FXML
    void onInternationalButtonClick(ActionEvent event) {
        connecticutRadioButton.setDisable(true);
        newYorkRadioButton.setDisable(true);
        connecticutRadioButton.setSelected(false);
        newYorkRadioButton.setSelected(false);
        studyAbroadCheckbox.setDisable(false);
    }

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
        int credits = 0;
        try {
            credits = Integer.parseInt(creditHoursTextfield.getText());
        } catch (NumberFormatException e) {
            outputTextArea.appendText("Invalid credit hours.\n");
            return;
//            e.printStackTrace();
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

    @FXML
    void onRemoveStudentButtonClick(ActionEvent event) {
        if (nameTextField.getText() == null ||
                nameTextField.getText().trim().isEmpty() ||
                majorSelectionGroup.getSelectedToggle() == null) {
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

    @FXML
    void onCalculateTuitionButtonClick(ActionEvent event) {
        try {
            String name = nameTextField.getText();
            RadioButton selectedMajorRadioButton =
                    (RadioButton) majorSelectionGroup.getSelectedToggle();
            Major major = checkMajor(selectedMajorRadioButton.getText());
            Student student = roster.retrieveStudent(new Student(name, major));
            student.tuitionDue();
            tuitionDueTextField.setText(String.valueOf(student.getAmountDue()));
            outputTextArea.appendText("Calculation completed.\n");
        } catch (NullPointerException e) {
//            e.printStackTrace();
        }
    }

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
        } catch (NumberFormatException e) {
//            e.printStackTrace();
        }
        if (payment <= 0) {
            outputTextArea.appendText("Invalid amount.\n");
//            System.out.println("Invalid amount.");
            return;
        }
        Student student = roster.retrieveStudent(new Student(name, major));
        if (student == null) {
            outputTextArea.appendText("Student not in the roster.\n");
//            System.out.println("Student not in the roster.");
            return;
        }
        float amountDue = student.getAmountDue();
        if (payment > amountDue) {
            outputTextArea.appendText("Amount is greater than amount due.\n");
//            System.out.println("Amount is greater than amount due.");
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
        } catch (NumberFormatException e) {
//            e.printStackTrace();
        }

        Student student = roster.retrieveStudent(new Student(name, major));
        if (student == null) {
            outputTextArea.appendText("Student not in the roster.\n");
//            System.out.println("Student not in the roster.");
            return;
        } else if (student.getCredits() < 12) {
            outputTextArea.appendText("Parttime student doesn't qualify for " +
                    "the award\n");
//            System.out.println("Parttime student doesn't qualify for the " +
//                    "award.");
            return;
        } else if (!(student instanceof Resident)) {
            outputTextArea.appendText("Not a resident student.\n");
//            System.out.println("Not a resident student.");
            return;
        }
        Resident resident = (Resident) student;
        if (resident.getFinancialAid() != 0) {
            outputTextArea.appendText("Awarded once already.\n");
//            System.out.println("Awarded once already.");
            return;
        }
        if (aidAmount > MIN_FINANCIAL_AID && aidAmount < MAX_FINANCIAL_AID) {
            resident.setFinancialAid(aidAmount);
            outputTextArea.appendText("Tuition updated.\n");
//            System.out.println("Tuition updated.");
        } else {
            outputTextArea.appendText("Invalid amount.\n");
//            System.out.println("Invalid amount.");
        }
    }

    @FXML
    void onEntireRosterButtonClick(ActionEvent event) {
        roster.calculateAllTuition();
        outputTextArea.appendText("Calculation completed.\n");
    }

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
        } catch (NullPointerException e) {
//            e.printStackTrace();
        }
    }

    @FXML
    void onCurrentOrderButtonClick(ActionEvent event) {
        outputTextArea.appendText(roster.toString() + "\n");
    }

    @FXML
    void onByNameButtonClick(ActionEvent event) {
        outputTextArea.appendText(roster.toStringByName() + "\n");

    }

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
