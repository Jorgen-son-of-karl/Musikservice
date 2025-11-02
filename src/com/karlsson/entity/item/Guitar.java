package com.karlsson.entity.item;

import com.karlsson.entity.member.Member;

public class Guitar extends Item { ;
    private boolean electric;
    private String color;

    public Guitar(int stock, String brand, String model, boolean electric, String color) {
        super(stock, brand, model);
        this.electric = electric;
        this.color = color;
    }


    public boolean isElectric() {return electric;}

    public void setElectric(boolean electric) {this.electric = electric;}

    public String getColor() {return color;}

    public void setColor(String color) {this.color = color;}

    @Override
    public String getDisplayName() {
        String s = "Gitarr, " + " " + getBrand() + " " + getModel();
        if (electric) {
            s += ", elgitarr";
        }
        else s += ", akustisk";
        return s;
    }

    @Override
    public double calculatePrice(Item item, int days, Member member) {
        double price = 500 * days;
        double discount = 0.0;

        switch (member.getLevel()) {
            case STUDENT -> discount = 0.2; //student 20% rabatt
            case PREMIUM -> discount = 0.5; //premium 50%
            case STANDARD -> discount = 0.0; //standard ingen rabatt
        }

        return price * (1 - discount);
    }

    // @Override
//    public String toString() {
//        return "Guitar{" +
//                "brand='" + brand + '\'' +
//                ", model='" + model + '\'' +
//                ", electric=" + electric +
//                ", color='" + color + '\'' +
//                '}';
//    }
}
