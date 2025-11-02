package com.karlsson.entity.item;

import com.karlsson.entity.PricePolicy;

public abstract class Item implements PricePolicy {
   private String brand;
   private String model;
   private int stock;

    public Item( int stock, String brand, String model) {
        this.brand = brand;
        this.model = model;
        this.stock = stock;
    }

    public String getBrand() {return brand;}

    public void setBrand(String brand) {this.brand = brand;}

    public String getModel() {return model;}

    public void setModel(String model) {this.model = model;}

    public int getStock() {return stock;}

    public void setStock(int stock) {this.stock = stock;}


    public abstract String getDisplayName();
}
