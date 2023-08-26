package com.shyamal.powerledger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shyamal.powerledger.model.CustomUser;
import com.shyamal.powerledger.service.CustomUserService;
import com.shyamal.powerledger.service.JWTService;

@RestController
@RequestMapping("/API/")
public class CustomUserController {
	
	@Autowired
	public CustomUserService customUserService;
	
	@Autowired
	public JWTService jwtService;
	
	@Autowired
	public AuthenticationManager authenticationManager;

	@PostMapping("addUser")
	//@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String save(@RequestBody CustomUser customUser ) {
		customUserService.addUser(customUser);
		return "New User Added successfully";
	}
	
	@PostMapping("/getJWTToken")
	public String getJWTToken(@RequestBody CustomUser userRequest) {
		Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userRequest.getName(), userRequest.getPassword()));
		if(authentication.isAuthenticated())
		{
			return jwtService.generateToken(userRequest.getName());
		}
		else 
		{
			throw new UsernameNotFoundException("User Request not found");
		}
	}
	
}
