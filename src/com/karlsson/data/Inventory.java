package com.karlsson.data;

import com.karlsson.entity.item.Drums;
import com.karlsson.entity.item.Guitar;
import com.karlsson.entity.item.Item;
import com.karlsson.entity.item.Piano;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    List<Item> items;
    public List<Item> initializeItemData() {
        items = new ArrayList<>();
        items.add(new Guitar(5, "Jackson", "kelly", true, "Black"));
        items.add(new Guitar( 5, "Fender", "Stratocaster", true, "Sunburst"));
        items.add(new Guitar( 3, "Takamine", "Dreadnought", false, "Natural"));
        items.add(new Drums( 2, "Yamaha", "Ryden", "Black glitter", 4, 2));
        items.add(new Piano( 4, "Yamaha", "NX-3 Avant", true));
        items.add(new Piano( 10, "Casio", "CS46", false));
        items.add(new Piano( 8, "FunKey", "DP-61", false));
        items.add(new Guitar(6,"Gibson", "Explorer", true, "Cream white"));
        items.add(new Guitar(6,"Gibson", "Flying V", true, "Cream white"));
        items.add(new Guitar(3,"BC. Rich", "Warlock", true, "Red quilted maple"));



        return items;
    }
}
