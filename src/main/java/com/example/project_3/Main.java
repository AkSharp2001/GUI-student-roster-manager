package com.example.project_3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * A class that creates a scene and launches the project GUI
 *
 * @author Mervin James, Akshar Patel
 */
public class Main extends Application {
    /**
     * Method that creates the scene for the project GUI
     *
     * @param stage object that represents the primary window of the JavaFX
     *              application
     * @throws IOException exception caused by an input/output error
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(
                Main.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 600);
        stage.setTitle("Student Tuition Manager");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Main method that launches the project GUI
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }
}