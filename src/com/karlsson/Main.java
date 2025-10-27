package com.karlsson;

import com.karlsson.data.Data;
import com.karlsson.entity.Rental;
import com.karlsson.entity.item.Guitar;
import com.karlsson.entity.item.Item;
import com.karlsson.entity.member.Member;
import com.karlsson.entity.member.Student;
import com.karlsson.methods.RentalService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Member member = new Student("Karlsson", "jorgen@email.com");

        List<Member> members = Data.initializeMemberData();
        List<Item> items = Data.initializeItemData();

        for (Member m : members) {
            System.out.println(m.toString());
        }

        for (Item i : items) {
            System.out.println(i);
        }



        Item guitar = new Guitar(1200, 6, "Jackson", "kelly", true, "black");

        RentalService rentalService = new RentalService();
        rentalService.createNewRental(member, guitar, scanner);

        for(Rental rental : member.getRentalHistory()){
            System.out.println(rental.toString());
        }
    }
}