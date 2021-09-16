package org.launchcode.FamilyOrganizer.controllers;

import org.launchcode.FamilyOrganizer.data.EventRepository;
import org.launchcode.FamilyOrganizer.data.UserRepository;
import org.launchcode.FamilyOrganizer.models.Event;
import org.launchcode.FamilyOrganizer.models.ToDoList;
import org.launchcode.FamilyOrganizer.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("home")
public class HomeController extends AuthenticationController{

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    private static List<Event> events = new ArrayList<>();
    private static List<ToDoList> todolist = new ArrayList<>();

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "/logout";
    }

    @GetMapping("/view")
    public String home(Model model, HttpServletRequest request) {
        User user = getUserFromSession(request.getSession());
        int userId = user.getId();
        //Family Name
        String UserName = user.getFamilyName();
        model.addAttribute("title1", UserName);

        //Events
        //events.add(new Event("8:30 AM Dr Appointment","Mom"));
        model.addAttribute("title", "All Events");
        model.addAttribute("events", eventRepository.findByUserId(userId));//this will need to be findByUserId, I think
        //To DO List
        todolist.add(new ToDoList("Laundry","Dad"));
        model.addAttribute("title2","To Do List");
        model.addAttribute("todolist",todolist);

        // displaying date
        Format f = new SimpleDateFormat("MMMM dd, yyyy");
        String strDate = f.format(new Date());
        model.addAttribute("currentdate",strDate);

        return "/home/view";
    }

    @GetMapping("events/displayEvent")
    public String displayEventDetails(@RequestParam Integer eventId, Model model) {

        Optional<Event> result = eventRepository.findById(eventId);

        if (result.isEmpty()) {
            model.addAttribute("title", "Invalid Event ID: " + eventId);
        } else {
            Event event = result.get();
            model.addAttribute("title", event.getName() + " Details");
            model.addAttribute("event", event);
        }

        return "events/displayEvent";
    }


}
