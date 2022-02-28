package com.example.attendance.repository;

import java.util.Optional;

import com.example.attendance.model.Attendance;
import com.example.attendance.model.Users;

import org.springframework.data.jpa.repository.JpaRepository;


public interface AttendanceRepository extends JpaRepository<Attendance,Integer> {

  //  Optional<Attendance> searchUser(String username);

}
