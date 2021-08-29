package org.launchcode.FamilyOrganizer.models;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import

@Entity
public class User extends AbstractEntity{

    @NotBlank(message = "Name is required.")
    @Size(min=3, max = 50, message = "Name must be between 3 and 50 characters")
    private String username;

    public User(){}

    public User(String username, String password, String familyName) {
        this.username = username;
        this.pwHash = encoder.encode(password);
    }


}
