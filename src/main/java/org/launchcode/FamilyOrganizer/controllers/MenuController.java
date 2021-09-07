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

//import javax.servlet.http.HttpServletRequest;
//import javax.validation.Valid;

@Controller
@RequestMapping("menu")
public class MenuController {

    @Autowired
    private MenuRepository menuRepository;
//    private UserRepository userRepository;

    @GetMapping("/add")
    public String getMenuForm(Model model) {
        model.addAttribute(new Menu());
        return "/menu/add";
    }

    @PostMapping("add")

    public String processMenuAddForm(@ModelAttribute Menu menuItem, Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add");
            return "/menu/add";
        }

        menuRepository.save(menuItem);

        return "/menu/add";
    }

//    @GetMapping("/view")
//    public String getMenuView(Model model) {
//        model.addAttribute(new Menu());
//        return "/menu/view";
//    }

}