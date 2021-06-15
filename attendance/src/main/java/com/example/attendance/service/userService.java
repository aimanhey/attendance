package com.example.attendance.service;

import com.example.attendance.exception.CustomException;
import com.example.attendance.model.user;
import com.example.attendance.repository.userRespository;
import com.example.attendance.security.JwtTokenProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class userService {
    
    @Autowired
    private userRespository userRepository;
  
    @Autowired
    private PasswordEncoder passwordEncoder;
  
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
  
    @Autowired
    private AuthenticationManager authenticationManager;


    public String signup(user user) {
        if (!userRepository.existsByUsername(user.getUsername())) {
          user.setPassword(passwordEncoder.encode(user.getPassword()));
          userRepository.save(user);
          return jwtTokenProvider.createToken(user.getUsername(), user.getRoles());
        } else {
          throw new CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
        }
      }
    

}
