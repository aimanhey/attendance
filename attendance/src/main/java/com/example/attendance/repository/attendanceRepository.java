package com.example.attendance.repository;

import java.util.Optional;

import com.example.attendance.model.Attendance;
import com.example.attendance.model.user;

import org.springframework.data.jpa.repository.JpaRepository;


public interface attendanceRepository extends JpaRepository<Attendance,Integer> {

  //  Optional<Attendance> searchUser(String username);

}
