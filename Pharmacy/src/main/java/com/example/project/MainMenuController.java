package com.example.project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class MainMenuController {
    protected static Pharmacy pharmacy;

    @FXML
    private BorderPane bp;

    @FXML
    private AnchorPane ap;

    @FXML
    private Button addCapacityBtn;

    @FXML
    private TextField capacityTextField;

    @FXML
    private Button home;
    @FXML
    private Button addDrug;
    @FXML
    private Button removeDrug;
    @FXML
    private Button placeOrder;
    @FXML
    private Button sales;

    @FXML
    private void switchToMainMenu(MouseEvent event) {
        String input = capacityTextField.getText();

        // Check if the input is a valid integer
        try {
            int capacity = Integer.parseInt(input);
            if (capacity <= 0) {
                showAlert("Error", "Invalid Capacity", "Capacity must be a positive integer.");
                return;
            }

            // Create the pharmacy
            pharmacy = new Pharmacy(capacity);

            // Enable the buttons
            home.setDisable(false);
            addDrug.setDisable(false);
            removeDrug.setDisable(false);
            placeOrder.setDisable(false);
            sales.setDisable(false);

            switchPage("Home.fxml");
        } catch (NumberFormatException e) {
            // Display error message if input is not an integer
            showAlert("Error", "Invalid Input", "Please enter a valid integer for capacity.");
        }

    }

    private void showAlert(String title, String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    @FXML
    private void switchToAddDrug(MouseEvent event) {
        switchPage("AddDrugWindow.fxml");
    }

    @FXML
    private void switchToRemoveDrug(MouseEvent event) {
        switchPage("RemoveDrug.fxml");
    }

    @FXML
    private void switchToPlaceOrder(MouseEvent event) {
        switchPage("PlaceOrder.fxml");
    }
    @FXML
    private void switchTosales(MouseEvent event) {
        switchPage("TotalSales.fxml");
    }

    @FXML
    private void switchPage(String filename) {
        Parent root = null;

        try {
            root = FXMLLoader.load(getClass().getResource(filename));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        bp.setCenter(root);
    }

    @FXML
    public void exit(MouseEvent e) {
        System.exit(0);
    }
}
