package com.example.attendance.service;

import com.example.attendance.exception.CustomException;
import com.example.attendance.model.NotificationEmail;
import com.example.attendance.model.Role;
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

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

  @Autowired
  private EmailService emailService;

  public Object signup(Users user) {
    if (!userRepository.existsByUsername(user.getUsername())) {
      user.setPassword(passwordEncoder.encode(user.getPassword()));
      System.out.println("ludah");
      if(user.getRoles()==null){
        List<Role> list=new ArrayList<Role>();
        list.add(Role.ROLE_CLIENT);
        user.setRoles(list);
      }
      userRepository.save(user);
      String token = jwtTokenProvider.createToken(user.getUsername(), user.getRoles());
      Map<String, Object> map = new HashMap<String, Object>();
      map.put("id", user.getId());
      map.put("email", user.getEmail());
      map.put("username", user.getUsername());
      map.put("name", user.getName());
      map.put("ROLE", user.getRoles());

      Map<String, Object> maps = new HashMap<String, Object>();
      maps.put("data", map);
      maps.put("token", token);
      emailService.sendMail(new NotificationEmail("Please Activate your Account",
      user.getEmail(), "Thank you for signing up to Spring Reddit, " +
      "please click on the below url to activate your account : " +
      "http://localhost:8080/api/auth/accountVerification/" + token));
      return maps;

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

  public List<Users> getUsers(){
    return  userRepository.findAll();
  }

}
