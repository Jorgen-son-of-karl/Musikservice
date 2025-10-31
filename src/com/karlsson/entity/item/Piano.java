package com.karlsson.entity.item;

public class Piano extends Instrument{
    private boolean grand;

    public Piano(double pricePerDay, int stock, String brand, String model, boolean grand) {
        super(pricePerDay, stock,  brand, model);
        this.grand = grand;
    }
    public boolean isGrand() {return grand;}

    public void setGrand(boolean grand) {this.grand = grand;}



    @Override
    public String getDisplayName() {
        String s = "Piano, " + getBrand() + " " + getModel();
        if (grand) {
            s += ", flygel";
        }
        return s;
    }

    @Override
    public String toString() {
        return "Piano{" +
                ", grand=" + grand +
                '}';
    }
}
