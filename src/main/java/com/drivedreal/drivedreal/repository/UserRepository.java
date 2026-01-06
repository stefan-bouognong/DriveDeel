package com.drivedreal.drivedreal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.drivedreal.drivedreal.entity.User;

public interface UserRepository  extends JpaRepository<User, Long>{

     Optional<User> findByEmail(String email);
}


