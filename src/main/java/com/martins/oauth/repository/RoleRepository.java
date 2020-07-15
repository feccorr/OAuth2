package com.martins.oauth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.martins.oauth.entity.Role;


public interface RoleRepository extends JpaRepository<Role, Long>{ 

}
