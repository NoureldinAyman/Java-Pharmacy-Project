package com.example.project;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

import static com.example.project.MainMenuController.pharmacy;

public class PlaceOrderController implements Initializable {

    @FXML
    public Button searchBtn;
    @FXML
    public Button buyBtn;
    @FXML
    private ComboBox<String> placeOrderCombo;
    @FXML
    private Label prescriptionLabel;
    @FXML
    private Label priceLabel;

    @FXML
    private Label statusLabel;


    public void initialize(URL url, ResourceBundle resourceBundle) {
        boolean b = placeOrderCombo.getItems().addAll(pharmacy.getDrugsNames());
    }

    public int searchDrug() {
        String selectedDrug = placeOrderCombo.getValue();

        if (selectedDrug == null) {
            return -1;
        }

        int drugID = -1;
        try {
            // Attempt to parse the input as an integer (drug ID)
            int enteredID = Integer.parseInt(selectedDrug);

            // Search for the drug by ID
            drugID = pharmacy.searchDrugID(enteredID);
            if (drugID != -1) {
                com.example.project.Drug drug = pharmacy.drugs.get(drugID);

                String price = String.valueOf(drug.getprice());
                priceLabel.setText(price);

                if (drug.getcategory().equals("Prescription Drug")) {
                    System.out.println("Prescription: " + drug.getPrescription());
                    prescriptionLabel.setText("Prescription: " + drug.getPrescription());
                }
            }
        } catch (NumberFormatException e) {

        }

        drugID = pharmacy.searchDrugName(selectedDrug);
        if (drugID == -1) {
            statusLabel.setText("Drug Not found");
            return -1;
        }
        else {
            com.example.project.Drug drug = pharmacy.drugs.get(drugID);

            String price = String.valueOf(drug.getprice() * 1.2);
            priceLabel.setText(price);

            if (drug.getcategory().equals("Prescription Drug")) {
                System.out.println("Prescription: " + drug.getPrescription());
                prescriptionLabel.setText("Prescription: " + drug.getPrescription());
            }
        }
        return drugID;
    }

    public void buy() {
        try {
            int drugIndex = searchDrug();
            if (drugIndex != -1) {
                boolean purchaseSuccess = pharmacy.buyDrug(drugIndex);

                if (purchaseSuccess) {
                    // Update the status label to show success message
                    statusLabel.setText("Successfully Purchased!");
                }
                prescriptionLabel.setText("");
            } else {
                // If drug is out of quantity
                statusLabel.setText("Out of stock");
            }
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }
}
