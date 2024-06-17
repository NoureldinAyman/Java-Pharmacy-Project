package com.example.project;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import static com.example.project.MainMenuController.pharmacy;

public class TotalSalesController {

    @FXML
    private void calculateTotalSalesAndShow() {
        double totalSales = calculateTotalSales();
        showTotalSales(totalSales);
    }
    @FXML
    private double calculateTotalSales() {
        return pharmacy.getTotalSales();
    }

    private void showTotalSales(double totalSales) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Total Sales");
        alert.setHeaderText(null);
        alert.setContentText("Total Sales: $" + String.format("%.2f", totalSales));
        alert.showAndWait();
    }
}
