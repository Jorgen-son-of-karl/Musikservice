package com.karlsson.entity.item;

public abstract class Item {
    private double pricePerDay;
    private int stock;

    public Item(double pricePerDay, int stock) {

        this.pricePerDay = pricePerDay;
        this.stock = stock;
    }


    public double getPricePerDay() {return pricePerDay;}

    public void setPricePerDay(double pricePerDay) {this.pricePerDay = pricePerDay;}

    public int getStock() {return stock;}

    public void setStock(int stock) {this.stock = stock;}

    public abstract void rentOut();
    public abstract void returnItem();

    public abstract String getDisplayName();
}
