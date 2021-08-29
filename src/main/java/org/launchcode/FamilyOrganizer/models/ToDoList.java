package org.launchcode.FamilyOrganizer.models;

import javax.persistence.Entity;

@Entity
public class ToDoList extends AbstractEntity{


    private String name;
    private String member;

    public ToDoList(String name, String member) {
        this.name = name;
        this.member = member;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    @Override
    public String toString() {
        return member+": "+name;
    }
}