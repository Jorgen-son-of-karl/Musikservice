package com.karlsson.entity;

import com.karlsson.entity.item.Item;

public interface PricePolicy {
    double calculatePrice(Item item, int days);
}
