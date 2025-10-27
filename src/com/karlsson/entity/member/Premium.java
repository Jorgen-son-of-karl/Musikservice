package com.karlsson.entity.member;

import com.karlsson.entity.item.Item;

public class Premium extends Member {
    public Premium(String name, String email) {
        super(name, email);
    }

    @Override
    public double calculatePrice(Item item, int days) {
        double price = item.getPricePerDay() * days;
        double discount = 0.50; // removes 50%
        price = price * discount;
        return price;
    }
}
