package com.karlsson;

import com.karlsson.data.Data;
import com.karlsson.entity.Rental;
import com.karlsson.entity.item.Drums;
import com.karlsson.entity.item.Guitar;
import com.karlsson.entity.item.Item;
import com.karlsson.entity.item.Piano;
import com.karlsson.entity.member.Member;
import com.karlsson.methods.ItemService;
import com.karlsson.methods.MemberService;
import com.karlsson.methods.RentalService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        int memberChoice;
        Member chosenMember;

        Data data = new Data();
        MemberService memberService = new MemberService();
        ItemService itemService = new ItemService();
        RentalService rentalService = new RentalService();

        List<Member> members = data.initializeMemberData();
        List<Item> items = data.initializeItemData();
        data.initializeRentalData();

        System.out.println("Välkommen till musikservice 1.0");
        System.out.println("Vad vill du göra?");

        while (loop) {
            System.out.println();
            System.out.println("1. Lägga till medlem");
            System.out.println("2. Söka efter medlem");
            System.out.println("3. Radera medlem");
            System.out.println("4. Ändra en medlem");
            System.out.println("5. Visa medlemslista");
            System.out.println("6. Visa lista med objekt");
            System.out.println("7. Hyr ut ett objekt");
            System.out.println("8. Hantera återlämning av objekt");
            System.out.println("9. Filtrera/söka efter objekt");
            System.out.println("10. Visa intäkter");

            switch(Integer.parseInt(scanner.nextLine())){
                case 1:
                    memberService.createNewMember(scanner, data);
                    break;
                case 2:
                    MemberService.findMember(scanner, data);
                    break;
                case 3:
                    MemberService.deleteMember(scanner, data);
                    break;
                case 4:
                    MemberService.alterMember(scanner, data);
                    break;
                case 5:
                    for(Member member : members) {
                        System.out.println(member.toString());
                    }
                    System.out.println();
                    break;
                case 6:
                    for(Item item : items) {
                        System.out.println(item.getDisplayName() + ", " + item.getStock() + " i lager.");
                    }
                    System.out.println();
                    break;
                case 7:
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
                    break;

                case 8:
                    System.out.println("Vilken medlem lämnar tillbaka ett objekt? (ange nummer):");
                    for (int i = 0; i < members.size(); i++) {
                        System.out.println((i + 1) + ". " + members.get(i).getName() + " (" + members.get(i).getLevel() + ")" + members.get(i).showActiveRentals());
                    }
                    int memberChoiceReturn = Integer.parseInt(scanner.nextLine());
                    Member chosenMemberReturn = members.get(memberChoiceReturn - 1);
                    if (chosenMemberReturn.getRentalHistory().isEmpty()) {
                        System.out.println(chosenMemberReturn.getName() + " har inga aktiva uthyrningar att återlämna.");
                        break;
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
                        break;
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
                    rentalService.returnRental(chosenRental);
                    break;
                case 9:
                    itemService.findItems(scanner, items);
                    break;

                case 10:
                    rentalService.calculateTotalRevenue(members);
                    default:
                        loop = false;
            }
        }
    }
}