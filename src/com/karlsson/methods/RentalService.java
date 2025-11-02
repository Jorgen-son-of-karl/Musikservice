package com.karlsson.methods;

import com.karlsson.entity.item.Item;
import com.karlsson.entity.Rental;
import com.karlsson.entity.member.Member;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Scanner;

public class RentalService {

    public void createNewRental(Member member, Item item, Scanner scanner) {
        if(item.getStock() <= 0){
            System.out.println("Objektet är inte tillgängligt.");
        }
        else{
            System.out.println("Hur många dagar ska objektet hyras?");
            if (!scanner.hasNextInt()) {
                System.out.println("Ogiltig inmatning, försök igen.");
                scanner.next(); // töm felaktigt input
                return;
            }
            int daysToRent = Integer.parseInt(scanner.nextLine());
            if (daysToRent <= 0) {
                System.out.println("Antal dagar måste vara minst 1");
                return;
            }
            LocalDate returnBy = LocalDate.now().plusDays(daysToRent);
            double price = item.calculatePrice(item, daysToRent, member);
            Rental rental = new Rental(member, item, returnBy, price);
            member.getRentalHistory().add(rental);
            item.setStock(item.getStock() - 1);
            System.out.println(member.getName() +" har hyrt " + item.getDisplayName() + " i " + daysToRent + " dagar.");
            System.out.println("Returdatum: " + returnBy);
            System.out.println("Pris: " + price + ":-");

        }
    }

    public void returnRental(Rental rental) {
        if (!rental.isActiveRental()) {
            System.out.println("Denna uthyrning är redan avslutad.");
            return;
        }
        Item item = rental.getItem();
        item.setStock(item.getStock() + 1);
        rental.setActiveRental(false);

        LocalDate today = LocalDate.now();
        if (today.isAfter(rental.getReturnBy())) {
            long delayDays = ChronoUnit.DAYS.between(rental.getReturnBy(), today);
            double fee = delayDays * rental.getPrice() * 0.2;
            double newTotal = rental.getPrice() + fee;
            rental.setPrice(newTotal);
            System.out.println("Förseningsavgift: " + fee + ":- (" + delayDays + " dagar)");
        }

        System.out.println(item.getDisplayName() + " är nu återlämnat.");
        System.out.println("Totalt pris: " + rental.getPrice() + ":-");
    }

    public void calculateTotalRevenue(List<Member> members) {
        double totalRevenue = 0;

        for (Member member : members) {
            for (Rental rental : member.getRentalHistory()) {
                if (!rental.isActiveRental()) { // Endast avslutade hyror
                    totalRevenue += rental.getPrice();
                }
            }
        }

        System.out.println("Totala intäkter: " + totalRevenue + " kr");
    }
}
