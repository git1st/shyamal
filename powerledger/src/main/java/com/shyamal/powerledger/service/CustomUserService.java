package com.shyamal.powerledger.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shyamal.powerledger.model.CustomUser;
import com.shyamal.powerledger.repository.CustomUserRepository;

@Service
public class CustomUserService {
	@Autowired
	public CustomUserRepository customUserRepository;
	@Autowired
	public PasswordEncoder encoder;

	public void addUser(CustomUser customUser) {
		customUser.setPassword(encoder.encode(customUser.getPassword()));
		customUserRepository.save(customUser);
	}
}
