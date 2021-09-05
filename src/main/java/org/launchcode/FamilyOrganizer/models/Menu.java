package org.launchcode.FamilyOrganizer.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Menu extends AbstractEntity {

    @ManyToOne
    private User user;

    //@NotNull(message = "Date is required.")
    //private Date date;


//private  dateFormat = DateFormat.getDateInstance(Full).format(date);

    @NotNull(message = "Main Course is required.")
    @Size(max = 250, message = "Main course cannot exceed 250 characters.")
    private String mainCourse;

    @Size(max = 100, message = "Vegetables cannot exceed 100 characters.")
    private String vegetable;

    @Size(max = 100, message =  "Main Side cannot exceed 100 characters.")
    private String mainSide;

    @Size(max = 100, message = "Additional Side cannot exceed 100 characters.")
    private String additionalSide;

    @Size(max = 100, message = "Dessert cannot exceed 100 characters.")
    private String dessert;

    public Menu(User user, /*Date date,*/ String mainCourse, String vegetable, String mainSide, String additionalSide, String dessert) {
        this.user = user;
        //this.date = date;
        this.mainCourse = mainCourse;
        this.vegetable = vegetable;
        this.mainSide = mainSide;
        this.additionalSide = additionalSide;
        this.dessert = dessert;
    }

    public Menu() {

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /*public Date getDate() {
        return date;
    }*/

    public String getMainCourse() {
        return mainCourse;
    }

    public void setMainCourse(String mainCourse) {
        this.mainCourse = mainCourse;
    }

    public String getVegetable() {
        return vegetable;
    }

    public void setVegetable(String vegetable) {
        this.vegetable = vegetable;
    }

    public String getMainSide() {
        return mainSide;
    }

    public void setMainSide(String mainSide) {
        this.mainSide = mainSide;
    }

    public String getAdditionalSide() {
        return additionalSide;
    }

    public void setAdditionalSide(String additionalSide) {
        this.additionalSide = additionalSide;
    }

    public String getDessert() {
        return dessert;
    }

    public void setDessert(String dessert) {
        this.dessert = dessert;
    }
}
