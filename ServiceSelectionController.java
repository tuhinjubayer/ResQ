package com.resq.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ServiceSelectionController {

    @FXML
    private Button ambulanceButton; // Stage nite use hobe

    // ✅ Common method to open SOS Page
    private void openSOSPage(String service) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sos_page.fxml"));
            Parent root = loader.load();

            // SOSController e service pass korbo
            SOSController controller = loader.getController();
            controller.setSelectedService(service);

            Stage stage = (Stage) ambulanceButton.getScene().getWindow();
            stage.setScene(new Scene(root, 400, 500));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void openAmbulanceSOS() {
        openSOSPage("Ambulance");
    }

    @FXML
    public void openPoliceSOS() {
        openSOSPage("Police");
    }

    @FXML
    public void openFireSOS() {
        openSOSPage("Fire Service");
    }

    // ✅ Open Emergency Blood Request Page
    @FXML
    public void openBloodRequest() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/blood_request.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ambulanceButton.getScene().getWindow();
            stage.setScene(new Scene(root, 400, 400));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
