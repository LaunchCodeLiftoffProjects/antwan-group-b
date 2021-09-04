package org.launchcode.FamilyOrganizer.models;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Menu extends AbstractEntity {

    //@ManyToOne
    //private User userid;

    //@NotNull(message = "Date is required.")
    //private Date date;


//private  dateFormat = DateFormat.getDateInstance(Full).format(date);

    @NotNull(message = "Main Course is required.")
    @Size(max = 250, message = "Main course cannot exceed 250 characters.")
    private String main_course;

    @Size(max = 100, message = "Vegetables cannot exceed 100 characters.")
    private String vegetable;

    @Size(max = 100, message =  "Main Side cannot exceed 100 characters.")
    private String main_side;

    @Size(max = 100, message = "Additional Side cannot exceed 100 characters.")
    private String additional_side;

    @Size(max = 100, message = "Dessert cannot exceed 100 characters.")
    private String dessert;

    public Menu(/*User userID, Date date,*/ String main_course, String vegetable, String main_side, String additional_side, String dessert) {
        //this.id = userid;
        //this.date = date;
        this.main_course = main_course;
        this.vegetable = vegetable;
        this.main_side = main_side;
        this.additional_side = additional_side;
        this.dessert = dessert;
    }

    public Menu() {

    }

    /*public User getUserId() {
        return userId;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }*/

    public String getMain_course() {
        return main_course;
    }

//    public void setMain_course(String main_course) {
//        this.main_course = main_course;
//    }

    public String getVegetable() {
        return vegetable;
    }

//    public void setVegetable(String vegetable) {
//        this.vegetable = vegetable;
//    }

    public String getMain_side() {
        return main_side;
    }

//    public void setMain_side(String main_side) {
//        this.main_side = main_side;
//    }

    public String getAdditional_side() {
        return additional_side;
    }

//    public void setAdditional_side(String additional_side) {
//        this.additional_side = additional_side;
//    }

    public String getDessert() {
        return dessert;
    }

//    public void setDessert(String dessert) {
//        this.dessert = dessert;
//    }
}
