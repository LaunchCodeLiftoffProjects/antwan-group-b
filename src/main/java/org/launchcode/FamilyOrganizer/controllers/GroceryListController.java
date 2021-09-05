package org.launchcode.FamilyOrganizer.controllers;

import org.launchcode.FamilyOrganizer.data.GroceryListRepository;
import org.launchcode.FamilyOrganizer.models.GroceryListItem;
import org.launchcode.FamilyOrganizer.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("groceryList")
public class GroceryListController extends AuthenticationController {

    private static List<GroceryListItem> groceryList = new ArrayList<>();

    @Autowired
    private GroceryListRepository groceryListRepository;

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "/logout";
    }
    @GetMapping("/home")
    public Object returnHome(HttpServletRequest request) {
        return new ModelAndView("redirect:home/view");
    }

    @GetMapping("/view")
    public Object getGroceryList(@ModelAttribute @Valid GroceryListItem groceryListItem,
                                 Errors errors, HttpServletRequest request,
                                 Model model){
        model.addAttribute("title", "Add Item to Grocery List");
        User user = getUserFromSession(request.getSession());
        int userId = user.getId();
        List<GroceryListItem> groceryList1 = (List<GroceryListItem>) groceryListRepository.findByUserId(userId);
        model.addAttribute("groceryList", groceryList1);

        return"groceryList/view";
    }

    @PostMapping("/view")
    public String processGroceryList(@ModelAttribute @Valid GroceryListItem groceryListItem,
                                     Errors errors, HttpServletRequest request,
                                     Model model){
        model.addAttribute("title", "Add Item to Grocery List");
        return "redirect:/groceryList/addItem";
    }


    @GetMapping("/addItem")
    public String renderAddItemForm(@ModelAttribute @Valid GroceryListItem groceryListItem,
                                    Errors errors, HttpServletRequest request,
                                    Model model) {
        return "groceryList/addItem";
    }

    @PostMapping("addItem")
    public String processAddItemForm(@ModelAttribute @Valid GroceryListItem groceryListItem,
                                     Errors errors, HttpServletRequest request,
                                     Model model) throws ParseException {

        User user = getUserFromSession(request.getSession());
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Item to Grocery List");
            return "/groceryList/addItem";
        }

        GroceryListItem newItem = new GroceryListItem(groceryListItem.getName(), groceryListItem.getQuantity(), user);
        groceryListRepository.save(newItem);

        return "redirect:groceryList/view";

    }

}
