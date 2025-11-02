package com.karlsson.data;


import com.karlsson.entity.member.MembershipLevel;
import com.karlsson.entity.Rental;
import com.karlsson.entity.item.*;
import com.karlsson.entity.member.*;
import com.karlsson.methods.RentalService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Data {
     public List<Member> members;
     List<Item> items;

    public List<Member> initializeMemberData() {
        members = new ArrayList<>();
        members.add(new Member("Jörgen Karlsson", "jorgen@email.com", MembershipLevel.STUDENT));
        members.add(new Member("Ricky McRich", "iamrich@money.com", MembershipLevel.PREMIUM));
        members.add(new Member("Anders Andersson", "anders@vanlig.com", MembershipLevel.STANDARD));

        return members;
    }

    public List<Item> initializeItemData() {
        items = new ArrayList<>();
        items.add(new Guitar(400, 5, "Jackson", "kelly", true, "Black"));
        items.add(new Guitar(350, 5, "Fender", "Stratocaster", true, "Sunburst"));
        items.add(new Guitar(550, 3, "Takamine", "Dreadnought", false, "Natural"));
        items.add(new Drums(900, 2, "Yamaha", "Ryden", "Black glitter", 4, 2));
        items.add(new Piano(1000, 4, "Yamaha", "NX-3 Avant", true));

        return items;
    }

    public void initializeRentalData() {

        Rental rental = new Rental(members.get(0), items.get(0), LocalDate.now().plusDays(7), items.get(0).getPricePerDay() * 7);
        members.get(0).getRentalHistory().add(rental);
        items.get(0).setStock(items.get(0).getStock() - 1);
    }

}
