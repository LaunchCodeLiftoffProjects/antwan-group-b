package org.launchcode.FamilyOrganizer.models;

import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

public class User extends AbstractEntity{

    @OneToMany(mappedBy = "User")
    private final List<Event> events = new ArrayList();


}
