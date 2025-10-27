package com.karlsson.entity.member;

import com.karlsson.entity.item.Item;

public class Standard extends Member{
    public Standard(String name, String email) {
        super(name, email);
    }

    @Override
    public double calculatePrice(Item item, int days) {
        return item.getPricePerDay() * days;
    }
}
