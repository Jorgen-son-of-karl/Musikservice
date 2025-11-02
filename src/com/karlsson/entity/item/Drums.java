package com.karlsson.entity.item;

import com.karlsson.entity.Rental;
import com.karlsson.entity.member.Member;

public class Drums extends Item {
    private String color;
    private int numberOfToms;
    private int numberOfKickDrums;

    public Drums(int stock, String brand, String model, String color, int numberOfToms, int numberOfKickDrums) {
        super(stock, brand, model);
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

    @Override
    public double calculatePrice(Item item, int days, Member member) {
        double price = 750 * days;
        double discount = 0.0;

        switch (member.getLevel()) {
            case STUDENT -> discount = 0.2; //student 20% rabatt
            case PREMIUM -> discount = 0.5; //premium 50%
            case STANDARD -> discount = 0.0; //standard ingen rabatt
        }

        return price * (1 - discount);
    }

}
