package com.karlsson.data;


import com.karlsson.entity.member.MembershipLevel;
import com.karlsson.entity.Rental;
import com.karlsson.entity.item.*;
import com.karlsson.entity.member.Member;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class MembershipRegistry {
     public List<Member> members;

    public List<Member> initializeMemberData() {
        members = new ArrayList<>();
        members.add(new Member("Jörgen Karlsson", "jorgen@email.com", MembershipLevel.STUDENT));
        members.add(new Member("Ricky McRich", "iamrich@money.com", MembershipLevel.PREMIUM));
        members.add(new Member("Anders Andersson", "anders@vanlig.com", MembershipLevel.STANDARD));

        return members;
    }

    public void initializeRentalData(List<Item> items) {
        Rental rental = new Rental(members.get(0), items.get(0), LocalDate.now().plusDays(7), items.get(0).calculatePrice(items.get(0), 7, members.get(0)));
        members.get(0).getRentalHistory().add(rental);
        items.get(0).setStock(items.get(0).getStock() - 1);

        rental = new Rental(members.get(0), items.get(5), LocalDate.now().plusDays(7), items.get(5).calculatePrice(items.get(5), 7, members.get(0)));
        members.get(0).getRentalHistory().add(rental);
        items.get(5).setStock(items.get(5).getStock() - 1);

        rental = new Rental(members.get(1), items.get(5), LocalDate.now().plusDays(7), items.get(5).calculatePrice(items.get(5), 7, members.get(1)));
        rental.setActiveRental(false);
        members.get(1).getRentalHistory().add(rental);
        items.get(5).setStock(items.get(5).getStock() - 1);

        rental = new Rental(members.get(1), items.get(5), LocalDate.now().plusDays(7), items.get(5).calculatePrice(items.get(5), 7, members.get(1)));
        members.get(0).getRentalHistory().add(rental);
        items.get(5).setStock(items.get(5).getStock() - 1);

        rental = new Rental(members.get(0), items.get(5), LocalDate.now().plusDays(7), items.get(0).calculatePrice(items.get(0), 7, members.get(0)));
        members.get(0).getRentalHistory().add(rental);
        items.get(5).setStock(items.get(5).getStock() - 1);
    }
}
