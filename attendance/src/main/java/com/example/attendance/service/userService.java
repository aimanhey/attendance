package com.example.attendance.service;

import com.example.attendance.exception.CustomException;
import com.example.attendance.model.Users;
import com.example.attendance.repository.UserRespository;
import com.example.attendance.security.JwtTokenProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    @Autowired
    private UserRespository userRepository;
  
    @Autowired
    private PasswordEncoder passwordEncoder;
  
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
  
    @Autowired
    private AuthenticationManager authenticationManager;


    public String signup(Users user) {
        if (!userRepository.existsByUsername(user.getUsername())) {
          user.setPassword(passwordEncoder.encode(user.getPassword()));
          userRepository.save(user);
          return jwtTokenProvider.createToken(user.getUsername(), user.getRoles());
        } else {
          throw new CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
        }
      }

      public String signin(String username, String password) {
        try {
          authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
          return jwtTokenProvider.createToken(username, userRepository.findByUsername(username).getRoles());
        } catch (AuthenticationException e) {
          throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
        }
      }
    

}
