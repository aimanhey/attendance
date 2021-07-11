package com.example.attendance.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import com.example.attendance.dto.attendanceDTO;
import com.example.attendance.dto.userDTO;
import com.example.attendance.model.Attendance;
import com.example.attendance.model.user;
import com.example.attendance.service.attendanceService;
import com.example.attendance.service.userService;

import org.hibernate.query.criteria.internal.expression.function.CurrentDateFunction;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.multipart.MultipartFile;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;

@RestController
@RequestMapping("/attendance")
@Api(tags = "attendance")
public class attendanceController {

    @Autowired
    private attendanceService attendanceService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private attendanceDTO attendance;

    @PostMapping("/attend")
    @ApiOperation(value = "${AttendanceController.attend}")
    @ApiResponses(value = { //
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 422, message = "Invalid username/password supplied") })
    public String attend(//
            @ApiParam("Username") @RequestPart String username, //
            @ApiParam("Picture") @RequestPart MultipartFile evidence) {
                String fileName = StringUtils.cleanPath(evidence.getOriginalFilename());
               
                attendance.setUsername(username);
                attendance.setImage(fileName);
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
                Date date = new Date();  
                System.out.println(formatter.format(date));  
                attendance.setDate(date);
               // attendanceService.save(attendance);
         
        return attendanceService.save(modelMapper.map(attendance, Attendance.class));
    }

}
