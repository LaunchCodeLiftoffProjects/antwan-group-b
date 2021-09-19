package org.launchcode.FamilyOrganizer.controllers;

import org.launchcode.FamilyOrganizer.data.GroceryListRepository;
import org.launchcode.FamilyOrganizer.models.GroceryList;
import org.launchcode.FamilyOrganizer.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("groceryList")
public class GroceryListController extends AuthenticationController {

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
    public Object getGroceryList(@ModelAttribute @Valid GroceryList groceryList,
                                 Errors errors, HttpServletRequest request,
                                 Model model){
        model.addAttribute("title", "Your Grocery List");
        User user = getUserFromSession(request.getSession());
        int userId = user.getId();
        List<GroceryList> groceryList1 = (List<GroceryList>) groceryListRepository.findByUserId(userId);
        model.addAttribute("groceryList", groceryList1);

        return"groceryList/view";
    }

    @PostMapping("/view")
    public String processGroceryList(@ModelAttribute @Valid GroceryList groceryList,
                                     Errors errors, HttpServletRequest request,
                                     Model model){
        model.addAttribute("title", "Your Grocery LIst");
        return "redirect:/groceryList/add";
    };

    @GetMapping("/add")
    public String displayAddGroceryListForm(@ModelAttribute GroceryList groceryList,
                                            Errors errors, HttpServletRequest request,
                                            Model model) {
        return "groceryList/add";
    }

    @PostMapping("/add")
    public Object processAddItemForm(@ModelAttribute @Valid GroceryList groceryList,
                                     Errors errors, HttpServletRequest request,
                                     Model model) throws ParseException {

        User user = getUserFromSession(request.getSession());
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add an Item to  Your Grocery List");
            return "/groceryList/add";
        }

        GroceryList newItem = new GroceryList(groceryList.getName(), groceryList.getQuantity(), user);
        groceryListRepository.save(newItem);

        return "redirect:/groceryList/view";

    }

    @GetMapping("/delete/{itemId}")
    public Object deleteGroceryListItem(@ModelAttribute @Valid GroceryList groceryList,
                                        Errors errors, HttpServletRequest request,
                                        Model model, @PathVariable int itemId) {

        groceryListRepository.deleteById(itemId);
        model.addAttribute("title", "Your Grocery List");
        User user = getUserFromSession(request.getSession());
        int userId = user.getId();
        List<GroceryList> groceryList1 = (List<GroceryList>) groceryListRepository.findByUserId(userId);
        model.addAttribute("groceryList", groceryList1);

        return"groceryList/view";
    }

    @GetMapping("/edit/{Id}")
    public String displayEditItemForm(Model model, @PathVariable("Id") int Id){

        Optional groceryItem = groceryListRepository.findById(Id);
        if (groceryItem.isPresent()) {
            GroceryList individualItem = (GroceryList) groceryItem.get();
            model.addAttribute("item", individualItem);
            return "/groceryList/edit";
        } else {
            return "groceryList/view";
        }
    }

    @PostMapping("edit/{Id}")
    public String processEditItemForm(@PathVariable("Id") int Id, Model model,
                                      @Valid @ModelAttribute GroceryList item,
                                      Errors errors) {
        if (errors.hasErrors()) {
            return "/groceryList/edit";
        }
        Optional<GroceryList> groceryItem = groceryListRepository.findById(Id);
        if(groceryItem.isPresent()) {
            GroceryList dbItem = groceryItem.get();
            dbItem.setName(item.getName());
            dbItem.setQuantity(item.getQuantity());

            groceryListRepository.save(dbItem);
        }
        return"redirect:/groceryList/view";
    }
}
