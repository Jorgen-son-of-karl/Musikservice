package com.karlsson.entity.item;

public class Guitar extends Instrument {
    String brand;
    private String model;
    private boolean electric;
    private String color;

    public Guitar(double pricePerDay, int stock, String brand, String model, boolean electric, String color) {
        super( pricePerDay, stock);
        this.brand = brand;
        this.model = model;
        this.electric = electric;
        this.color = color;
    }

    public String getBrand() {return brand;}

    public void setBrand(String brand) {this.brand = brand;}

    public String getModel() {return model;}

    public void setModel(String model) {this.model = model;}

    public boolean isElectric() {return electric;}

    public void setElectric(boolean electric) {this.electric = electric;}

    public String getColor() {return color;}

    public void setColor(String color) {this.color = color;}

    @Override
    public String getDisplayName() {
        String s = getBrand() + " " + getModel();
        return s;
    }

    @Override
    public String toString() {
        return "Guitar{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", electric=" + electric +
                ", color='" + color + '\'' +
                '}';
    }
}
