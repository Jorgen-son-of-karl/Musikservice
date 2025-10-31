package com.karlsson.methods;

import com.karlsson.entity.member.Member;
import com.karlsson.entity.member.MembershipLevel;

import java.util.List;
import java.util.Scanner;

import static com.karlsson.data.Data.members;

public class MemberService {
    public static void createNewMember(Scanner sc) {
        System.out.println("Ange namn:");
        String name = sc.nextLine();

        System.out.println("Ange e-post:");
        String email = sc.nextLine();

        System.out.println("Välj medlemsnivå (1 = STANDARD, 2 = STUDENT, 3 = PREMIUM):");
        try {
            int choice = Integer.parseInt(sc.nextLine());
            MembershipLevel level = switch (choice) {
                case 2 -> MembershipLevel.STUDENT;
                case 3 -> MembershipLevel.PREMIUM;
                default -> MembershipLevel.STANDARD;
            };
            Member member = new Member(name, email, level);
            members.add(member);
            System.out.println("Ny medlem skapad: " + member);
        }
        catch(Exception e){
            System.out.println("Ogiltigt input av medlemsnivå!");
            System.out.println();
        }
    }

    public static void deleteMember(Scanner sc) {
        System.out.println("Välj medlem som ska raderas:");
        for(int i = 0; i < members.size(); i++){
            System.out.println((i + 1) + ". " + members.get(i).getId() +" , " + members.get(i).getName() + " , Email: " +members.get(i).getEmail() + " (" + members.get(i).getLevel() + ")");
        }
        int choice = Integer.parseInt(sc.nextLine());
        System.out.println("OBS! Är du säker på att du vill ta bort medlem " + members.get(choice-1).getId() + " , " + members.get(choice-1).getName() + " , Email: " +members.get(choice-1).getEmail() + " (Y/N)");
        String answer = sc.nextLine().toLowerCase();
        if(answer.equals("n") || answer.equals("no")){
            System.out.println("Radering av medlem avbruten.");
        }
        else if (answer.equals("y") || answer.equals("yes")){

            System.out.println("Medlem " + members.get(choice-1).getId() + " , " + members.get(choice-1).getName() + " , Email: " +members.get(choice-1).getEmail() + ", raderad.");
            members.remove(choice - 1);
        }

    }

    public static void alterMember(Scanner sc) {
        System.out.println("Välj vilken medlem som ska ändras");
        for(int i = 0; i < members.size(); i++){
            System.out.println((i + 1) + ". " + members.get(i).getId() +" , " + members.get(i).getName() + " , Email: " +members.get(i).getEmail() + " (" + members.get(i).getLevel() + ")");
        }
        int memberChoice = Integer.parseInt(sc.nextLine());
        System.out.println("vad ska ändras?");
        System.out.println("1. Namn: " + members.get(memberChoice-1).getName());
        System.out.println("2. Email: " + members.get(memberChoice-1).getEmail());
        System.out.println("3. Medlemsstatus: " + members.get(memberChoice-1).getLevel());
        System.out.println("4. Avbryt");
        int  attributeChoice = Integer.parseInt(sc.nextLine());
        if(attributeChoice == 1){
            System.out.println("Välj nytt namn:");
            members.get(memberChoice-1).setName(sc.nextLine());
        }
        else if(attributeChoice == 2){
            System.out.println("Välj nytt email:");
            members.get(memberChoice-1).setEmail(sc.nextLine());
        }
        else if(attributeChoice == 3){
            System.out.println("Välj nytt medlemskap");
            System.out.println("1. Student");
            System.out.println("2. Premium");
            System.out.println("3. Standard");
            int membershipChoice = Integer.parseInt(sc.nextLine());
            switch (membershipChoice) {
                case 1 -> members.get(memberChoice-1).setLevel(MembershipLevel.STUDENT);
                case 2 -> members.get(memberChoice-1).setLevel(MembershipLevel.PREMIUM);
                case 3 -> members.get(memberChoice-1).setLevel(MembershipLevel.STANDARD);
            }
        }
        else {
            System.out.println("Avbryter.");
            System.out.println();
            return;
        }
        System.out.println("Medlem ändrad.");
        System.out.println();
    }

    public static void findMember(Scanner sc) {

        System.out.println("Ange medlemmens namn, email, eller ID");
        String input = sc.nextLine();
        List<Member> foundMembers = members.stream()
                .filter(m -> m.getName().toLowerCase().contains(input)
                        || m.getEmail().toLowerCase().contains(input)
                        || m.getId().equalsIgnoreCase(input))
                .toList();
        if(foundMembers.isEmpty()){
            System.out.println("Kunde ej hitta medlem.");
        }
        else{
            System.out.println("Hittade " + foundMembers.size() + " medlem/medlemmar.");
            for(Member member : foundMembers){
                System.out.println(member.toString());
                //TODO lägga till logik för att hantera medlem
            }
        }
    }
}
