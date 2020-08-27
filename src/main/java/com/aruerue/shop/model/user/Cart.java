package com.aruerue.shop.model.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cart {
private int id;
private int productId;
private int userId;
private int quantity;
private int price;
}
