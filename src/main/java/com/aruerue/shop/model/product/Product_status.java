package com.aruerue.shop.model.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product_status {
private int id;
private boolean sale;
private boolean newly;
private boolean best;
private int productId;
}
