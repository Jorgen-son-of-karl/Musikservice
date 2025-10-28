package com.karlsson.entity.item;

public abstract class Instrument extends Item {



    public Instrument(double pricePerDay, int stock, String brand, String model) {
        super( pricePerDay, stock, brand, model);

    }

    @Override
    public void rentOut() {

    }

    @Override
    public void returnItem() {

    }
}
