package com.aruerue.shop.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Notice {
private int id;
private String title;
private String content;
private Timestamp createDate;
private int readCount;
private String thumb;
}
