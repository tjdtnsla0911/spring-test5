package com.aruerue.shop.model.user;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Point {
private int id;
private int userId;
private Date historyDate;
private String reason;
private int point;
private int totalPoint;
}
