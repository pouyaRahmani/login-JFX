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
    private TextField emailTextField;

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
        String email = emailTextField.getText();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("userdata.dat"))) {
            Object obj;
            while ((obj = ois.readObject()) != null) {
                if (obj instanceof User) {
                    User user = (User) obj;
                    if (user.getUsername().equals(username) && user.getEmail().equals(email)) {
                        // Username and email match, login is successful
                        return true;
                    }
                }
            }
        } catch (EOFException e) {
            // End of file reached
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        // No matching user found, login failed
        return false;
    }

    // Method to switch to the main scene
    private void switchToMainScene(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root, 800, 600);
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
