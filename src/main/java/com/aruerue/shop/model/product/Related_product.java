package com.aruerue.shop.model.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Related_product {
private int id;
private int parentProductId;
private int relatedProductId;
}
