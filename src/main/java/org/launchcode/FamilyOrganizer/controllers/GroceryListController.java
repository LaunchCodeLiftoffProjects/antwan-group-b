package org.launchcode.FamilyOrganizer.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.HashMap;


@Controller
@RequestMapping("groceryList")
public class GroceryListController {

    private static HashMap<String, Integer> groceries = new HashMap<>();


    @GetMapping("list")
    public String displayGroceryList(Model model){
        model.addAttribute("groceryList", groceries);
        return "groceryList/list";
    }

    @GetMapping("addItem")
    public String renderAddItemForm() {
        return "groceryList/addItem";
    }

    @PostMapping("addItem")
    public String createItem(@RequestParam String itemName, @RequestParam Integer quantity){
        groceries.put(itemName, quantity);

        return "redirect:/groceryList/list";

    }


}
