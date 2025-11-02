package com.karlsson.methods;

import com.karlsson.entity.item.Drums;
import com.karlsson.entity.item.Guitar;
import com.karlsson.entity.item.Item;
import com.karlsson.entity.Rental;
import com.karlsson.entity.item.Piano;
import com.karlsson.entity.member.Member;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
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

    public void returnRental(List<Member> members, Scanner scanner)
    {
        System.out.println("Vilken medlem lämnar tillbaka ett objekt? (ange nummer):");
        for (int i = 0; i < members.size(); i++) {
            System.out.println((i + 1) + ". " + members.get(i).getName() + " (" + members.get(i).getLevel() + ")" + members.get(i).showActiveRentals());
        }
        int memberChoiceReturn = Integer.parseInt(scanner.nextLine());
        Member chosenMemberReturn = members.get(memberChoiceReturn - 1);
        if (chosenMemberReturn.getRentalHistory().isEmpty()) {
            System.out.println(chosenMemberReturn.getName() + " har inga aktiva uthyrningar att återlämna.");
            return;
        }

        System.out.println("Vilket objekt vill du lämna tillbaka?");

        // Skapa en ny lista med aktiva uthyrningar
        List<Rental> activeRentals = new ArrayList<>();
        for (Rental rental : chosenMemberReturn.getRentalHistory()) {
            if (rental.isActiveRental()) {
                activeRentals.add(rental);
            }
        }

        //  Kolla om det finns några aktiva uthyrningar
        if (activeRentals.isEmpty()) {
            System.out.println("Inga aktiva uthyrningar hittades för " + chosenMemberReturn.getName() + ".");
            return;
        }

        // Visa alla aktiva uthyrningar
        for (int i = 0; i < activeRentals.size(); i++) {
            Rental rental = activeRentals.get(i);
            System.out.println((i + 1) + ". "
                    + rental.getItem().getClass().getSimpleName() + " "
                    + rental.getItem().getBrand() + " "
                    + rental.getItem().getModel() +
                    " (Uthyrd: " + rental.getRentedOn() + ")");
        }

        int rentalChoice = Integer.parseInt(scanner.nextLine());
        Rental chosenRental = activeRentals.get(rentalChoice - 1);

        if (!chosenRental.isActiveRental()) {
            System.out.println("Denna uthyrning är redan avslutad.");
            return;
        }
        Item item = chosenRental.getItem();
        item.setStock(item.getStock() + 1);
        chosenRental.setActiveRental(false);

        LocalDate today = LocalDate.now();
        if (today.isAfter(chosenRental.getReturnBy())) {
            long delayDays = ChronoUnit.DAYS.between(chosenRental.getReturnBy(), today);
            double fee = delayDays * chosenRental.getPrice() * 0.2;
            double newTotal = chosenRental.getPrice() + fee;
            chosenRental.setPrice(newTotal);
            System.out.println("Förseningsavgift: " + fee + ":- (" + delayDays + " dagar)");
        }

        System.out.println(item.getDisplayName() + " är nu återlämnat.");
        System.out.println("Totalt pris: " + chosenRental.getPrice() + ":-");
    }

    public void choseRentingMember(List<Member> members, List<Item> items, RentalService rentalService, Scanner scanner) {
        int memberChoice;
        Member chosenMember;
        System.out.println("Vilken medlem är det som ska hyra?");
        System.out.println("Välj medlem (ange nummer):");
        for (int i = 0; i < members.size(); i++) {
            System.out.println((i + 1) + ". " + members.get(i).getName() + " (" + members.get(i).getLevel() + ")");
        }
        memberChoice = Integer.parseInt(scanner.nextLine());
        chosenMember = members.get(memberChoice - 1);

        System.out.println("Välj vilket objekt som ska hyras:");
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            int itemPrice = 0;
            if(item instanceof Guitar) {
                itemPrice = 500;
            }
            else if(item instanceof Drums){
                itemPrice = 750;
            }
            else if(item instanceof Piano){
                itemPrice = 1000;
            }
            System.out.println((i + 1) + ". " + item.getClass().getSimpleName() + " " + item.getBrand() + " " + item.getModel() + " (Pris: " + itemPrice + " kr/dag, i lager: " + item.getStock() + ")");
        }
        int itemChoice = Integer.parseInt(scanner.nextLine());
        Item chosenItem = items.get(itemChoice - 1);
        rentalService.createNewRental(chosenMember, chosenItem, scanner);
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
