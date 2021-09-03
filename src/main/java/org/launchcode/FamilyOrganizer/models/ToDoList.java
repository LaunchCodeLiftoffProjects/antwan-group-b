package org.launchcode.FamilyOrganizer.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
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

    private @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date start;

    public ToDoList(String name, String member, Date start, User user) {
        this.name = name;
        this.member = member;
        this.start = start;
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

    @Override
    public String toString() {
        return member+": "+name;
    }
}
