package com.karlsson.entity.member;


import com.karlsson.entity.item.Item;
import com.karlsson.entity.PricePolicy;
import com.karlsson.entity.Rental;
import com.karlsson.methods.Methods;

import java.util.ArrayList;
import java.util.List;

public abstract class Member implements PricePolicy {
    private final String id;
    private String name;
    private String email;
    private List<Rental> rentalHistory;

    public Member(String name, String email) {
        this.id = Methods.generateIdNumber();
        this.name = name;
        this.email = email;
        this.rentalHistory = new ArrayList<Rental>();
    }

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public String getId() {return id;} //bara getter, ID ska inte kunna ändras

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public List<Rental> getRentalHistory() {return rentalHistory;}

    public void setRentalHistory(List<Rental> rentalHistory) {this.rentalHistory = rentalHistory;}

    @Override
    public abstract double calculatePrice(Item item, int days);

    @Override
    public String toString() {
        return "Member{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", rentalHistory=" + rentalHistory +
                '}';
    }
}
