package com.example.attendance.dto;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class attendanceDTO {

   
    @ApiModelProperty(position = 0)
    private String username;
    @ApiModelProperty(position = 1)
    private Date date;
    @ApiModelProperty(position = 2)
    private String image;




    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username=username;
    }

   

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setDate(String format) {
    }
    
}
