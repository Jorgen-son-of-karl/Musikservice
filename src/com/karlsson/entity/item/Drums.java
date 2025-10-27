package com.karlsson.entity.item;

public class Drums extends Instrument{
    private String brand;
    private String model;
    private String color;
    private int numberOfToms;
    private int numberOfKickDrums;

    public Drums(double pricePerDay, int stock, String brand, String model, String color, int numberOfToms, int numberOfKickDrums) {
        super(pricePerDay, stock);
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.numberOfToms = numberOfToms;
        this.numberOfKickDrums = numberOfKickDrums;
    }

    public String getBrand() {return brand;}

    public void setBrand(String brand) {this.brand = brand;}

    public String getModel() {return model;}

    public void setModel(String model) {this.model = model;}

    public String getColor() {return color;}

    public void setColor(String color) {this.color = color;}

    public int getNumberOfToms() {return numberOfToms;}

    public void setNumberOfToms(int numberOfToms) {this.numberOfToms = numberOfToms;}

    public int getNumberOfKickDrums() {return numberOfKickDrums;}

    public void setNumberOfKickDrums(int numberOfKickDrums) {this.numberOfKickDrums = numberOfKickDrums;}

    @Override
    public String getDisplayName() {
        String s = getBrand() + " " + getModel();
        return s;
    }
}
