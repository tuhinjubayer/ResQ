package com.resq.controller;

import com.resq.utils.EmailUtil;
import com.resq.utils.SMSUtil;
import com.resq.utils.LocationUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class SOSController {

    @FXML
    private Text serviceLabel;

    @FXML
    private Button sosButton;

    private String selectedService = "";
    private long pressStartTime;

    // ✅ Previous page theke selected service set
    public void setSelectedService(String service) {
        this.selectedService = service;
        serviceLabel.setText("Service: " + service);
    }

    @FXML
    public void handleSOSPress() {
        pressStartTime = System.currentTimeMillis();
    }

    @FXML
    public void handleSOSRelease() {
        long holdTime = System.currentTimeMillis() - pressStartTime;

        if (holdTime >= 2000) {
            System.out.println("✅ SOS Triggered to " + selectedService + "! Sending Alerts...");

            // ✅ Get Location
            String location = LocationUtil.getLocation();
            System.out.println("📍 Location: " + location);

            // ✅ Email Alert
            String subject = "EMERGENCY SOS ALERT: " + selectedService;
            String body = "Emergency! Immediate help required for " + selectedService +
                    "\nLocation: " + location;
            String recipient = "nur.cnits@gmail.com"; // 👉 Tor real Gmail

            EmailUtil.sendEmail(recipient, subject, body);

            // ✅ SMS Alert
            String messageText = "SOS: " + selectedService + " | Location: " + location;
            String phoneNumber = "8801706027955"; // 👉 Tor real number

            SMSUtil.sendSMS(phoneNumber, messageText);

        } else {
            System.out.println("⚠ Hold for at least 2 seconds to trigger SOS.");
        }
    }
}
