package com.martins.oauth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.martins.oauth.entity.User;

public interface UserRepository  extends JpaRepository<User, Long>{
	
	User getById(Long id);
	
	User findByEmail(String username);
	
}
