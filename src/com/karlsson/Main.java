package com.karlsson;

import com.karlsson.data.Data;
import com.karlsson.entity.item.Item;
import com.karlsson.entity.member.Member;
import com.karlsson.methods.MemberService;
import com.karlsson.methods.RentalService;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

//        Member member = new Member("Karlsson", "jorgen@email.com", MembershipLevel.STUDENT);

        List<Member> members = Data.initializeMemberData();
        List<Item> items = Data.initializeItemData();
        Data.initializeRentalData();

        System.out.println("Välkommen till musikservice 1.0");
        System.out.println("Vad vill du göra?");
        System.out.println();
        System.out.println("1. Lägga till medlem");
        System.out.println("2. Visa Medlemslista");
        System.out.println("3. Hyr ut ett objekt");

        switch(scanner.nextInt()){
            case 1:
                MemberService.createNewMember(scanner);
                break;
            case 2:
                for(Member member : members){
                    System.out.println(member.toString());
                }
            case 3:
                System.out.println("Vilken medlem är det som ska hyra?");
                System.out.println("Välj medlem (ange nummer):");
                for (int i = 0; i < members.size(); i++) {
                    System.out.println((i + 1) + ". " + members.get(i).getName() + " (" + members.get(i).getLevel() + ")");
                }
                int memberChoice = scanner.nextInt();
                Member chosenMember = members.get(memberChoice - 1);

                System.out.println("Välj vilket objekt som ska hyras:");
                for (int i = 0; i < items.size(); i++) {
                    Item item = items.get(i);
                    System.out.println((i + 1) + ". " + item.getClass().getSimpleName() + " " + item.getBrand() + " " + item.getModel() +
                            " (Pris: " + item.getPricePerDay() + " kr/dag, i lager: " + item.getStock() + ")");
                }
                int itemChoice = scanner.nextInt();
                Item chosenItem = items.get(itemChoice - 1);

        }

//        for (Member m : members) {
//            System.out.println(m.toString());
//        }
//
//        for (Item i : items) {
//            System.out.println(i);
//        }
//
//
//
//
//        Item guitar = new Guitar(1200, 6, "Jackson", "kelly", true, "black");
//
//        RentalService rentalService = new RentalService();
//        rentalService.createNewRental(member, guitar, scanner);
//
//        for(Rental rental : member.getRentalHistory()){
//            System.out.println(rental.toString());
//        }
    }
}