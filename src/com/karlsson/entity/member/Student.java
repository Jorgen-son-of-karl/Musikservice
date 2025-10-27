package com.karlsson.entity.member;

import com.karlsson.entity.item.Item;

public class Student extends Member {
    public Student(String name) {
        super(name);
    }

    @Override
    public double calculatePrice(Item item, int days) {
        double price = item.getPricePerDay() * days;
        double discount = 0.80; // removes 20%
        price = price * discount;
        return price;
    }
}
