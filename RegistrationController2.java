package com.resq.controller;

import com.resq.dao.UserDAO;
import com.resq.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class RegistrationController2 {

    @FXML
    private TextField nameField, emailField, ageField, parentMobileField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private ComboBox<String> bloodGroupCombo;

    @FXML
    private Label statusLabel;

    private final UserDAO userDAO = new UserDAO();

    @FXML
    public void initialize() {
        bloodGroupCombo.getItems().addAll("A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-");
    }

    @FXML
    public void handleRegister() {
        String name = nameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        String bloodGroup = bloodGroupCombo.getValue();
        String parentMobile = parentMobileField.getText();
        int age;

        try {
            age = Integer.parseInt(ageField.getText());
        } catch (NumberFormatException e) {
            statusLabel.setText("⚠ Age must be a number!");
            statusLabel.setStyle("-fx-text-fill: red;");
            statusLabel.setVisible(true);
            return;
        }

        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || bloodGroup == null || parentMobile.isEmpty()) {
            statusLabel.setText("⚠ Please fill all fields!");
            statusLabel.setStyle("-fx-text-fill: red;");
            statusLabel.setVisible(true);
            return;
        }

        // ✅ User Object Creation
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setBloodGroup(bloodGroup);
        user.setAge(age);
        user.setParentMobile(parentMobile);

        // ✅ Save to Database
        boolean isSaved = userDAO.saveUser(user);

        if (isSaved) {
            statusLabel.setText("✅ Registration Successful!");
            statusLabel.setStyle("-fx-text-fill: green;");
            statusLabel.setVisible(true);
        } else {
            statusLabel.setText("❌ Error! Try again.");
            statusLabel.setStyle("-fx-text-fill: red;");
            statusLabel.setVisible(true);
        }
    }

    @FXML
    public void handleGoToLogin() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/login.fxml"));
            Stage stage = (Stage) nameField.getScene().getWindow();
            stage.setScene(new Scene(root, 400, 500));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
