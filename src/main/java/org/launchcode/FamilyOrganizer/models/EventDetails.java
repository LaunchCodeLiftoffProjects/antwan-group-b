package org.launchcode.FamilyOrganizer.models;

import net.bytebuddy.asm.Advice;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
public class EventDetails extends AbstractEntity{

    @NotBlank(message = "Event Name is required")
    private String eventName;

    @NotBlank(message = "Start Time is required")
    private Date startTime;

    private Date endTime;

    @Size(max=100, message = "Name too long")
    private String name;

    @Size(max = 200, message = "Location too long!")
    private String location;

    @Size(max = 500, message = "Description too long!")
    private String description;

    @OneToOne
    private Event event;

    public EventDetails(){

    }

    public EventDetails(String eventName) {
        this.eventName = eventName;
    }

//    public EventDetails(String eventName, Date startTime, Date endTime, String name, String location, String description) {
//        this.eventName = eventName;
//        this.startTime = startTime;
//        this.endTime = endTime;
//        this.name = name;
//        this.location = location;
//        this.description = description;
//    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
