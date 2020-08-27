package com.aruerue.shop.model.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Orders_detail {
private int id;
private int orderId;
private int productId;
private int quantity;
private int price;
private String stats;
private int fee;
}
