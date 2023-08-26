package com.shyamal.powerledger.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shyamal.powerledger.model.CustomUser;

//@Repository
public interface CustomUserRepository extends JpaRepository<CustomUser, Integer>{

	Optional<CustomUser> findByName(String username);

}
