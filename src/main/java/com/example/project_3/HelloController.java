package com.example.project_3;

        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.scene.control.Label;

public class HelloController {

    @FXML
    private Label yea;

    @FXML
    void onHelloButtonClick(ActionEvent event) {
        yea.setText("HI");
    }

}
