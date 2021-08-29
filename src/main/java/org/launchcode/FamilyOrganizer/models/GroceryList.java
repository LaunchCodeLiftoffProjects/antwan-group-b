package org.launchcode.FamilyOrganizer.models;

import javax.persistence.Entity;
import java.util.ArrayList;

@Entity
public class GroceryList extends AbstractEntity {

    private ArrayList< String > groceries = new ArrayList<>();


    public GroceryList() {
    }

    public GroceryList(ArrayList<String> groceries) {
        this.groceries = groceries;
    }

    public ArrayList<String> getGroceries() {
        return groceries;
    }

    public void setGroceries(ArrayList<String> groceries) {
        this.groceries = groceries;
    }
}
