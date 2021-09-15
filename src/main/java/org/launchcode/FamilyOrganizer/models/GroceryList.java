package org.launchcode.FamilyOrganizer.models;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Entity
public class GroceryList extends AbstractEntity{

    @ManyToOne
    private User user;

    @NotBlank(message= "Item required.")
    @Size(min=2, max=25, message="Must be between 2 and 25 characters long.")
    private String name;

    private int quantity;

    public GroceryList() {
    }

    public GroceryList(String name, int quantity, User user) {
        this.name = name;
        this.quantity = quantity;
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


}
