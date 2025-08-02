package com.resq.controller;

import com.resq.dao.UserDAO;
import com.resq.model.User;
import com.resq.utils.EmailUtil;
import com.resq.utils.SMSUtil;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.List;

public class BloodRequestController {

    @FXML
    private ComboBox<String> bloodGroupCombo;

    @FXML
    private TextField locationField; // ✅ User Location only

    @FXML
    private Label statusLabel;

    private final UserDAO userDAO = new UserDAO();

    @FXML
    public void initialize() {
        bloodGroupCombo.getItems().addAll("A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-");
    }

    @FXML
    public void handleBloodRequest() {
        String bloodGroup = bloodGroupCombo.getValue();
        String location = locationField.getText();

        if (bloodGroup == null || location.isEmpty()) {
            statusLabel.setText("⚠ Please select blood group and location!");
            statusLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        // ✅ Same Blood Group এর সব User Database থেকে আনা
        List<User> users = userDAO.getUsersByBloodGroup(bloodGroup);

        if (users.isEmpty()) {
            statusLabel.setText("❌ No users found with this blood group!");
            statusLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        // ✅ SMS + Email সবার কাছে পাঠানো
        String message = "🩸 Emergency Blood Request!\n" +
                "Blood Group: " + bloodGroup + "\n" +
                "Location: " + location;

        for (User user : users) {
            // Email পাঠানো
            EmailUtil.sendEmail(user.getEmail(), "🩸 Blood Request", message);

            // SMS পাঠানো (Parent Mobile ব্যবহার হবে)
            if (user.getParentMobile() != null && !user.getParentMobile().isEmpty()) {
                SMSUtil.sendSMS(user.getParentMobile(), message);
            }
        }

        statusLabel.setText("✅ Blood Request Sent to " + users.size() + " users!");
        statusLabel.setStyle("-fx-text-fill: green;");
    }
}
