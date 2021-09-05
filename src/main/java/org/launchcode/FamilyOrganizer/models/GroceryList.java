package org.launchcode.FamilyOrganizer.models;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@Entity
public class GroceryList extends AbstractEntity{

    //@OneToMany(cascade=)
    private List<GroceryItem> items = new ArrayList<>();

    public GroceryList() {
    }

    public GroceryList(List<GroceryItem> items) {
        this.items = items;
    }

    public void addItem(GroceryItem newItem){
        items.add(newItem);

    }

    public List<GroceryItem> getItems() {
        return items;
    }

    public void setItems(List<GroceryItem> items) {
        this.items = items;
    }
}
