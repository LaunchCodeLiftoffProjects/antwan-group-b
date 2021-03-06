package org.launchcode.FamilyOrganizer.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
//@Scope("session")
public class EventDetails extends AbstractEntity implements Comparable<Event>{

   @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern= "yyyy-MM-dd")
    @Column(name="date")
    private Date date;


    @Temporal(TemporalType.TIME)
    @DateTimeFormat(pattern= "HH:mm")
    @Column(name="startTime")
    private Date startTime;

    @Temporal(TemporalType.TIME)
    @DateTimeFormat(pattern= "HH:mm")
    @Column(name="endTime")
    private Date endTime;



    @Size(max=100, message = "Name too long")
    private String name;

    @Size(max = 200, message = "Location too long!")
    private String location;

    @Size(max = 500, message = "Description too long!")
    private String notes;

    @OneToOne
    private Event event;

    @ManyToOne
    //@NotNull
    private User user;

    public EventDetails(){

    }

   public EventDetails(Date date, String name, String location, String notes, Date startTime, Date endTime, User user) {
        this.date = date;
        this.name = name;
        this.location = location;
        this.notes = notes;
        this.user = user;
        this.startTime = startTime;
        this.endTime = endTime;

   }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public User getUser() {
        return user;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public int compareTo(Event o){
        if (getDate() == null || o.eventDetails.getDate() == null)
            return 0;
        return getDate().compareTo(o.eventDetails.getDate());
    }

}
