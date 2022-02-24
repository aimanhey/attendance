package com.example.attendance.repository;

import java.util.List;

import com.example.attendance.model.Users;

import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRespository extends JpaRepository<Users,Integer> {

    Users findByUsername(String username);

    boolean existsByUsername(String username);

    Users findByName(String name);

    List<Users> findAll();
    
}
