package com.aruerue.shop.repository;

import java.util.List;

import com.aruerue.shop.addminDto.AddminDto;
import com.aruerue.shop.model.product.Category;
import com.aruerue.shop.model.product.Product;
import com.aruerue.shop.model.product.Product_status;
import com.aruerue.shop.model.product.Related_product;
import com.aruerue.shop.model.user.User;

public interface AddminRepository {
	///////////등록할떄 이거 3개가 동시에 Insert되게함.////////////////////////
	List<AddminDto> addminDtoList();
	public int saveProduct(AddminDto addminDto);
	public void saveRelated_product(AddminDto addminDto);
	public void saveProduct_status(AddminDto addminDto);

	public AddminDto selectProduct(AddminDto addminDto);
	///////////////////////////////////////////////////////////////

	List<Product> productList();

	List<AddminDto> relatedProduct();
	//////////////////////////////////////////////////
	public int listdelete(int id);
	public int listdetete_product_status(int id);

	///////////////////////////////////////////
	public void updata(Product product);
	public Product selectUpdate(int id);

	/////////////////////////////////////

}
