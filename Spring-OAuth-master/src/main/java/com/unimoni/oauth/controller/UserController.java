package com.unimoni.oauth.controller;


import com.unimoni.oauth.request.dto.OauthHeader;
import com.unimoni.oauth.request.dto.UserDetailReq;
import com.unimoni.oauth.request.dto.UserReq;
import com.unimoni.oauth.response.dto.OauthDetail;
import com.unimoni.oauth.response.dto.UserDetailDto;
import com.unimoni.oauth.response.dto.UserDto;
import com.unimoni.oauth.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping(value = "/register")
	public UserDto registerUser(@RequestBody UserDetailReq user) {
		return userService.registerUser(user);
	}

	@PostMapping(value = "/login/{userId}")
	public UserDto getUserDetail(@PathVariable(value = "userId") String loginId) {
		
		System.out.println("userId ::::::::::"+loginId);
		return userService.getUserDetail(loginId);
	}

	@PostMapping(value = "/userdetail/{userId}")
	public UserDetailDto getUserAdditionalInfoDetail(@PathVariable(value = "userId") Long userId) {
		return userService.getUserAdditionalInfoDetail(userId).get(0);
	}

	@PostMapping(value = "/userdetail/update")
	public UserDto updateUserDetail(@RequestBody UserDetailReq userDetail) {
		return userService.updateUserDetail(userDetail);
	}
	
	@PostMapping(value = "/userdetail/add")
	public UserDetailDto addUserDetail(@RequestBody UserDetailReq userDetail) {
		return userService.addUserDetail(userDetail);
	}
	
	@PostMapping(value = "/status/update")
	public UserDto updateUserDetail(@RequestBody UserReq userReq) {
		return userService.updateUserStatus(userReq);
	}
	
	
	
	

}
