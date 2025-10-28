package com.karlsson.entity.item;

public abstract class Item {
   private String brand;
   private String model;
   private double pricePerDay;
   private int stock;

    public Item(double pricePerDay, int stock, String brand, String model) {
        this.brand = brand;
        this.model = model;
        this.pricePerDay = pricePerDay;
        this.stock = stock;
    }

    public String getBrand() {return brand;}

    public void setBrand(String brand) {this.brand = brand;}

    public String getModel() {return model;}

    public void setModel(String model) {this.model = model;}

    public double getPricePerDay() {return pricePerDay;}

    public void setPricePerDay(double pricePerDay) {this.pricePerDay = pricePerDay;}

    public int getStock() {return stock;}

    public void setStock(int stock) {this.stock = stock;}

    public abstract void rentOut();
    public abstract void returnItem();

    public abstract String getDisplayName();
    //public abstract String toString();
}
