package com.example.attendance.model;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "attendance")
public class Attendance {
    

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer attendId;

    @Column(name="username")
    private String username;

    @Column(name="date")
    private Date date;

    @Column(name="image_location")
    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id", referencedColumnName = "id")
    private Users user;
/*

    public Integer getAttendId(){
        return attendId;
    }

    public void setAttendId(Integer attendId){
        this.attendId=attendId;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username=username;
    }
    public Date getDate(){
        return date;
    }

    public void setDate(Date date){
        this.date=date;
    }

    public String getImage(){
        return image;
    }

    public void setImage(String image){
        this.image=image;
    }
*/    




}
