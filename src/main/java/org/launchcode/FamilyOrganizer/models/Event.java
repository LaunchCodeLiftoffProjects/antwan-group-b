package org.launchcode.FamilyOrganizer.models;

import org.springframework.context.annotation.Scope;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Scope("session")
public class Event extends AbstractEntity{

    @NotBlank(message = "Name is required.")
    @Size(min=3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @Valid
    @NotNull
    public EventDetails eventDetails;

    @ManyToOne
 //   @NotNull
    private User user;

    public Event(String name, EventDetails eventDetails, User user) {
        this.name = name;
        this.eventDetails = eventDetails;
        this.user = user;
    }

    public Event() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EventDetails getEventDetails() {
        return eventDetails;
    }


    public void setEventDetails(EventDetails eventDetails) {
        this.eventDetails = eventDetails;

    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return name;
    }

}
