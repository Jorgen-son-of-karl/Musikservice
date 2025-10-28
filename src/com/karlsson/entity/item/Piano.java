package com.karlsson.entity.item;

public class Piano extends Instrument{
    private String brand;
    private String model;
    private boolean grand;

    public Piano(double pricePerDay, int stock, String brand, String model, boolean grand) {
        super(pricePerDay, stock,  brand, model);
        this.grand = grand;
    }
    public String getBrand() {return brand;}

    public void setBrand(String brand) {this.brand = brand;}

    public String getModel() {return model;}

    public void setModel(String model) {this.model = model;}

    public boolean isGrand() {return grand;}

    public void setGrand(boolean grand) {this.grand = grand;}



    @Override
    public String getDisplayName() {
        String s = getBrand() + " " + getModel();
        return s;
    }

    @Override
    public String toString() {
        return "Piano{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", grand=" + grand +
                '}';
    }
}
