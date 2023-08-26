package com.shyamal.powerledger.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.shyamal.powerledger.model.CustomUser;
import com.shyamal.powerledger.model.CustomUserDetail;
import com.shyamal.powerledger.repository.CustomUserRepository;

@Component
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	public CustomUserRepository customUserRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<CustomUser> customUser= customUserRepository.findByName(username);
	
		CustomUserDetail customUserDetail=customUser.map(CustomUserDetail::new)
			.orElseThrow(()->new UsernameNotFoundException("User Not Found:"+username));
		return customUserDetail;
	}

}
