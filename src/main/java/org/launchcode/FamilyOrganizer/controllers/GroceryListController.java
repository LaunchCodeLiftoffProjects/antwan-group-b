package org.launchcode.FamilyOrganizer.controllers;

import org.launchcode.FamilyOrganizer.data.GroceryListRepository;
import org.launchcode.FamilyOrganizer.models.GroceryList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping("groceryList")
public class GroceryListController {

    private GroceryListRepository groceryListRepository;

    private GroceryList groceryList = new GroceryList();

    @GetMapping("list")
    public String displayGroceryList(Model model){
        model.addAttribute("title", "Family Organizer: Grocery List");
        model.addAttribute("groceryList", groceryListRepository.findAll());
        return "groceryList/list";
    }

    @GetMapping("addItem")
    public String renderAddItemForm() {
        return "groceryList/addItem";
    }

    @PostMapping("addItem")
    public String addGroceryItemToList(@RequestParam String itemName, @RequestParam Integer quantity){


        return "redirect:/groceryList/list";

    }


}
