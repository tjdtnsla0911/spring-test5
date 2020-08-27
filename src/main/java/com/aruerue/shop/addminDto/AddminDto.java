package com.aruerue.shop.addminDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddminDto {
private int id;
private String title;
private String thumb;
private int price;
private String disc;
private int discounted;
private String content;
private String bgImg;
private String radioSale;
private String radioAd;
private String radioParentTypeId; //카테고리아이디 겸용
private String radioBest;
private String radioNew;
///////////추가되놈들///////
private int productid;
private int parentProductId;
private int relateProductId;

//html + ajax에서 boolean형으로 true 적어서 문자형으로 자꾸인식해서
//위에서 String 형으로 한번받고 if으로 다시 boolean으로 하는 방식 택했습니다.

private boolean radioSale2;
private boolean radioAd2;
private boolean radioParentTypeId2; //카테고리아이디 겸용
private boolean radioBest2;
private boolean radioNew2;



}
