package com.example.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import static com.example.project.MainMenuController.pharmacy;

public class RemoveDrugController {

    @FXML
    private TextField removeID;

    public void removeDrug(ActionEvent event) {
        String idText = removeID.getText();

        try {
            int id = Integer.parseInt(idText);

            boolean removed = pharmacy.drugs.removeIf(drug -> drug.getid() == id);
            if (removed) {
                showAlert("Drug removed successfully", Alert.AlertType.INFORMATION);
            } else {
                showAlert("ID does not exist. Please try another one.", Alert.AlertType.WARNING);
            }

        } catch (NumberFormatException e) {
            showAlert("Invalid ID format: " + idText, Alert.AlertType.ERROR);
        }
    }

    private void showAlert(String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
