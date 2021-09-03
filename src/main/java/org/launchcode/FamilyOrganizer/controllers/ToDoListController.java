package org.launchcode.FamilyOrganizer.controllers;

import org.launchcode.FamilyOrganizer.data.ToDoListRepository;
import org.launchcode.FamilyOrganizer.models.ToDoList;
import org.launchcode.FamilyOrganizer.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("todolist")
public class ToDoListController extends AuthenticationController{
    private static List<ToDoList> todolist = new ArrayList<>();

    @Autowired
    ToDoListRepository toDoListRepository;

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
    public Object getToDoList(@ModelAttribute @Valid ToDoList toDoList,
                                          Errors errors, HttpServletRequest request,
                                          Model model) {
        model.addAttribute("title", "Add to To Do List");
        User user = getUserFromSession(request.getSession());
        int userId = user.getId();
        List<ToDoList> toDoList1 = (List<ToDoList>) toDoListRepository.findByUserId(userId);
        model.addAttribute("todolist", toDoList1);

        return "todolist/view";
    }

    @PostMapping("/view")
    public String processToDoList(@ModelAttribute @Valid ToDoList toDoList,
                              Errors errors, HttpServletRequest request,
                              Model model) {
        model.addAttribute("title", "Add to To Do List");
        return "redirect:/todolist/add";
    }

    @GetMapping("/add")
    public String getToDoListForm(@ModelAttribute @Valid ToDoList toDoList,
                                      Errors errors, HttpServletRequest request,
                                      Model model) {
        return "todolist/add";
    }

    @PostMapping("/add")

    public Object processToDoListForm(@ModelAttribute @Valid ToDoList toDoList,
                                          Errors errors, HttpServletRequest request,
                                          Model model) throws ParseException {
        User user = getUserFromSession(request.getSession());
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add to To Do List");
            return "todolist/add";
        }

        ToDoList newList = new ToDoList(toDoList.getName(), toDoList.getMember(),toDoList.getStart(),user);
        toDoListRepository.save(newList);

        return "redirect:/todolist/view";
    }

    @GetMapping("/delete/{listId}")
    public Object deleteToDoList(@ModelAttribute @Valid ToDoList toDoList,
                              Errors errors, HttpServletRequest request,
                              Model model,@PathVariable int listId) {
        toDoListRepository.deleteById(listId);
        model.addAttribute("title", "Add to To Do List");
        User user = getUserFromSession(request.getSession());
        int userId = user.getId();
        List<ToDoList> toDoList1 = (List<ToDoList>) toDoListRepository.findByUserId(userId);
        model.addAttribute("todolist", toDoList1);

        return "todolist/view";
    }


}
