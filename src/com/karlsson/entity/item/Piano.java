package com.karlsson.entity.item;

import com.karlsson.entity.member.Member;

public class Piano extends Item {
    private boolean grand;

    public Piano( int stock, String brand, String model, boolean grand) {
        super(stock,  brand, model);
        this.grand = grand;
    }
    public boolean isGrand() {return grand;}

    public void setGrand(boolean grand) {this.grand = grand;}



    @Override
    public String getDisplayName() {
        String s = "Piano, " + getBrand() + " " + getModel();
        if (isGrand()) {
            s += ", flygel";
        }
        return s;
    }

    @Override
    public String toString() {
        return "Piano{" +
                ", grand=" + isGrand() +
                '}';
    }

    @Override
    public double calculatePrice(Item item, int days, Member member) {
        double price = 1000 * days;
        double discount = 0.0;

        switch (member.getLevel()) {
            case STUDENT -> discount = 0.2; //student 20% rabatt
            case PREMIUM -> discount = 0.5; //premium 50%
            case STANDARD -> discount = 0.0; //standard ingen rabatt
        }

        return price * (1 - discount);
    }

}
