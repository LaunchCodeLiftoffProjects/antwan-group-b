package org.launchcode.FamilyOrganizer.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class ToDoList extends AbstractEntity{
    @ManyToOne
    private User user;

    private String name;

    private String member;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern= "yyyy-MM-dd")
    @Column(name="startdate")
    private Date start;


    @Temporal(TemporalType.TIME)
    @DateTimeFormat(pattern= "HH:mm")
    @Column(name="starttime")
    private Date starttime;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern= "yyyy-MM-dd")
    @Column(name="enddate")
    private Date enddate;


    @Temporal(TemporalType.TIME)
    @DateTimeFormat(pattern= "HH:mm")
    @Column(name="endtime")
    private Date endtime;

    public ToDoList(String name, String member, Date start, Date starttime, Date enddate, Date endtime,  User user) {
        this.name = name;
        this.member = member;
        this.start = start;
        this.starttime = starttime;
        this.enddate = enddate;
        this.endtime = endtime;
        this.user = user;
    }

    public ToDoList(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public Date  getStart() {
        return start;}

    public void setStart(Date  start){this.start = start;}

    public Date  getStarttime() {
        return starttime;}

    public void setStarttime(Date  starttime){this.starttime = starttime;}

    public Date  getEnddate() {
        return enddate;}

    public void setEnddate(Date  enddate){this.enddate = enddate;}

    public Date  getEndtime() {
        return endtime;}

    public void setEndtime(Date  endtime){this.endtime = endtime;}

    @Override
    public String toString() {
        return member+": "+name;
    }
}
