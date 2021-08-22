package org.launchcode.FamilyOrganizer.models;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Entity
public class Event {

    @NotBlank(message = "Name is required.")
    @Size(min=3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;

  //  @OneToOne//(cascade = CascadeType.ALL)
//will make sure that Event is only valid when subobjects such as EventDetails also has valid fields
  //  @Valid
//the below ensures that the eventDetails field in not null.
  //  @NotNull
//the below line relates Event to EventDetails
   // private EventDetails eventDetails;

}
