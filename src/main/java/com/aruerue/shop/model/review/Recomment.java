package com.aruerue.shop.model.review;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Recomment {
private int id;
private int reviewId;
private String comment;
private Timestamp createDate;
}
