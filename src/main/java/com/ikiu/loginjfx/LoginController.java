package com.ikiu.loginjfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;

public class LoginController {
    @FXML
    private TextField userNameTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    public void goToSignUp(ActionEvent event) throws IOException {
        // Load the sign-up scene
        Parent root = FXMLLoader.load(getClass().getResource("signup.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void onSignInButtonClick(ActionEvent actionEvent) {
        if (validateLogin()) {
            // If login is successful, switch to the main scene
            switchToMainScene(actionEvent);
        } else {
            // If login fails, show an error message
            showAlert(Alert.AlertType.ERROR, "Error", "Invalid credentials. Please try again.");
        }
    }

    // Method to validate login credentials
    private boolean validateLogin() {
        String username = userNameTextField.getText();
        String password = passwordTextField.getText();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("userdata.dat"))) {
            while (true) {
                Object obj = ois.readObject();
                if (obj instanceof User) {
                    User user = (User) obj;
                    if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                        // Username and password match, login is successful
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            // Handle any exceptions, such as file not found or EOFException
            e.printStackTrace(); // Print the stack trace for debugging
        }
        // No matching user found or an error occurred, login failed
        return false;
    }

    // Method to switch to the main scene
    private void switchToMainScene(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            currentStage.setScene(scene);
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // Method to show an alert
    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
