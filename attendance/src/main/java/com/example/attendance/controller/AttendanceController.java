package com.example.attendance.controller;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.example.attendance.dto.AttendanceDTO;
import com.example.attendance.model.Attendance;
import com.example.attendance.model.Users;
import com.example.attendance.service.AttendanceService;
import com.example.attendance.service.UserService;

import org.hibernate.query.criteria.internal.expression.function.CurrentDateFunction;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private ModelMapper modelMapper;

    private AttendanceDTO attendance;

    @PostMapping("/attend")
    @ApiOperation(value = "${AttendanceController.attend}")
    @ApiResponses(value = { //
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 422, message = "Invalid username/password supplied") })
    public String attend(//
            @ApiParam("Username") @RequestPart String username, //
            @ApiParam("Picture") @RequestPart MultipartFile evidence,HttpServletRequest req) throws IOException {
        String fileName = StringUtils.cleanPath(evidence.getOriginalFilename());
            
        String mkn= req.getHeader("Authorization");
        System.out.println(mkn);

        Attendance attend = new Attendance();
        attend.setUsername(username);
        attend.setImage(fileName);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        System.out.println(formatter.format(date));
        attend.setDate(date);

        Path uploadPath = Paths.get("./src/main/resources/static/");
        try (InputStream inputStream = evidence.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new IOException("Could not save image file: " + fileName, ioe);
        }

        return attendanceService.save(attend);
        // return attendanceService.save(modelMapper.map(attendance, Attendance.class));
    }

}
