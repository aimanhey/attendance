package com.example.attendance.repository;

import com.example.attendance.model.Attendance;
import com.example.attendance.model.user;

import org.springframework.data.jpa.repository.JpaRepository;


public interface attendanceRepository extends JpaRepository<Attendance,Integer> {

    Attendance searchUser(String username);

}
