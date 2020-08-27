package com.aruerue.shop.controller;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.resource.HttpResource;

import com.aruerue.shop.addminDto.AddminDto;
import com.aruerue.shop.controller.dto.ProductDto;
import com.aruerue.shop.controller.dto.ResponseDto;
import com.aruerue.shop.model.product.Product;
import com.aruerue.shop.model.product.Related_product;
import com.aruerue.shop.model.user.User;
import com.aruerue.shop.repository.AddminRepository;

@Controller
//@MultipartConfig(maxFileSize=1024*1024*2,location = "c:\\upload")
public class AddminController {


	@Value("${file.path}")
	private String fileRealPath;

	@Autowired
	private AddminRepository addminRepository;


	private AddminDto addminDto;

	@GetMapping("/list2")
	public @ResponseBody List<Product> list2(Model model) {
		addminRepository.productList();
		System.out.println("결과 = " + addminRepository.productList());
		model.addAttribute("productList", addminRepository.productList());
		System.out.println("왓니");
		return addminRepository.productList();
	}

	@GetMapping("/relatedProducts")
	public String relatedProductsList(Model model) {

		model.addAttribute("relatedProduct", addminRepository.relatedProduct());


		return "relatedProductList"; // <--여기서 관련상품리스트로 보냄
	}

	@GetMapping("/list")
	public String list(Model model) {
		// List<AddminDto> add = addminRepository.relatedProduct();
		System.out.println("list에 왔습니다.");
		String http = "http://localhost:8080";
		List<Product> lists = addminRepository.productList();

		for( int i=0; i<=lists.size()-1;i++) {
		System.out.println(http+lists.get(i).getBgImg());
		String http2 = http+lists.get(i).getBgImg();
		lists.get(i).setBgImg(http2);
		System.out.println("lists = "+lists.get(i).getBgImg());
		//System.out.println(lists.get(i).setBgImg(http+lists.get(i).getBgImg()));

		}

//http://localhost:8080/images/cef5c43d-3781-448e-b7d9-8462d566fcfa_kocs.jpg
		System.out.println("lists의 결과  = "+lists);
		System.out.println("결과 = " + addminRepository.productList());

		model.addAttribute("productList", addminRepository.productList());
		// model2.addAttribute("relatedProduct",add);

		return "addList";
	}

	@GetMapping("/axios")
	public String index(Model model) {
		List<AddminDto> pro = addminRepository.addminDtoList();
		model.addAttribute("list", pro);
		System.out.println("axios 탓다");
		return "index";
	}
	@PostMapping("/instar")
	public String insert(@RequestParam("file") MultipartFile file) throws IOException {
		System.out.println("오긴옴");
		System.out.println("리얼패쓰 = "+fileRealPath);
		UUID uuid = UUID.randomUUID();
		System.out.println("1111111111111111");
		String uuidFilename = uuid + "_"+file.getOriginalFilename();
		System.out.println("222222222222222222222");
		Path filePath = Paths.get(fileRealPath + uuidFilename);
		System.out.println("filePath = "+filePath);
		System.out.println("파일패쓰 = "+filePath);
		System.out.println("33333333333333333333333");
		Files.write(filePath, file.getBytes());
		String realuuidFilename = "/imags/"+uuidFilename;
		System.out.println("ㅠㅠ아이디= "+realuuidFilename);
		System.out.println("4444444444444444444444");

		return "ass";
	}
	//여기가 물풍 적은곳 받아서 db에 올리는곳
	@PostMapping("/crud")
	public  String crud(@RequestParam("file") MultipartFile file,@ModelAttribute AddminDto addminDto) throws IOException {
		System.out.println("일단 crud에 왔습니다");
		UUID uuid = UUID.randomUUID();
		System.out.println("111111111111111");
		String uuidFilename = uuid + "_"+file.getOriginalFilename();
		System.out.println("22222222222222222");
		Path filePath = Paths.get(fileRealPath + uuidFilename);
		System.out.println("filePath = "+fileRealPath);
		Files.write(filePath, file.getBytes());

		String realuuidFilename = "/images/"+uuidFilename;
		addminDto.setBgImg(realuuidFilename);
		System.out.println("바뀐 bgImg = "+addminDto.getBgImg());
		System.out.println("filePath = "+filePath);
		System.out.println("파일패쓰 = "+filePath);
		System.out.println("33333333333333333333333");
		System.out.println("crud에 쳐왔다");
		System.out.println("처음온 addminDto.getParentProductId()? = " + addminDto.getParentProductId());
		if (addminDto.getRadioAd().equals("true")) {
			addminDto.setRadioAd2(true);
		} else {
			addminDto.setRadioAd2(false);
		}

		if (addminDto.getRadioBest().equals("true")) {
			addminDto.setRadioBest2(true);
		} else {
			addminDto.setRadioBest2(false);
		}

		if (addminDto.getRadioNew().equals("true")) {
			addminDto.setRadioNew2(true);
		} else {
			addminDto.setRadioNew2(false);
		}

		if (addminDto.getRadioSale().equals("true")) {
			addminDto.setRadioSale2(true);
		} else {
			addminDto.setRadioSale2(false);
		}
		if (addminDto.getRadioBest().equals("true")) {
			addminDto.setRadioBest2(true);
		} else {
			addminDto.setRadioBest2(false);
		}

		// values(#{title},#{thumb},#{price},#{disc},#{radioAd2},#{discounted},#{content},#{radioParentTypeId},#{bgImg})
		System.out.println("다하고 나서 title = " + addminDto.getTitle());
		System.out.println("다하고 나서 thumb = " + addminDto.getThumb());
		System.out.println("다하고 나서 price = " + addminDto.getPrice());
		System.out.println("다하고 나서 disc = " + addminDto.getDisc());
		System.out.println("다하고 나서 radioAd2 = " + addminDto.isRadioBest2());

		System.out.println("다하고 나서 discounted = " + addminDto.getDiscounted());
		System.out.println("다하고 나서 content = " + addminDto.getContent());
		System.out.println("다하고 나서 radioParentTypeId = " + addminDto.getRadioParentTypeId());
		//System.out.println("다하고 나서 bgImg = " + addminDto.getBgImg());



		addminRepository.saveProduct(addminDto);

		int Productid = addminDto.getId();
		System.out.println("진짜로 가져왔는가 ? = " + Productid);
		// AddminDto addminDto2= addminRepository.selectProduct(addminDto);
		addminDto.setProductid(Productid);
		addminRepository.saveProduct_status(addminDto);
		System.out.println("addminDto.getParentProductId()는 ? = " + addminDto.getParentProductId());
		if (addminDto.getParentProductId() == 1) {

			System.out.println("addminDto.getParentProductId() == 1에 왔습니다");
			addminDto.setParentProductId(Productid);
			addminRepository.saveRelated_product(addminDto);
		} else {

			addminRepository.saveRelated_product(addminDto);
		}

		System.out.println("여기까오면 save는된거임");

		// System.out.println("여기까지오면 select는한거임 결과 = "+addminDto2);
		// System.out.println("productId에 넣을꺼 = "+addminDto2.getId());

		System.out.println("무사히 넣은듯?");

		return "redirect:/axios";
	}
//http://localhost:8080/upload/kocs.png
	// 등록물품 삭제하는곳
	@DeleteMapping("/listDelete/{id}")
	public @ResponseBody String listDelete(@PathVariable int id) {
		System.out.println("listDelete에 왔쪄용!!");
		System.out.println("받은 id = " + id);
		addminRepository.listdelete(id);
		addminRepository.listdetete_product_status(id);
		System.out.println("성공한거같은데 ㅅㅂ ");
		return "Ok";
	}

	// 등록물품
	@PutMapping("/change/{id}")
	public @ResponseBody String updata(@PathVariable int id, @RequestBody Product product) {
		System.out.println("가져온 체인지값 = " + product);
		System.out.println("sdfsdf = " + product.getChangebgImg());
		if (!product.getChangebgImg().equals("null")) {
			System.out.println("if문에 왔어요");
			System.out.println("if문 ㅅㅂ = " + product.getChangebgImg());
			product.setBgImg(product.getChangebgImg());
		}
		System.out.println("체인지에왔음");

		System.out.println("product = " + product);
		addminRepository.updata(product);

		return "OK";

	}

	@GetMapping("/sss")
	public String asdf() {
		return "ass";
	}

}