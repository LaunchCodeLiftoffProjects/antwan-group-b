package org.launchcode.FamilyOrganizer.models;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;

@Entity
public class EventDetails extends AbstractEntity{



    @NotNull(message = "Start Time is required")
    private double startTime;


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

   public EventDetails(double startTime, Date endTime, String name, String location, String notes) {
        this.startTime = startTime;
        this.name = name;
        this.location = location;
        this.notes = notes;
   }



    public double getStartTime() {
        return startTime;
    }

    public void setStartTime(double startTime) {
        this.startTime = startTime;
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
