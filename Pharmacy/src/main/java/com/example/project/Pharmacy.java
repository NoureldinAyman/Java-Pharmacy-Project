package com.example.project;

import javafx.scene.control.ChoiceBox;

import java.util.ArrayList;

public class Pharmacy {
    protected ArrayList<com.example.java_fx_project.Drug> drugs;
    protected int capacity;
    public double totalSales;


    public Pharmacy(int capacity) {
        this.drugs = new ArrayList<>();
        this.capacity = capacity;
        this.totalSales = 0.0;

    }

    // Getters
    public ArrayList<com.example.java_fx_project.Drug> getDrugs() {
        return this.drugs;
    }

    public String[] getDrugsNames() {
        String[] names = new String[drugs.size()];

        for (int i = 0; i < drugs.size(); i++) {
            names[i] = drugs.get(i).getDrugname();
        }
        return names;
    }

    public boolean checkID(int givenId) {
        for (com.example.java_fx_project.Drug drug : drugs) {
            if (drug.getid() == givenId) {
                return true;
            }
        }
        return false;
    }

    public double getTotalSales() {
        return totalSales;
    }

    public void addDrug(String name, int ID, double price, ChoiceBox category, int quantity, String prescription) {
        if (quantity > this.capacity) {
            System.out.println("Error: quantity exceeds maximum capacity");
            return;
        }
        com.example.java_fx_project.Drug drug = new com.example.java_fx_project.Drug(name, ID, price, category, quantity, prescription);
        this.drugs.add(drug);
        this.capacity--;
    }

    public int searchDrugID(int ID) {
        // Returns id of a given drug id
        for (int i = 0; i < drugs.size(); i++) {
            com.example.java_fx_project.Drug drug = drugs.get(i);
            if (drug.getid() == ID) {
                return i;
            }
        }
        return -1;
    }

    public int searchDrugName(String drugName) {
        for (int i = 0; i < drugs.size(); i++) {
            com.example.java_fx_project.Drug drug = drugs.get(i);
            if (drug.getDrugname().equals(drugName)) {
                return i;
            }
        }
        return -1;
    }

    public boolean buyDrug(int index) {
        com.example.java_fx_project.Drug drug = drugs.get(index);
        if (drug.getavailable_quantity() > 0) {
            if (drug.getcategory().equals("Cosmetic")) {
                totalSales += 1.2 * drug.getprice();
            } else {
                totalSales += drug.getprice();
            }
            int drugQuantity = drug.getavailable_quantity() - 1;
            drug.setavailable_quantity(drugQuantity);
            if (drugQuantity == 0) {
                drugs.remove(index);
            }
            return true;
        }
        return false;
    }

}
