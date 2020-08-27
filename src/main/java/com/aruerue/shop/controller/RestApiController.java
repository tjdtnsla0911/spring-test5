package com.aruerue.shop.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aruerue.shop.config.auth.PrincipalDetails;
import com.aruerue.shop.model.user.User;
import com.aruerue.shop.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class RestApiController {

	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	// 모든 사람이 접근 가능
	@GetMapping("home")
	public String home() {
		System.out.println("Controller.RestApiController.java의 home에 왔습니다");
		return "<h1>home</h1>";
	}

	// Tip : JWT를 사용하면 UserDetailsService를 호출하지 않기 때문에 @AuthenticationPrincipal 사용
	// 불가능.
	// 왜냐하면 @AuthenticationPrincipal은 UserDetailsService에서 리턴될 때 만들어지기 때문이다.

	// 유저 혹은 매니저 혹은 어드민이 접근 가능
	@GetMapping("user")
	public String user(Authentication authentication) {
		System.out.println("controller.RestApiController.user에 왔습니다");
		PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
		System.out.println("controller.RestApiController.user의 principal= " + principal);
		System.out.println("principal : " + principal.getUser().getId());
		System.out.println("principal : " + principal.getUser().getUsername());
		System.out.println("principal : " + principal.getUser().getPassword());
		System.out.println("principal : " + principal.getUser().getRole());
		return "user";
	}

	// 매니저 혹은 어드민이 접근 가능
	@GetMapping("manager/reports")
	public String reports() {
		System.out.println("Controller.RestApiController.java의 manager/reports에 왔습니다");
		return "reports";
	}

	// 어드민이 접근 가능
	@GetMapping("admin/users")
	public List<User> users() {
		System.out.println("controller.RestApiController.java의 users에 왔습니다.");
		return userRepository.findAll();
	}

	//일반 회원가입
	@PostMapping("join")
	public String join(@RequestBody User user) {
		System.out.println("join에왔습니다 ");
		System.out.println("받은 user = " + user);
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setRole("ROLE_USER");
		user.setProvider("일반회원가입유저");
		user.setProviderId("일반회원가입유저");
		System.out.println("들어가기전의 user = " + user);
		userRepository.save(user);
		System.out.println("---------여기나와라-----------");
		return "1";
	}

////////////////////아르아르 관리자페이지///////////////////////
	@GetMapping("{id}")
	// {/id} 하면안댐
	public User fetchUserById(@PathVariable int id) {
		System.out.println("여기는 fetchUserById에왔습니다");
		User user = userRepository.fetchUserByID(id);
		return user;
	}

	@GetMapping
	public List<User> userList() {
		System.out.println("userList에 왔습니다.");

		return userRepository.findAll();
	}

	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable int id) {
		System.out.println("여기는 deleteUser입니다");
		userRepository.deleteUser(id);
	}

	@PutMapping("/{id}")
	public void updateUser(@PathVariable int id, @RequestBody User user) {
		System.out.println("여긴 updatauser 데스네");
		User users = user;
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userRepository.updateUser(users);
	}

}
