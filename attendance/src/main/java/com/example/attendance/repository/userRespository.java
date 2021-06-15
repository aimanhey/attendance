package com.example.attendance.repository;

import com.example.attendance.model.user;

import org.springframework.data.jpa.repository.JpaRepository;


public interface userRespository extends JpaRepository<user,Integer> {

    user findByUsername(String username);

    boolean existsByUsername(String username);
    
}
