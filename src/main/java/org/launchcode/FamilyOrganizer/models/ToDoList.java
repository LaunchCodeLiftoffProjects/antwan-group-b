package org.launchcode.FamilyOrganizer.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class ToDoList extends AbstractEntity{
    @ManyToOne
    private User user;

    @NotBlank(message= "To Do details is required.")
    @Size(min=2, max=25, message="Must be between 2 and 25 characters long.")
    private String name;

    @NotBlank(message= "Family member is required.")
    @Size(min=2, max=25, message="Must be between 2 and 25 characters long.")
    private String member;


    @Future(message = "Start Date should be greater than today's date")
    @NotNull(message= "Start Date is required")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern= "yyyy-MM-dd")
    @Column(name="startdate")
    private Date start;

    @NotNull(message= "Start Time is required")
    @Temporal(TemporalType.TIME)
    @DateTimeFormat(pattern= "HH:mm")
    @Column(name="starttime")
    private Date starttime;

    @Future(message = "End Date should be greater than today's date")
    @NotNull(message= "End Date is required")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern= "yyyy-MM-dd")
    @Column(name="enddate")
    private Date enddate;

    @NotNull(message= "End Time is required")
    @Temporal(TemporalType.TIME)
    @DateTimeFormat(pattern= "HH:mm")
    @Column(name="endtime")
    private Date endtime;

    //@AssertTrue(message="Start datetime should be less than end datetime")
    public boolean isValidRange() {

        if(this.start.compareTo(this.enddate)==0) {
            if(this.starttime.compareTo(this.endtime)>=0)
            {
                return true;
            }
            else
                return false;
        }
        else if (this.start.compareTo(this.enddate)>0)
        {
            return true;
        }
        else
            return false;
    }

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
