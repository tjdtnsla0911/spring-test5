package com.aruerue.shop.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.aruerue.shop.model.user.User;
import com.aruerue.shop.repository.AddminRepository;
import com.aruerue.shop.repository.UserRepository;

@Controller
public class AddminUserController {

	@Autowired
	AddminRepository addminRepository;

	@Autowired
	UserRepository userRepository;

	@Value("${file.path}")
	private String fileRealPath;

	private User user;

	// 유저 리스트로 가는곳
	@GetMapping("/userlist")
	public String UserList(Model model) {
		List<User> user = userRepository.findAll();
		String http = "http://localhost:8080";

		for(int i=0; i<=user.size()-1;i++) {
			String http2 = http+user.get(i).getProfile();
			user.get(i).setProfile(http2);
		}
		System.out.println(user);
		model.addAttribute("addminUserList", user);

		return "addminUserList";
	}

	@PutMapping("/changeUser/{id}")
	public @ResponseBody String userString(
			MultipartFile profile2 , @ModelAttribute User user) throws IOException {
		System.out.println("user = "+user);

		if(profile2 == null) {
			System.out.println("사진안바꿔서 null에옴");
			userRepository.updateUser(user);
			return "OK";
		}
		System.out.println("null이아닌데옴");
		UUID uuid = UUID.randomUUID();
		String uuidProfile = uuid + "_"+profile2.getOriginalFilename();
		System.out.println("uuidProfile = "+ uuidProfile);
		Path fileProfile = Paths.get(fileRealPath + uuidProfile);
		System.out.println("fileProfile = "+fileProfile);
		Files.write(fileProfile,profile2.getBytes());
		System.out.println("11111111111111");
		String realProfile = "/images/"+uuidProfile;
		System.out.println("realProfile = "+realProfile);
		user.setProfile(realProfile);
		System.out.println("user.getProfile = "+user.getProfile());
		System.out.println("마지막 user = "+user);
		userRepository.updateUser(user);

		System.out.println("null이 아닌데 끝 ^^");
		return "OK";
	}

	@DeleteMapping("/listUserDelete/{id}")
	public @ResponseBody String listDelete(@PathVariable int id) {
		System.out.println("listUserDelete에 왔쪄용!!");
		System.out.println("받은 id = " + id);
		userRepository.deleteUser(id);
//		addminRepository.listdelete(id);
//		addminRepository.listdetete_product_status(id);
		System.out.println("성공한거같은데 ㅅㅂ ");
		return "Ok";
	}

}
