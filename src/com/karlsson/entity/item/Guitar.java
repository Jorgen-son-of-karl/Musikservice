package com.karlsson.entity.item;

public class Guitar extends Instrument { ;
    private boolean electric;
    private String color;

    public Guitar(double pricePerDay, int stock, String brand, String model, boolean electric, String color) {
        super( pricePerDay, stock, brand, model);
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
