package com.example.attendance.service;

import com.example.attendance.exception.CustomException;
import com.example.attendance.model.Attendance;
import com.example.attendance.model.Users;
import com.example.attendance.repository.AttendanceRepository;
import com.example.attendance.security.JwtTokenProvider;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class AttendanceService {

  @Autowired
  private AttendanceRepository attendanceRepository;

  public String save(Attendance attendance) {

    attendanceRepository.save(attendance);
    return "Successfully taking attendance";
  }

}
