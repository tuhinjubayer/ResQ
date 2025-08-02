package com.resq.controller;

import com.resq.dao.UserDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField usernameField; // Email field

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label errorLabel;

    private final UserDAO userDAO = new UserDAO();

    @FXML
    private void handleLogin() {
        String email = usernameField.getText();
        String password = passwordField.getText();

        // ✅ Empty Field Check
        if (email.isEmpty() || password.isEmpty()) {
            errorLabel.setText("⚠ Please fill all fields!");
            errorLabel.setStyle("-fx-text-fill: red;");
            errorLabel.setVisible(true);
            return;
        }

        // ✅ Database Validation
        if (userDAO.validateLogin(email, password)) {
            errorLabel.setText("✅ Login Successful!");
            errorLabel.setStyle("-fx-text-fill: green;");
            errorLabel.setVisible(true);

            System.out.println("✅ Login Successful!");

            // ✅ Redirect to Service Selection Page
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/service_selection.fxml"));
                Stage stage = (Stage) usernameField.getScene().getWindow();
                stage.setScene(new Scene(root, 400, 500));
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            errorLabel.setText("❌ Invalid Email or Password!");
            errorLabel.setStyle("-fx-text-fill: red;");
            errorLabel.setVisible(true);
        }
    }

    @FXML
    public void handleGoToSignUp() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/registration_form.fxml"));
            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(new Scene(root, 400, 500));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
