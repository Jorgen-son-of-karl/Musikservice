package com.karlsson.entity;

import com.karlsson.entity.item.Item;
import com.karlsson.entity.member.Member;

public interface PricePolicy {
    double calculatePrice(Item item, int days, Member member);
}
