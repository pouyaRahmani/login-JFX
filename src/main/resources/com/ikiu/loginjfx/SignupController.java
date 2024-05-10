package com.ikiu.loginjfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void onSubmitButtonClick(ActionEvent actionEvent) {
        // Validate the form data
        if (validateForm()) {
            // If validation is successful, save the data and close the window
            saveFormData();
            stage.close();
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

    private void saveFormData() {
        // Save form data to a file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("userdata.txt", true))) {
            writer.write(String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s%n",
                    firstNameTextField.getText(), lastNameTextField.getText(), ageTextField.getText(),
                    birthdayTextField.getText(), genderTextField.getText(), civilTextField.getText(),
                    countryTextField.getText(), contactTextField.getText(), emailTextField.getText(),
                    usernameTextField.getText(), passwordTextField.getText(), compasswordTextField.getText()));
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
    }
}
