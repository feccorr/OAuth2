package com.martins.oauth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.test.OAuth2ContextConfiguration.Password;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.martins.oauth.entity.User;
import com.martins.oauth.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {
	
	
	@Autowired
	UserRepository userRepository;
	

	
	@Secured({"ROLE_ADMIN"})
	@RequestMapping(value = "", method = RequestMethod.GET)
	public Page<User> list(
			@RequestParam("page") int page,
			@RequestParam("size") int size
			){
		
		Pageable pageable = PageRequest.of(page, size);
		
		return userRepository.findAll(pageable);
	}
	
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public User save(@RequestBody User user){
		return userRepository.save(user);
	}
	
	@RequestMapping(value = "", method = RequestMethod.PUT)
	public User edit(@RequestBody User user){
		return userRepository.save(user);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id){
		 userRepository.deleteById(id);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public User detail(@PathVariable Long id){
		 return userRepository.findById(id).orElse(null);
	}
}
 