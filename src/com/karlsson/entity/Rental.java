package com.karlsson.entity;

import com.karlsson.entity.item.Item;
import com.karlsson.entity.member.Member;
import java.time.LocalDate;

public class Rental {
    private final Member member;
    private Item item;
    private LocalDate rentedOn;
    private LocalDate returnBy;
    private boolean activeRental;
    private double price;

    public Rental(Member member, Item item, LocalDate returnBy, double price) {
        this.member = member;
        this.item = item;
        this.rentedOn = LocalDate.now(); // vi antar att uthyrningen ska börja samma dag som beställningen sker
        this.returnBy = returnBy;
        this.activeRental = true; //när en ny rental skapas sätts activeRental till true direkt
        this.price = price;
    }

    public Member getMember() {return member;} // bara getter, vi vill inte kunna ändra vilken member som har hyrt

    public Item getItem() {return item;}

    public void setItem(Item item) {this.item = item;}

    public LocalDate getRentedOn() {return rentedOn;}

    public void setRentedOn(LocalDate rentedOn) {this.rentedOn = rentedOn;}

    public LocalDate getReturnBy() {return returnBy;}

    public void setReturnBy(LocalDate returnBy) {this.returnBy = returnBy;}

    public boolean isActiveRental() {return activeRental;}

    public void setActiveRental(boolean activeRental) {this.activeRental = activeRental;}

    public double getPrice() {return price;}

    public void setPrice(double price) {this.price = price;}

    @Override
    public String toString() {
        return "Rental{" +
                "member=" + member.getName() +
                ", item=" + item.getDisplayName() +
                ", rentedOn=" + rentedOn +
                ", returnBy=" + returnBy +
                ", activeRental=" + activeRental +
                ", price=" + price +
                '}';
    }
}
