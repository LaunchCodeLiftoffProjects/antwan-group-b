package org.launchcode.FamilyOrganizer.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("groceryList")
public class GroceryListController {



    @GetMapping("list")
    public String displayGroceryList(Model model){
        Map<String, Integer> groceries = new HashMap<>();
        groceries.put("milk", 1);
        groceries.put("water", 2);
        groceries.put("potatoes", 4);
        groceries.put("steak", 4);
        model.addAttribute("groceryList", groceries);
        return "groceryList/list";
    }

    @GetMapping("addItem")
    public String renderAddItemForm() {
        return "groceryList/addItem";
    }


}
