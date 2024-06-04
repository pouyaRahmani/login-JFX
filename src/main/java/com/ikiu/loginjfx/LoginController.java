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
import java.util.HashSet;
import java.util.Set;

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

        Set<User> users = readUsersFromFile("userdata.dat");
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                // Username and password match, login is successful
                return true;
            }
        }

        // No matching user found, login failed
        return false;
    }

    private Set<User> readUsersFromFile(String filename) {
        Set<User> users = new HashSet<>();
        File file = new File(filename);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
                users = (Set<User>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return users;
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
