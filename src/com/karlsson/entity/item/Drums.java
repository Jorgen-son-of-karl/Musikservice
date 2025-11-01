package com.karlsson.entity.item;

public class Drums extends Instrument{
    private String color;
    private int numberOfToms;
    private int numberOfKickDrums;

    public Drums(double pricePerDay, int stock, String brand, String model, String color, int numberOfToms, int numberOfKickDrums) {
        super(pricePerDay, stock, brand, model);
        this.color = color;
        this.numberOfToms = numberOfToms;
        this.numberOfKickDrums = numberOfKickDrums;
    }


    public String getColor() {return color;}

    public void setColor(String color) {this.color = color;}

    public int getNumberOfToms() {return numberOfToms;}

    public void setNumberOfToms(int numberOfToms) {this.numberOfToms = numberOfToms;}

    public int getNumberOfKickDrums() {return numberOfKickDrums;}

    public void setNumberOfKickDrums(int numberOfKickDrums) {this.numberOfKickDrums = numberOfKickDrums;}

    @Override
    public String getDisplayName() {
        String s = "Trummor, " + getBrand() + " " + getModel() + ", med " + getNumberOfToms() + " pukor och " + getNumberOfKickDrums() + " baskaggar";
        return s;
    }

    @Override
    public String toString() {
        return "Drums{" +
                ", color='" + color + '\'' +
                ", numberOfToms=" + numberOfToms +
                ", numberOfKickDrums=" + numberOfKickDrums +
                '}';
    }
}
