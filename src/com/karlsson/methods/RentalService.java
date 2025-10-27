package com.karlsson.methods;

import com.karlsson.entity.item.Item;
import com.karlsson.entity.Rental;
import com.karlsson.entity.member.Member;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class RentalService {

    public Rental createNewRental(Member member, Item item, Scanner scanner) {
        if(item.getStock() <= 0){
            System.out.println("Objektet är inte tillgängligt.");
            return null;
        }
        else{
            System.out.println("Hur många dagar vill du hyra objektet?");
            if (!scanner.hasNextInt()) {
                System.out.println("Ogiltig inmatning, försök igen.");
                scanner.next(); // töm felaktigt input
                return null;
            }
            int daysToRent = scanner.nextInt();
            if (daysToRent <= 0) {
                System.out.println("Antal dagar måste vara minst 1");
                return null;
            }
            LocalDate returnBy = LocalDate.now().plusDays(daysToRent);
            double price = member.calculatePrice(item, daysToRent);
            Rental rental = new Rental(member, item, returnBy, price);
            member.getRentalHistory().add(rental);
            item.setStock(item.getStock() - 1);
            System.out.println("Du har hyrt " + item.getDisplayName() + " i " + daysToRent + " dagar.");
            System.out.println("Returdatum: " + returnBy);
            System.out.println("Pris: " + price + ":-");

            return rental;
        }
    }

    public void returnRental(Rental rental) {
        if (!rental.isActiveRental()) {
            System.out.println("Denna hyrning är redan avslutad.");
            return;
        }

        Item item = rental.getItem();
        item.setStock(item.getStock() + 1);
        rental.setActiveRental(false);

        LocalDate today = LocalDate.now();
        if (today.isAfter(rental.getReturnBy())) {
            long delayDays = ChronoUnit.DAYS.between(rental.getReturnBy(), today);
            double fee = delayDays * item.getPricePerDay() * 0.2;
            double newTotal = rental.getPrice() + fee;
            rental.setPrice(newTotal);
            System.out.println("Förseningsavgift: " + fee + ":- (" + delayDays + " dagar)");
        }

        System.out.println("Tack! Du har återlämnat " + item.getDisplayName() + ".");
        System.out.println("Totalt pris: " + rental.getPrice() + ":-");
    }
}
