package org.launchcode.FamilyOrganizer.models;

import com.sun.istack.NotNull;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Scope("session")
public class User extends AbstractEntity {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @NotNull
    private String username;

    @NotNull
    private String pwHash;

    @javax.validation.constraints.NotNull
    @NotBlank
    @Size(message="Must be at least 3 characters long")
    private String familyName;

    public User() {}

    public User(String username, String password, String familyName) {
        this.username = username;
        this.pwHash = encoder.encode(password);
        this.familyName = familyName;
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

}