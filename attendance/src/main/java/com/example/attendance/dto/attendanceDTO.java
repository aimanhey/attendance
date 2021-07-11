package com.example.attendance.dto;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class attendanceDTO {

    @ApiModelProperty(position = 0)
    private Integer attendId;
    @ApiModelProperty(position = 1)
    private String username;
    @ApiModelProperty(position = 2)
    private Date date;
    @ApiModelProperty(position = 3)
    private String image;


    public void setAttendId(Integer attendId) {
        this.attendId = attendId;
    }

    public Integer getAttendId() {
        return attendId;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }
    
}
