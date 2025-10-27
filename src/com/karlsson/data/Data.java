package com.karlsson.data;

import com.karlsson.entity.item.*;
import com.karlsson.entity.member.*;

import java.util.ArrayList;
import java.util.List;

public class Data {
    public static List<Member> initializeMemberData() {
        List<Member> members = new ArrayList<>();
        members.add(new Student("Jörgen Karlsson", "jorgen@email.com"));
        members.add(new Premium("Ricky McRich", "iamrich@money.com"));
        members.add(new Standard("Anders Andersson", "anders@vanlig.com"));
        return members;
    }

    public static List<Item> initializeItemData() {
        List<Item> items = new ArrayList<>();
        items.add(new Guitar(400, 5, "Jackson", "kelly", true, "Black"));
        items.add(new Guitar(350, 5, "Fender", "Stratocaster", true, "Sunburst"));
        items.add(new Guitar(550, 3, "Takamine", "Dreadnought", false, "Natural"));
        items.add(new Drums(900, 2, "Yamaha", "Ryden", "Black glitter", 4, 2));
        items.add(new Piano(1000, 4, "Yamaha", "NX-3 Avant", true));

        return items;
    }
}
