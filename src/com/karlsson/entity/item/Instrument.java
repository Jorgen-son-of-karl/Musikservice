package com.karlsson.entity.item;

public abstract class Instrument extends Item {



    public Instrument(double pricePerDay, int stock) {
        super( pricePerDay, stock);

    }




    @Override
    public void rentOut() {

    }

    @Override
    public void returnItem() {

    }
}
