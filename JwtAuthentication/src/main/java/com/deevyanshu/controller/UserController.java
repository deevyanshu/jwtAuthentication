package com.deevyanshu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deevyanshu.Service.UserService;
import com.deevyanshu.entity.User;
import com.deevyanshu.entity.UserRequest;
import com.deevyanshu.entity.UserResponse;
import com.deevyanshu.util.JwtUtil;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@Autowired
	private JwtUtil util;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/save")
	public Integer save(@RequestBody User user)
	{
		return service.save(user);
	}

	@PostMapping("/login")
	public UserResponse login(@RequestBody UserRequest request)
	{
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		String token=util.generateToken(request.getUsername());
		return new UserResponse(token,"generated");
	}
	
	@GetMapping("/welcome")
	public String afterLogin()
	{
		return "hello";
	}
}
