package org.launchcode.FamilyOrganizer.models;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class GroceryListItem extends AbstractEntity{

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotBlank(message = "Name of item is required.")
    @Size(max=100, message="Item name cannot exceed 100 characters")
    private String name;

    @NotNull(message="Must have a quantity of items")
    private int quantity;

    public GroceryListItem() {
    }

    public GroceryListItem(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
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

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return name;
    }
}
