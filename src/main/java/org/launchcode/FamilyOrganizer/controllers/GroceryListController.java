package org.launchcode.FamilyOrganizer.controllers;

import org.launchcode.FamilyOrganizer.models.GroceryList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("groceryList")
public class GroceryListController {



    @GetMapping("list")
    public String displayGroceryList(Model model){
        List<String> groceries = new ArrayList<>();
        groceries.add("milk");
        groceries.add("water");
        groceries.add("potatoes");
        groceries.add("steak");
        model.addAttribute("groceryList", groceries);
        return "groceryList/list";
    }



}
