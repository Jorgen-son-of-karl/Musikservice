package com.karlsson;

import com.karlsson.data.Data;
import com.karlsson.entity.Rental;
import com.karlsson.entity.item.Item;
import com.karlsson.entity.member.Member;
import com.karlsson.methods.MemberService;
import com.karlsson.methods.RentalService;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        int memberChoice;
        Member chosenMember;

        List<Member> members = Data.initializeMemberData();
        List<Item> items = Data.initializeItemData();
        Data.initializeRentalData();

        System.out.println("Välkommen till musikservice 1.0");
        System.out.println("Vad vill du göra?");
        System.out.println();

        while (loop) {
            System.out.println("1. Lägga till medlem");
            System.out.println("2. Visa Medlemslista");
            System.out.println("3. Hyr ut ett objekt");
            System.out.println("4. Hantera återlämning av objekt");
            switch(Integer.parseInt(scanner.nextLine())){
                case 1:
                    MemberService.createNewMember(scanner);
                    break;
                case 2:
                    for(Member member : members) {
                        System.out.println(member.toString());
                    }
                        break;

                case 3:
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
                        System.out.println((i + 1) + ". " + item.getClass().getSimpleName() + " " + item.getBrand() + " " + item.getModel() +
                                " (Pris: " + item.getPricePerDay() + " kr/dag, i lager: " + item.getStock() + ")");
                    }
                    int itemChoice = Integer.parseInt(scanner.nextLine());
                    Item chosenItem = items.get(itemChoice - 1);
                    RentalService.createNewRental(chosenMember, chosenItem, scanner);
                    break;

                case 4:
                    System.out.println("Vilken medlem lämnar tillbaka ett objekt? (ange nummer):");
                    for (int i = 0; i < members.size(); i++) {
                        System.out.println((i + 1) + ". " + members.get(i).getName() + " (" + members.get(i).getLevel() + ")");
                    }
                    int memberChoiceReturn = Integer.parseInt(scanner.nextLine());
                    Member chosenMemberReturn = members.get(memberChoiceReturn - 1);
                    if (chosenMemberReturn.getRentalHistory().isEmpty()) {
                        System.out.println(chosenMemberReturn.getName() + " har inga aktiva hyrningar att återlämna.");
                        break;
                    }

                    // Visa uthyrda objekt för medlemmen
                    System.out.println("Vilket objekt vill du lämna tillbaka?");
                    List<Rental> rentalList = chosenMemberReturn.getRentalHistory();
                    for (int i = 0; i < rentalList.size(); i++) {
                        Rental rental = rentalList.get(i);
                        System.out.println((i + 1) + ". " + rental.getItem().getClass().getSimpleName() + " " + rental.getItem().getBrand() + " " + rental.getItem().getModel() + " (Pris: " + rental.getPrice() + " kr, Uthyrd: " + rental.getRentedOn() + ")");
                    }

                    // Läs val av rental
                    int rentalChoice = Integer.parseInt(scanner.nextLine());
                    Rental chosenRental = rentalList.get(rentalChoice - 1);

                    // Skicka till RentalService
                    RentalService.returnRental(chosenRental);

                    break;
            }
        }
    }
}