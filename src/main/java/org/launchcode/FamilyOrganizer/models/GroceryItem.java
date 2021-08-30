package org.launchcode.FamilyOrganizer.models;

import javax.persistence.Entity;
import java.util.ArrayList;

@Entity
public class GroceryItem extends AbstractEntity {

    private String name;
    private int quantity;


    public GroceryItem() {
    }

    public GroceryItem(String name, int quantity) {
        this.name = name;
        this.quantity= quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String item) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
