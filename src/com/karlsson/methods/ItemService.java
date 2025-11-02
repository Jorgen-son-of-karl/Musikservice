package com.karlsson.methods;

import com.karlsson.entity.item.Drums;
import com.karlsson.entity.item.Guitar;
import com.karlsson.entity.item.Item;
import com.karlsson.entity.item.Piano;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ItemService {
    public void findItems(Scanner sc, List<Item> items) {
        System.out.println("Sök efter objekt (ange märke, modell, eller typ):");
        String input = sc.nextLine().toLowerCase();

        List<Item> foundItems = new ArrayList<>();

        for (Item item : items) {
            String type = item.getClass().getSimpleName().toLowerCase();

            if ((item.getBrand() != null && item.getBrand().toLowerCase().contains(input)) ||
                    (item.getModel() != null && item.getModel().toLowerCase().contains(input)) ||
                    type.contains(input)) {
                foundItems.add(item);
            }
        }

        if (foundItems.isEmpty()) {
            System.out.println("Inga objekt hittades.");
        } else {
            System.out.println("Hittade följande objekt:");
            for (Item i : foundItems) {
                int itemPrice = 0;
                if(i instanceof Guitar) {
                    itemPrice = 500;
                }
                else if(i instanceof Drums){
                    itemPrice = 750;
                }
                else if(i instanceof Piano){
                    itemPrice = 1000;
                }
                System.out.println(i.getDisplayName() + " (Pris: " + itemPrice + " kr/dag, i lager: " + i.getStock() + ")");
            }
        }
    }
}
