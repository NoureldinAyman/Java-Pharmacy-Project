package com.example.java_fx_project;

import javafx.scene.control.ChoiceBox;

public class Drug {
    public String Drugname;
    public String category;
    public int id;
    public double price;
    public int quantity;
    public String prescription;


    //constructor
    public Drug(String Drugname, int id, double price, ChoiceBox<String> category, int quantity, String prescription){
        this.Drugname=Drugname;
        this.quantity=quantity;
        this.category = category.getValue();
        this.id=id;
        this.price=price;
        this.prescription = prescription;
    }

    //setters
    public void setDrugname(String Drugname){
        this.Drugname= Drugname;
    }
    public void setprice(double price){
        this.price= price;
    }
    public void setcategory(String category){
        this.category= category;
    }
    public void setid(int id){
        this.id= id;
    }
    public void setavailable_quantity(int available_quantity){
        this.quantity= available_quantity;
    }


    //getters
    public String getDrugname(){
        return Drugname;
    }
    public double getprice(){
        return  price;
    }
    public String getcategory(){
        return  category;
    }
    public int getid(){
        return  id;
    }
    public int getavailable_quantity(){
        return quantity;
    }

    public String getPrescription() {
        return this.prescription;
    }
}







