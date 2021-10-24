package com.example.project_3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
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
    private RadioButton CSMajor;

    @FXML
    private RadioButton CSMajorRadioButton;

    @FXML
    private RadioButton EEMajor;

    @FXML
    private RadioButton EEMajorRadioButton;

    @FXML
    private RadioButton ITMajor;

    @FXML
    private RadioButton ITMajorRadioButton;

    @FXML
    private RadioButton MEMajor;

    @FXML
    private RadioButton MEMajorRadioButton;

    @FXML
    private Button addStudentButton;

    @FXML
    private Button calculateTuitionButton;

    @FXML
    private RadioButton connecticutRadioButton;

    @FXML
    private TextField creditHoursTextfield;

    @FXML
    private TextField financialAidAmount;

    @FXML
    private RadioButton internationalRadioButton;

    @FXML
    private ToggleGroup majorSelection;

    @FXML
    private ToggleGroup majorSelectionGroup;

    @FXML
    private TextField nameTextField;

    @FXML
    private RadioButton newYorkRadioButton11;

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
    private TextField studentName;

    @FXML
    private CheckBox studyAbroadCheckbox;

    @FXML
    private RadioButton tristateRadioButton1;

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
    void onAddStudentButtonClick(ActionEvent event) {
        if (nameTextField.getText() == null ||
                nameTextField.getText().trim().isEmpty()) {

        } else if (
                majorSelectionGroup.getSelectedToggle() == null) {
        }

        String name = nameTextField.getText();
        RadioButton selectedMajorRadioButton =
                (RadioButton) majorSelectionGroup.getSelectedToggle();
        Major major = checkMajor(selectedMajorRadioButton.getText());
        int credits = Integer.parseInt(creditHoursTextfield.getText());
        String studentResidencyStatus = getStudentResidencyStatus();


        boolean isAdded = false;
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
//                State state = checkState(splitInput[4]);
                RadioButton selectedTristateRadioButton =
                        (RadioButton) tristateSelectionGroup.getSelectedToggle();
                State state =
                        checkState(selectedTristateRadioButton.getText());
//                if (state == null) {
//                    System.out.println("Not part of the tri-state area.");
//                    return;
//                }
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
                boolean isStudyAbroad = studyAbroadCheckbox.isSelected();
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


    @FXML
    void onCalculateTuitionButtonClick(ActionEvent event) {

    }

    @FXML
    void onRemoveStudentButtonClick(ActionEvent event) {

    }

    @FXML
    void onPayButtonClick(ActionEvent event) {
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

        float payment = 0;
        try {
            payment = Float.parseFloat(paymentAmount.getText());
        } catch (NumberFormatException e) {
//            e.printStackTrace();
        }
        if (payment <= 0) {
            outputTextArea.setText("Invalid amount.");
//            System.out.println("Invalid amount.");
            return;
        }
        Student student = roster.retrieveStudent(new Student(name, major));
        if (student == null) {
            outputTextArea.setText("Student not in the roster.");
//            System.out.println("Student not in the roster.");
            return;
        }
        float amountDue = student.getAmountDue();
        if (payment > amountDue) {
            outputTextArea.setText("Amount is greater than amount due.");
//            System.out.println("Amount is greater than amount due.");
            return;
        }
        Date dateOfPayment = new Date((paymentDate.getValue()).toString());
        if (!dateOfPayment.isValid()) {
            outputTextArea.setText("Payment date invalid.");
//            System.out.println("Payment date invalid.");
            return;
        }

        outputTextArea.setText("Payment applied.");
//        outputTextArea.setText((paymentDate.getValue()).toString());

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
            outputTextArea.setText("Student not in the roster.");
//            System.out.println("Student not in the roster.");
            return;
        } else if (student.getCredits() < 12) {
            outputTextArea.setText("Parttime student doesn't qualify for " +
                    "the award");
//            System.out.println("Parttime student doesn't qualify for the " +
//                    "award.");
            return;
        } else if (!(student instanceof Resident)) {
            outputTextArea.setText("Not a resident student.");
//            System.out.println("Not a resident student.");
            return;
        }
        Resident resident = (Resident) student;
        if (resident.getFinancialAid() != 0) {
            outputTextArea.setText("Awarded once already.");
//            System.out.println("Awarded once already.");
            return;
        }
        if (aidAmount > MIN_FINANCIAL_AID && aidAmount < MAX_FINANCIAL_AID) {
            resident.setFinancialAid(aidAmount);
            outputTextArea.setText("Tuition updated.");
//            System.out.println("Tuition updated.");
        } else {
            outputTextArea.setText("Invalid amount.");
//            System.out.println("Invalid amount.");
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
