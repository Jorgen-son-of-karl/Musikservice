package com.karlsson.methods;

import com.karlsson.data.MembershipRegistry;
import com.karlsson.entity.member.Member;
import com.karlsson.entity.member.MembershipLevel;

import java.util.List;
import java.util.Scanner;


public class MemberService {
    public void createNewMember(Scanner sc, MembershipRegistry d) {
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
            d.members.add(member);
            System.out.println("Ny medlem skapad: " + member);
        }
        catch(Exception e){
            System.out.println("Ogiltigt input av medlemsnivå!");
            System.out.println();
        }
    }

    public void deleteMember(Scanner sc, MembershipRegistry d) {
        System.out.println("Välj medlem som ska raderas:");
        for(int i = 0; i < d.members.size(); i++){
            System.out.println((i + 1) + ". " + d.members.get(i).getId() +" , " + d.members.get(i).getName() + " , Email: " + d.members.get(i).getEmail() + " (" + d.members.get(i).getLevel() + ")");
        }
        int choice = Integer.parseInt(sc.nextLine());
        System.out.println("OBS! Är du säker på att du vill ta bort medlem " + d.members.get(choice-1).getId() + " , " + d.members.get(choice-1).getName() + " , Email: " + d.members.get(choice-1).getEmail() + " (Y/N)");
        String answer = sc.nextLine().toLowerCase();
        if(answer.equals("n") || answer.equals("no")){
            System.out.println("Radering av medlem avbruten.");
        }
        else if (answer.equals("y") || answer.equals("yes")){

            System.out.println("Medlem " + d.members.get(choice-1).getId() + " , " + d.members.get(choice-1).getName() + " , Email: " + d.members.get(choice-1).getEmail() + ", raderad.");
            d.members.remove(choice - 1);
        }

    }

    public void alterMember(Scanner sc, MembershipRegistry d) {
        System.out.println("Välj vilken medlem som ska ändras");
        for(int i = 0; i < d.members.size(); i++){
            System.out.println((i + 1) + ". " + d.members.get(i).getId() +" , " + d.members.get(i).getName() + " , Email: " + d.members.get(i).getEmail() + " (" + d.members.get(i).getLevel() + ")");
        }
        int memberChoice = Integer.parseInt(sc.nextLine());
        System.out.println("vad ska ändras?");
        System.out.println("1. Namn: " + d.members.get(memberChoice-1).getName());
        System.out.println("2. Email: " + d.members.get(memberChoice-1).getEmail());
        System.out.println("3. Medlemsstatus: " + d.members.get(memberChoice-1).getLevel());
        System.out.println("4. Avbryt");
        int  attributeChoice = Integer.parseInt(sc.nextLine());
        if(attributeChoice == 1){
            System.out.println("Välj nytt namn:");
            d.members.get(memberChoice-1).setName(sc.nextLine());
        }
        else if(attributeChoice == 2){
            System.out.println("Välj nytt email:");
            d.members.get(memberChoice-1).setEmail(sc.nextLine());
        }
        else if(attributeChoice == 3){
            System.out.println("Välj nytt medlemskap");
            System.out.println("1. Student");
            System.out.println("2. Premium");
            System.out.println("3. Standard");
            int membershipChoice = Integer.parseInt(sc.nextLine());
            switch (membershipChoice) {
                case 1 -> d.members.get(memberChoice-1).setLevel(MembershipLevel.STUDENT);
                case 2 -> d.members.get(memberChoice-1).setLevel(MembershipLevel.PREMIUM);
                case 3 -> d.members.get(memberChoice-1).setLevel(MembershipLevel.STANDARD);
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

    public void findMember(Scanner sc, MembershipRegistry d) {

        System.out.println("Ange medlemmens namn, email, eller ID");
        String input = sc.nextLine();
        List<Member> foundMembers = d.members.stream()
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
            }
        }
    }
}
