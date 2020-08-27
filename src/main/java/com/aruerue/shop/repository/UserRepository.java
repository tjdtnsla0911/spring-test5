package com.aruerue.shop.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.aruerue.shop.controller.dto.ProductDto;
import com.aruerue.shop.model.user.User;

public interface UserRepository{
	User findByUsername(String username);
	List<User> findAll();
	public User saveGoogle(User user);
	public void save(User user);
	List<ProductDto> productDto();
	void deleteUser(int id);
	void updateUser(User user);
	User fetchUserByID(int id);

}
