package com.karlsson.entity.member;


import com.karlsson.entity.item.Item;
import com.karlsson.entity.PricePolicy;
import com.karlsson.entity.Rental;
import com.karlsson.methods.Methods;
import java.util.ArrayList;
import java.util.List;


public class Member implements PricePolicy {
    private final String id;
    private String name;
    private MembershipLevel level;
    private String email;
    private List<Rental> rentalHistory;

    public Member(String name, String email,  MembershipLevel level) {
        this.id = Methods.generateIdNumber();
        this.name = name;
        this.email = email;
        this.level = level;
        this.rentalHistory = new ArrayList<Rental>();
    }

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public MembershipLevel getLevel() { return level; }

    public void setLevel(MembershipLevel level) { this.level = level; }

    public String getId() {return id;} //bara getter, ID ska inte kunna ändras

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public List<Rental> getRentalHistory() {return rentalHistory;}

    public void setRentalHistory(List<Rental> rentalHistory) {this.rentalHistory = rentalHistory;}

    @Override
    public double calculatePrice(Item item, int days, Member member) {
        double price = item.getPricePerDay() * days;
        double discount = 0.0;

        switch (member.getLevel()) {
            case STUDENT -> discount = 0.2; //student 20% rabatt
            case PREMIUM -> discount = 0.5; //premium 50%
            case STANDARD -> discount = 0.0; //standard ingen rabatt
        }

        return price * (1 - discount);
    }

    public String showActiveRentals(){
        int activeRentals = 0;
        for (Rental rental : rentalHistory) {
            if(rental.isActiveRental()){
                activeRentals++;
            }
        }
        if(activeRentals > 0){
            return activeRentals + " objekt uthyrda nu.";
        }
        else return "";
    }

    @Override
    public String toString() {
        return "Member{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", level=" + level +
                ", rentalHistory=" + rentalHistory +
                '}';
    }
}
