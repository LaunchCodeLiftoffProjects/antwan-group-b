package org.launchcode.FamilyOrganizer.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;


@Entity
public class EventDetails extends AbstractEntity{

    @DateTimeFormat(pattern= "dd-MM-yyyy HH:mm:ss")
    @Column(name="date")
    private Date date;


    @Size(max=100, message = "Name too long")
    private String name;

    @Size(max = 200, message = "Location too long!")
    private String location;

    @Size(max = 500, message = "Description too long!")
    private String notes;

    @OneToOne
    private Event event;

    public EventDetails(){

    }

   public EventDetails(Date date, String name, String location, String notes) {
        this.date = date;
        this.name = name;
        this.location = location;
        this.notes = notes;
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

    public void setDescription(String notes) {
        this.notes = notes;
    }
}
