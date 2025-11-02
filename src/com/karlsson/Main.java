package com.karlsson;

import com.karlsson.data.MembershipRegistry;
import com.karlsson.data.Inventory;
import com.karlsson.entity.Rental;
import com.karlsson.entity.item.Item;
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

        Inventory inventory = new Inventory();
        MembershipRegistry membershipRegistry = new MembershipRegistry();
        MemberService memberService = new MemberService();
        ItemService itemService = new ItemService();
        RentalService rentalService = new RentalService();
        List<Item> items = inventory.initializeItemData();
        List<Member> members = membershipRegistry.initializeMemberData();
        membershipRegistry.initializeRentalData(items);


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
            System.out.println("11. Avsluta program");
            try {
                switch (Integer.parseInt(scanner.nextLine())) {
                    case 1:
                        memberService.createNewMember(scanner, membershipRegistry);
                        break;
                    case 2:
                        memberService.findMember(scanner, membershipRegistry);
                        break;
                    case 3:
                        memberService.deleteMember(scanner, membershipRegistry);
                        break;
                    case 4:
                        memberService.alterMember(scanner, membershipRegistry);
                        break;
                    case 5:
                        for (Member member : members) {
                            System.out.println(member.toString());
                        }
                        System.out.println();
                        break;
                    case 6:
                        for (Item item : items) {
                            System.out.println(item.getDisplayName() + ", " + item.getStock() + " i lager.");
                        }
                        System.out.println();
                        break;
                    case 7:
                        rentalService.choseRentingMember(members, items, rentalService, scanner);
                        break;

                    case 8:
                        rentalService.returnRental(members, scanner);
                        break;
                    case 9:
                        itemService.findItems(scanner, items);
                        break;

                    case 10:
                        rentalService.calculateTotalRevenue(members);
                        break;
                    case 11:
                        System.out.println("Avslutar programmet. Ha en fortsatt trevlig dag!");
                        loop = false;
                        break;
                }
            }
            catch (Exception e) {
                System.out.println("Felaktig input, försök igen.");
            }
        }
    }
}