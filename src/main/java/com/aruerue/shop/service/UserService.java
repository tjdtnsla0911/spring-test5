package com.aruerue.shop.service;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aruerue.shop.config.jwt.JwtProperties;
import com.aruerue.shop.config.ouath.provider.GoogleUser;
import com.aruerue.shop.config.ouath.provider.OAuthUserInfo;
import com.aruerue.shop.model.user.User;
import com.aruerue.shop.repository.UserRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Transactional
	public User 회원가입(User user) {
			user.setRole("ROLE_USER");
			userRepository.save(user);
			return userRepository.findByUsername(user.getUsername());
	}

	//일반 로그인?
	@Transactional(readOnly = true) //데이터 변경을 허용하지 않음 (혹시 이 트랜젝션이 끝나기 전에
	//다른 사용자가 데이터를 변경한 내용 때문에 엉키지않게 하려고)=> 정합성을 위해서
		public User 일반로그인(User user) {
		System.out.println(user);

			return userRepository.findByUsername(user.getUsername());
	}


	//oauth로 login할 때
	@Transactional
	public String 유저찾기(Map<String, Object> data) {
		System.out.println("유저찾기");
		///////////
		OAuthUserInfo googleUser =
				new GoogleUser((Map<String, Object>)data.get("profileObj"));
		System.out.println("------------------------------------------------------------");
		System.out.println("googleUser.getProvider() = "+googleUser.getProvider());
		System.out.println("googleUser.getProvider() = "+googleUser.getProviderId());
		System.out.println("kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
		User userEntity =
				userRepository.findByUsername(googleUser.getProvider()+"_"+googleUser.getProviderId());
		System.out.println("Service.UserService.의 jwtCreate의 userEntity = "+userEntity);
		if(userEntity == null) {
			System.out.println("Service.UserService.의 jwtCreate의 if(userEntity == null)에 왔습니다 ");
			User userRequest = User.builder()
					.username(googleUser.getProvider()+"_"+googleUser.getProviderId())
					.password(bCryptPasswordEncoder.encode("겟인데어"))
					.email(googleUser.getEmail())
					.provider(googleUser.getProvider())
					.providerId(googleUser.getProviderId())
					.role("ROLE_USER")
					.build();
			System.out.println("mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm");
			System.out.println(userEntity);
			userEntity = userRepository.saveGoogle(userRequest);
			System.out.println("Service.UserService.java의 jwtCreate의 if(userEntity == null)의 userEntity = "+userEntity);
		}

		String jwtToken = JWT.create()
				.withSubject(userEntity.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis()+JwtProperties.EXPIRATION_TIME))
				.withClaim("id", userEntity.getId())
				.withClaim("username", userEntity.getUsername())
				.sign(Algorithm.HMAC512(JwtProperties.SECRET));
		System.out.println("Service.UserService..java의 jwtCreate의jwtTkoen입니다 ="+jwtToken);
		return jwtToken;
		//////////////


			//return userRepository.findByUsername(useranme);
	}




}
