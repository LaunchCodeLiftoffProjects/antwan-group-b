package org.launchcode.FamilyOrganizer.models;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User extends AbstractEntity {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @NotNull
    private String username;

    @NotNull
    private String pwHash;

    @NotNull
    @NotBlank
    @Size(message="Must be at least 3 characters long")
    private String familyName;

//    @OneToMany
//    private List<GroceryListItem> groceryListItems;


    public User() {}

    public User(String username, String password, String familyName) {
        this.username = username;
        this.pwHash = encoder.encode(password);
        this.familyName = familyName;
;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getUsername() {
        return username;
    }

    public boolean isMatchingPassword(String password) {
        return encoder.matches(password, pwHash);
    }

//    public List<GroceryListItem> getGroceryListItems() {
//        return groceryListItems;
//    }
//
//    public void setGroceryListItems(List<GroceryListItem> groceryListItems) {
//        this.groceryListItems = groceryListItems;
//    }
}