package com.ikiu.loginjfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SignupController {

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private TextField ageTextField;

    @FXML
    private TextField birthdayTextField;

    @FXML
    private TextField genderTextField;

    @FXML
    private TextField civilTextField;

    @FXML
    private TextField countryTextField;

    @FXML
    private TextField contactTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField usernameTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private TextField compasswordTextField;
    @FXML
    private ComboBox <String> roleComboBox;
    private String role;
    private Stage stage;
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void onSubmitButtonClick(ActionEvent actionEvent) {
        // Validate the form data
        if (validateForm()) {
            // If validation is successful, create a User object and save the data
            User user = createUserFromForm();
            saveUser(user);
            switchToLoginScene(actionEvent);
        } else {
            // If validation fails, show an error message
            showAlert(Alert.AlertType.ERROR, "Error", "Please fill in all the required fields.");
        }
    }

    public void onClearButtonClick(ActionEvent actionEvent) {
        // Clear all text fields
        clearFields();
    }

    private boolean validateForm() {
        // Check if any of the text fields are empty
        return !isEmpty(firstNameTextField) && !isEmpty(lastNameTextField) && !isEmpty(ageTextField)
                && !isEmpty(birthdayTextField) && !isEmpty(genderTextField) && !isEmpty(civilTextField)
                && !isEmpty(countryTextField) && !isEmpty(contactTextField) && !isEmpty(emailTextField)
                && !isEmpty(usernameTextField) && !isEmpty(passwordTextField) && !isEmpty(compasswordTextField);
    }

    private boolean isEmpty(TextField textField) {
        // Check if a text field is empty
        return textField.getText().trim().isEmpty();
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        // Show an alert
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }


    private User createUserFromForm() {
        // Create a User object from the form data
        User user = new User();
        user.setFirstName(firstNameTextField.getText());
        user.setLastName(lastNameTextField.getText());
        user.setAge(Integer.parseInt(ageTextField.getText()));
        user.setBirthday(birthdayTextField.getText());
        user.setGender(genderTextField.getText());
        user.setCivilStatus(civilTextField.getText());
        user.setCountry(countryTextField.getText());
        user.setContact(contactTextField.getText());
        user.setEmail(emailTextField.getText());
        user.setUsername(usernameTextField.getText());
        user.setPassword(passwordTextField.getText());
        user.setRole(role);
        return user;
    }

    @FXML
    public void onRoleComboBoxAction(ActionEvent event) {
        role = roleComboBox.getValue();
    }


    private void saveUser(User user) {
        // Save user data to a file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("userdata.dat", true))) {
            writer.write(user.toString() + "\n");
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to save data.");
        }
    }

    private void clearFields() {
        // Clear all text fields
        firstNameTextField.clear();
        lastNameTextField.clear();
        ageTextField.clear();
        birthdayTextField.clear();
        genderTextField.clear();
        civilTextField.clear();
        countryTextField.clear();
        contactTextField.clear();
        emailTextField.clear();
        usernameTextField.clear();
        passwordTextField.clear();
        compasswordTextField.clear();
        roleComboBox.getSelectionModel().clearSelection();
    }

    private void switchToLoginScene(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 456, 374);
            Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            currentStage.setScene(scene);
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
