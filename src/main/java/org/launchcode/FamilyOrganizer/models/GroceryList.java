//package org.launchcode.FamilyOrganizer.models;
//
//import javax.persistence.*;
//import java.util.ArrayList;
//
//
//@Entity(name="GroceryList")
//@Table(name="Grocery_List")
//public class GroceryList{
//
//    @Id
//    @GeneratedValue
//    private int id;
//
//
//    private ArrayList<GroceryListItem> items = new ArrayList<>();
//
//
//    public GroceryList() {
//    }
//
//    public GroceryList(ArrayList<GroceryListItem> item) {
//        this.items = item;
//    }
//
//    public void addItem(GroceryListItem newItem){
//        items.add(newItem);
//    }
//
//    public ArrayList<GroceryListItem> getItems() {
//        return items;
//    }
//
//    public void setItems(ArrayList<GroceryListItem> items) {
//        this.items = items;
//    }
//}
