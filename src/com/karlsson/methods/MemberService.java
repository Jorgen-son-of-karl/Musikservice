package com.karlsson.methods;

import com.karlsson.entity.member.Member;
import com.karlsson.entity.member.MembershipLevel;

import java.util.Scanner;

import static com.karlsson.data.Data.members;

public class MemberService {
    public static Member createNewMember(Scanner sc) {
        sc.nextLine();// tom buffert
        System.out.println("Ange namn:");
        String name = sc.nextLine();

        System.out.println("Ange e-post:");
        String email = sc.nextLine();

        System.out.println("Välj medlemsnivå (1 = STANDARD, 2 = STUDENT, 3 = PREMIUM):");
        int choice = sc.nextInt();
        sc.nextLine(); // töm buffert

        MembershipLevel level = switch (choice) {
            case 2 -> MembershipLevel.STUDENT;
            case 3 -> MembershipLevel.PREMIUM;
            default -> MembershipLevel.STANDARD;
        };

        Member member = new Member(name, email, level);
        members.add(member);
        System.out.println("Ny medlem skapad: " + member);

        return member;
    }

}
