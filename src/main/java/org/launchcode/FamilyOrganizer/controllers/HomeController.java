package org.launchcode.FamilyOrganizer.controllers;

import org.launchcode.FamilyOrganizer.data.EventRepository;
import org.launchcode.FamilyOrganizer.models.Event;
import org.launchcode.FamilyOrganizer.models.ToDoList;
import org.launchcode.FamilyOrganizer.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("home")
public class HomeController extends AuthenticationController{

    @Autowired
    private EventRepository eventRepository;

    private static List<Event> events = new ArrayList<>();
    private static List<ToDoList> todolist = new ArrayList<>();

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "/logout";
    }

    @GetMapping("view")
    public String home(Model model, HttpServletRequest request) {
        User user = getUserFromSession(request.getSession());

        //Family Name
        String UserName = user.getFamilyName();
        model.addAttribute("title1", UserName);

        //Events
        //events.add(new Event("8:30 AM Dr Appointment","Mom"));
        model.addAttribute("title", "All Events");
        model.addAttribute("events", eventRepository.findAll());//this will need to be findByUserId, I think
        //To DO List
        todolist.add(new ToDoList("Laundry","Dad"));
        model.addAttribute("title2","To Do List");
        model.addAttribute("todolist",todolist);

        // displaying date
        Format f = new SimpleDateFormat("MMMM dd, yyyy");
        String strDate = f.format(new Date());
        model.addAttribute("currentdate",strDate);

        return "home/view";
    }


}
