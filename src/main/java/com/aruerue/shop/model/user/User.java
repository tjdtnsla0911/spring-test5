package com.aruerue.shop.model.user;

//import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

   // @Id
   // @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
  //  @Column(unique = true)
    private String username;
    private String password;
    private String email;
    private String name;
    private String gender;
    private String phone;
    private String address;
    private String detail_address;
    private String provider;
    private String providerId;
    private Timestamp birthday;
    private int total_amount;
    private String role;
    private String cancel;
    private String profile;
    private Timestamp createDate;


    // ENUM으로 안하고 ,로 해서 구분해서 ROLE을 입력 -> 그걸 파싱!!
    public List<String> getRoleList(){
    	System.out.println("model.User의 getRoleList()에 왔습니다");
        if(this.role.length() > 0){
        	System.out.println("model.User의 getRoleList()의 if문에 왔습니다");
            return Arrays.asList(this.role.split(","));
        }
        return new ArrayList<>();
    }
}
