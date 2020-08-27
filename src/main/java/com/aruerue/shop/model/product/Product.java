package com.aruerue.shop.model.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
private int id;
private String title;
private String thumb;
private int price;
private String disc;
private boolean ad;
private int discounted;
private String content;
private int categoryId;
private String bgImg;
private String changebgImg;

}
