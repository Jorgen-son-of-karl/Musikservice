package com.karlsson;

import com.karlsson.entity.Rental;
import com.karlsson.entity.item.Guitar;
import com.karlsson.entity.item.Item;
import com.karlsson.entity.member.Member;
import com.karlsson.entity.member.Student;
import com.karlsson.methods.RentalService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Member member = new Student("Karlsson");

        System.out.println(member.toString());


        Item guitar = new Guitar(1200, 6, "Jackson", "kelly", true, "black");

        RentalService rentalService = new RentalService();
        rentalService.createNewRental(member, guitar, scanner);

        for(Rental rental : member.getRentalHistory()){
            System.out.println(rental.toString());
        }
    }
}