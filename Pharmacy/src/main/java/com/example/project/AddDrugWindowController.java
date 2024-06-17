package com.example.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

import static com.example.project.MainMenuController.pharmacy;


public class AddDrugWindowController implements Initializable {

    @FXML
    public TextField presTextField;
    @FXML
    private TextField dName;
    @FXML
    private TextField dID;
    @FXML
    private TextField dPrice;
    @FXML
    private TextField dQuantity;
    @FXML
    private ChoiceBox<String> categories;

    private String[] catg = {"Cosmetic","Prescription Drug","Other"};

    public void addDrug(ActionEvent e) {
        String name = dName.getText();
        String idText = dID.getText();
        String priceText = dPrice.getText();
        String quantityText = dQuantity.getText();

        try {
            int id = Integer.parseInt(idText);
            double price = Double.parseDouble(priceText);
            int quantity = Integer.parseInt(quantityText);
            if (quantity > pharmacy.capacity) {
                warning("Quantity exceeds the maximum capacity");
                return;
            }
            else if (quantity <= 0) {
                warning("Quantity is not valid");
                return;
            } else if (price <= 0) {
                warning("Price is not valid");
                return;
            } else if (id <= 0) {
                warning("ID is not valid");
                return;
            } else if (pharmacy.checkID(id)) {
                warning("ID is already taken");
                return;
            }

            pharmacy.addDrug(name, id, price, categories, quantity, presTextField.getText());

            // Clear text fields
            dName.clear();
            dID.clear();
            dPrice.clear();
            dQuantity.clear();
            presTextField.clear();

            showAlert("A new drug added successfully with ID: " + id, Alert.AlertType.INFORMATION);
        } catch (NumberFormatException ex) {
            warning("Input is not valid");
        }
    }

    public void warning(String text) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("An Error Occurred");
        alert.setContentText(text);
        alert.setHeaderText("Error");
        alert.showAndWait();
    }

    private void showAlert(String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        categories.getItems().addAll(catg);
        categories.setValue(catg[0]);

        categories.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                checkIfPrescription(newValue);
            }
        });
    }

    public void checkIfPrescription(String item) {
        if (item.equals("Prescription Drug")) {
            presTextField.setDisable(false);
        }
        else {
            presTextField.setDisable(true);
        }
    }
}
