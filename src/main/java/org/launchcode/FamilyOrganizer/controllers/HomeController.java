package org.launchcode.FamilyOrganizer.controllers;

import org.launchcode.FamilyOrganizer.data.EventRepository;
import org.launchcode.FamilyOrganizer.data.ToDoListRepository;
import org.launchcode.FamilyOrganizer.models.Event;
import org.launchcode.FamilyOrganizer.models.ToDoList;
import org.launchcode.FamilyOrganizer.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("home")
public class HomeController extends AuthenticationController{

    private static List<Event> events = new ArrayList<>();
    private static List<ToDoList> todolist = new ArrayList<>();

    @Autowired
    ToDoListRepository toDoListRepository;

    @Autowired
    EventRepository eventRepository;

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "/logout";
    }

    @GetMapping("view")
    public String home(Model model, HttpServletRequest request) {
        User user = getUserFromSession(request.getSession());
        int userId = user.getId();

        //Family Name
        String UserName = user.getFamilyName();
        model.addAttribute("title1", UserName);

        //To DO List
        model.addAttribute("title2", "To Do List");
        List<ToDoList> toDoLists = (List<ToDoList>) toDoListRepository.findByUserId(userId);
        model.addAttribute("todolist", toDoLists);

        try {
            //Events
            model.addAttribute("title", "All Events");
            List<Event> event1 = (List<Event>) eventRepository.findByUserId(userId);
            //model.addAttribute("events", event1);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date todaysDate = new Date();

            //System.out.println(formatter.format(todaysDate));

            List<Event> todaysEvents = new ArrayList<Event>();
            for (Event element : event1) {
                if (formatter.format(element.getEventDetails().getDate()).equals(formatter.format(todaysDate))) {
                    todaysEvents.add(element);
                }
            }
            model.addAttribute("events", todaysEvents);


        }
        catch (Exception e)
        {

        }
        // displaying date
        Format f = new SimpleDateFormat("MMMM dd, yyyy");
        String strDate = f.format(new Date());
        model.addAttribute("currentdate",strDate);

        return "home/view";
    }

    @GetMapping("todolist")
    public Object todolist(){

        return new ModelAndView("redirect:/todolist/view");
    }

    @GetMapping("events")
    public Object events(){

        return new ModelAndView("redirect:/events");
    }

    @GetMapping("grocerylist")
    public Object grocerylist(){

        return new ModelAndView("redirect:/groceryList/view");
    }

    @GetMapping("menu")
    public Object menu(){

        return new ModelAndView("redirect:/menu/view");
    }
}
