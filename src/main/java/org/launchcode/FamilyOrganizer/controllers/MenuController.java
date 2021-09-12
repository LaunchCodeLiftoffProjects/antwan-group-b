package org.launchcode.FamilyOrganizer.controllers;

import org.launchcode.FamilyOrganizer.data.MenuRepository;
import org.launchcode.FamilyOrganizer.models.Menu;
import org.launchcode.FamilyOrganizer.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

//import javax.servlet.http.HttpServletRequest;
//import javax.validation.Valid;

@Controller
@RequestMapping("menu")
public class MenuController extends AuthenticationController{
    private static List<Menu> menu = new ArrayList<>();

    @Autowired
    MenuRepository menuRepository;

    @GetMapping("/add")
    public String getMenuForm(@ModelAttribute @Valid Menu menu, Errors errors, HttpServletRequest request,
                              Model model) {
        return "/menu/add";
    }

    @PostMapping("/add")

    public Object processMenuAddForm(@ModelAttribute @Valid Menu menu, Errors errors, HttpServletRequest request,
                                     Model model) throws ParseException {

        User user = getUserFromSession(request.getSession());

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add");
            return "/menu/add";
        }

        Menu newMenu = new Menu(menu.getDate(), menu.getMainCourse(), menu.getVegetable(), menu.getMainSide(), menu.getAdditionalSide(),
                                menu.getDessert(), user);
        menuRepository.save(newMenu);

        return "redirect:/menu/view";
    }

    @GetMapping("view")
    public String getMenuView(HttpServletRequest request, Model model) {

        User user = getUserFromSession(request.getSession());

        /*public static menu = (List<Menu>) menuRepository;*/
        model.addAttribute("menu", menu);

        return "/menu/view";
    }

}
