package com.aruerue.shop.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {
private String title;
private int price;
private int discounted;
private boolean sale;
private boolean best;
private boolean newly;
}
