package com.aruerue.shop.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aruerue.shop.config.jwt.JwtProperties;
import com.aruerue.shop.config.ouath.provider.CommonUser;
import com.aruerue.shop.config.ouath.provider.GoogleUser;
import com.aruerue.shop.config.ouath.provider.OAuthUserInfo;
import com.aruerue.shop.controller.dto.ProductDto;
import com.aruerue.shop.model.user.User;
import com.aruerue.shop.repository.UserRepository;
import com.aruerue.shop.service.UserService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class JwtCreateController {

	private final UserRepository userRepository;
	private final UserService userService;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;




	@GetMapping("/test/index")
	public List<ProductDto> asdf() {
		System.out.println("asdf에 왔습니다.");
		List<ProductDto> dto = userRepository.productDto();
		return dto;
	}




//일반유저 로그인
	@PostMapping("/oauth/jwt/common")
	public String commonlogin(@RequestBody Map<String, Object> data) {
		System.out.println("controller.JwtCreateController.java의 jwtCreate에 왔습니다 ");
		System.out.println("여긴 데이터 data = "+data);
		CommonUser commonUser =
				new CommonUser((Map<String, Object>)data);
		System.out.println("------------------------------------------------------------");
		System.out.println("googleUser.getProvider() = "+commonUser.getProvider());
		System.out.println("googleUser.getProviderId() = "+commonUser.getProviderId());
		User userEntity =
				userRepository.findByUsername(commonUser.getName());
		System.out.println("controller.JwtCreateController.java의 jwtCreate의 userEntity = "+userEntity);



		if(userEntity == null) {

			System.out.println("아이디가 틀렸거나 아이디가 없습니다, 회원가입으로 이동");
			return "안녕";

//			System.out.println("controller.JwtCreateController.java의 jwtCreate의 if(userEntity == null)에 왔습니다 ");
//
//
//			User userRequest = User.builder()
//					.username(commonUser.getProvider()+"_"+commonUser.getName())
//					.password(bCryptPasswordEncoder.encode(commonUser.getPassword()))
//					.email(commonUser.getEmail())
//					.provider(commonUser.getProvider())
//					.providerId(commonUser.getProviderId())
//					.roles("ROLE_USER")
//					.build();
//
//			userEntity = userRepository.save(userRequest);
//			System.out.println("controller.JwtCreateController.java의 jwtCreate의 if(userEntity == null)의 userEntity = "+userEntity);
		}

//		boolean checkPassword = bCryptPasswordEncoder.matches((String)data.get("password"), userEntity.getPassword());
//		System.out.println("유저의 비번1 ="+userEntity.getPassword());
//		System.out.println("유저의 비번2 ="+data.get("password"));
//		System.out.println("비번 체크 = "+checkPassword);
//		if(!checkPassword) {
//			System.out.println("비밀번호가 틀렸습니다, 회원가입으로 이동");
//			return "안녕";
//		}

		String jwtToken = JWT.create()
				.withSubject(userEntity.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis()+JwtProperties.EXPIRATION_TIME))
				.withClaim("id", userEntity.getId())
				.withClaim("username", userEntity.getUsername())
				.sign(Algorithm.HMAC512(JwtProperties.SECRET));
		System.out.println("controller.JwtCreateController.java의 jwtCreate의 jwtToken = "+jwtToken);

		return jwtToken;
	}






	//구글 로그인
	@PostMapping("/oauth/jwt/google")
	public String jwtCreate(@RequestBody Map<String, Object> data) {
		System.out.println("-----------------");
		System.out.println("controller.JwtCreateController.java의 jwtCreate에 왔습니다 ");
		System.out.println("여긴 데이터 data = "+data);
		System.out.println(data.get("profileObj"));//구글에서 주는양식 .

		String jwtToken = userService.유저찾기(data);

		return jwtToken;
		////////////////



		/*
		 * OAuthUserInfo googleUser = new GoogleUser((Map<String,
		 * Object>)data.get("profileObj")); System.out.println(
		 * "------------------------------------------------------------");
		 * System.out.println("googleUser.getProvider() = "+googleUser.getProvider());
		 * System.out.println("googleUser.getProvider() = "+googleUser.getProviderId());
		 * User userEntity =
		 * userRepository.findByUsername(googleUser.getProvider()+"_"+googleUser.
		 * getProviderId()); System.out.
		 * println("controller.JwtCreateController.java의 jwtCreate의 userEntity = "
		 * +userEntity); if(userEntity == null) { System.out.
		 * println("controller.JwtCreateController.java의 jwtCreate의 if(userEntity == null)에 왔습니다 "
		 * ); User userRequest = User.builder()
		 * .username(googleUser.getProvider()+"_"+googleUser.getProviderId())
		 * .password(bCryptPasswordEncoder.encode("겟인데어")) .email(googleUser.getEmail())
		 * .provider(googleUser.getProvider()) .providerId(googleUser.getProviderId())
		 * .role("ROLE_USER") .build(); userEntity =
		 * userRepository.saveGoogle(userRequest); System.out.
		 * println("controller.JwtCreateController.java의 jwtCreate의 if(userEntity == null)의 userEntity = "
		 * +userEntity); }
		 *
		 * String jwtToken = JWT.create() .withSubject(userEntity.getUsername())
		 * .withExpiresAt(new
		 * Date(System.currentTimeMillis()+JwtProperties.EXPIRATION_TIME))
		 * .withClaim("id", userEntity.getId()) .withClaim("username",
		 * userEntity.getUsername()) .sign(Algorithm.HMAC512(JwtProperties.SECRET));
		 * System.out.
		 * println("의controller.JwtCreateController.java의 jwtCreate의jwtTkoen입니다 ="
		 * +jwtToken); return jwtToken;
		 */
		////////////////////////////
	}


}
