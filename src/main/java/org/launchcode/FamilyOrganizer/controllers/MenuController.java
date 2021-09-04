package org.launchcode.FamilyOrganizer.controllers;

import org.launchcode.FamilyOrganizer.data.MenuRepository;
import org.launchcode.FamilyOrganizer.models.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("menu")
public class MenuController {

    @Autowired
    private MenuRepository menuRepository;

    @GetMapping("add")
    public String menu(Model model) {
        model.addAttribute(new Menu());
        return "/menu/add";
    }

    @PostMapping("/menu/add")
    public String processMenuAddForm(@ModelAttribute Errors errors,
                                     Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add");
            return "/menu/add";
        }

        return null;
    }
}
