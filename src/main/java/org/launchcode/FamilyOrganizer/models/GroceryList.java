package org.launchcode.FamilyOrganizer.models;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class GroceryList extends AbstractEntity{

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GroceryListItem> items = new ArrayList<>();

    public GroceryList() {
    }

    public GroceryList(List<GroceryListItem> item) {
        this.items = item;
    }

    public void addItem(GroceryListItem newItem){
        items.add(newItem);
    }

    public List<GroceryListItem> getItems() {
        return items;
    }

    public void setItems(List<GroceryListItem> items) {
        this.items = items;
    }
}
