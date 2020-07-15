package com.martins.oauth.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.martins.oauth.entity.Role;
import com.martins.oauth.entity.User;
import com.martins.oauth.repository.RoleRepository;
import com.martins.oauth.repository.UserRepository;

@Component
public class DataInitializr implements ApplicationListener<ContextRefreshedEvent>{

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository RoleRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

		List<User> users = userRepository.findAll();
		if(users.isEmpty()) {
			this.createUser("admin", "admin", passwordEncoder.encode("123456"),"ROLE_ADMIN");
			this.createUser("Jonh Doe", "foo@foo.com", passwordEncoder.encode("123456"),"ROLE_USER");
			this.createUser("Gandalf", "foo@lotr.com", passwordEncoder.encode("fool"),"ROLE_USER");
			this.createUser("Jack", "foo@chan.com", passwordEncoder.encode("vanilla"),"ROLE_USER");
			this.createUser("Cris", "foo@buble.com", passwordEncoder.encode("coldice"),"ROLE_USER");
		}
		
	}
	
	public void createUser(String name, String email, String password, String role) {
		
		Role roleObject = new Role();
		roleObject.setName(role);
		
		this.RoleRepository.save(roleObject); 
		
		User user =  new User(name, email, password, Arrays.asList(roleObject));
		userRepository.save(user);
	}

}
