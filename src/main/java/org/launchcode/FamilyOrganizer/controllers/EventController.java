package org.launchcode.FamilyOrganizer.controllers;

import org.launchcode.FamilyOrganizer.data.EventRepository;
import org.launchcode.FamilyOrganizer.models.Event;
import org.launchcode.FamilyOrganizer.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Controller
//@Scope("session")
@RequestMapping("events")
public class EventController extends AuthenticationController{

    private static List<Event> events = new ArrayList<>();

    @Autowired
    EventRepository eventRepository;

    @InitBinder(value = "date")
    public void initBinder(WebDataBinder binder, WebRequest request) {
        //convert the date Note that the conversion here should always be in the same format as the string passed in, e.g. 2015-9-9 should be yyyy-MM-dd
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));// CustomDateEditor is a custom date editor

    }

    @InitBinder(value = "time")
    public void timeBinder(WebDataBinder binder, WebRequest request) {
        //convert the date Note that the conversion here should always be in the same format as the string passed in, e.g. 2015-9-9 should be yyyy-MM-dd
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(timeFormat, true));
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "/logout";
    }
    @GetMapping
    public String displayEvents(@ModelAttribute @Valid Event event, Errors errors, HttpServletRequest request,  Model model) {
            model.addAttribute("title", "All Events");
            User user = getUserFromSession(request.getSession());
            int userId = user.getId();
            List<Event> event1 = (List<Event>) eventRepository.findByUserId(userId);
            model.addAttribute("events", event1);
        return "events/index";
    }

    @GetMapping("/create")
    public String displayCreateEventForm(@ModelAttribute @Valid Event event, Errors errors, HttpServletRequest request, Model model) {
        model.addAttribute("title", "Create Event");
        return "events/create";
    }

    @PostMapping("/create")
    public Object processCreateEventForm(@ModelAttribute Date date, @ModelAttribute Date time, Event event,
                                         Errors errors, Model model, HttpServletRequest request){
        User user = getUserFromSession(request.getSession());
        if(errors.hasErrors()){
            model.addAttribute("title", "Create Event");
            return "events/create";
        }

        Event newEvent = new Event(event.getName(), event.eventDetails, user);
        eventRepository.save(newEvent);
        return "redirect:";
    }

    @GetMapping("/cancel")
    public String displayCancelEventForm(Model model) {
        model.addAttribute("title", "Cancel Event");
        model.addAttribute("events", eventRepository.findAll());
        return "events/cancel";
    }

    @PostMapping("/cancel")
    public String processDeleteEventsForm(@RequestParam(required = false) int[] eventIds) {

        if (eventIds != null) {
            for (int id : eventIds) {
                eventRepository.deleteById(id);
            }
        }

        return "redirect:";
    }

    @GetMapping("/displayEvent")
    public String displayEventDetails(@RequestParam Integer eventId, HttpServletRequest request, Model model) {

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
