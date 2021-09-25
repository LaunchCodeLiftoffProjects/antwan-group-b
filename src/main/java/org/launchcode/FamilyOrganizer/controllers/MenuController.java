package org.launchcode.FamilyOrganizer.controllers;

import org.launchcode.FamilyOrganizer.data.MenuRepository;
import org.launchcode.FamilyOrganizer.models.Menu;
import org.launchcode.FamilyOrganizer.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


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

//        public static void dateUserTable (Connection con) throws SQLException {
//            String query = "Select date From menu Where user = user and date = date";
//            try (Statement stmt = con.createStatement()) {
//                ResultSet rs = stmt.executeQuery(query);
//                while (rs.next()) {
//                    String rsDate = rs.getString(1);
//                }
//            }
//        }
//
//        if (date = rsDate) {
//            model.addAttribute("title", "Add");
//            return "/menu/add";
//        }
//        public static void main(String[] args) throws SQLException {
//            String query = "Select date From menu" + " Where date = this.date";
//            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/family_organizer",
//                    "family_organizer", "Liftoff2021");
//                Statement stmt = con.createStatement();) {
//                ResultSet rs = stmt.executeQuery(query);
//                while (rs.next()) {
//                    String rsDate = rs.getString(1);
//                }
//            }
//        }

        Menu newMenu = new Menu(menu.getDate(), menu.getMainCourse(), menu.getVegetable(), menu.getMainSide(),
                                menu.getAdditionalSide(), menu.getDessert(), user);
        menuRepository.save(newMenu);

        return "redirect:/menu/view";
    }

    @GetMapping("/view")
    public String getMenuView(@ModelAttribute @Valid Menu menu, Errors errors, HttpServletRequest request,
                              Model model) {
        model.addAttribute("title", "Menu");
        User user = getUserFromSession(request.getSession());
        int userId = user.getId();
        List<Menu> menuView = (List<Menu>) menuRepository.findByUserId(userId);
        List<Menu> sortedDay = menuView.stream().sorted(Comparator.comparing(Menu::getDate))
                .collect(Collectors.toList());
        model.addAttribute("menu", sortedDay);

        return "/menu/view";
    }

    /*@PostMapping("/view")
    public String processMenu(@ModelAttribute @Valid Menu menu, Errors errors, HttpServletRequest request,
                               Model model) {
        model.addAttribute("title", "menuView");
        return "redirect:/menu/view";
    }*/

    @GetMapping("edit/{id}")
    public String displayMenuEditForm(Model model, @PathVariable("id") int id) {

        Optional menuList = menuRepository.findById(id);

        if (menuList.isPresent()) {
            Menu individualMenu = (Menu) menuList.get();
            model.addAttribute("menu", individualMenu);
            return "/menu/edit";
        } else {
            return "/menu/view";
        }
    }

        @PostMapping("/edit/{id}")

        public String processMenuEditForm(@PathVariable("id") int id, Model model, @Valid @ModelAttribute Menu menu,
                                        Errors errors) {

            if (errors.hasErrors()) {
                return "/menu/edit";
            }

            Optional<Menu> menuList = menuRepository.findById(id);

            if(menuList.isPresent()) {
                Menu dbList = menuList.get();
                dbList.setDate(menu.getDate());
                dbList.setMainCourse(menu.getMainCourse());
                dbList.setVegetable(menu.getVegetable());
                dbList.setMainSide(menu.getMainSide());
                dbList.setAdditionalSide(menu.getAdditionalSide());
                dbList.setDessert(menu.getDessert());

                menuRepository.save(dbList);

            }

            return "redirect:/menu/view";

            }

        @GetMapping("/delete/{itemID}")
        public Object deleteMenu(@ModelAttribute @Valid Menu menu, Errors errors, HttpServletRequest request,
                                 Model model, @PathVariable int itemID) {

            menuRepository.deleteById(itemID);
            model.addAttribute("title", "Menu");
            User user = getUserFromSession(request.getSession());
            int userId = user.getId();
            List<Menu> menuView = (List<Menu>) menuRepository.findByUserId(userId);
            List<Menu> sortedDay = menuView.stream().sorted(Comparator.comparing(Menu::getDate))
                    .collect(Collectors.toList());
            model.addAttribute("menu", sortedDay);

            return "menu/view";
        }

        }