package com.aruerue.shop.model.review;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Review {
private int id;
private int productId;
private String title;
private String content;
private int userId;
private Timestamp createDate;
private int star;
private String img;
private boolean existenceOfImg;
private int orderId;
}
