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
import java.util.*;



@Controller
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
            model.addAttribute("event", "All Events");
            User user = getUserFromSession(request.getSession());
            int userId = user.getId();
            List<Event> event1 = (List<Event>) eventRepository.findByUserId(userId);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date todaysDate = new Date();
            String formatedTodaysDate = formatter.format(todaysDate);

            try {
                for (Event element : event1) {
                    if(element.getEventDetails().getDate().before(formatter.parse(formatedTodaysDate)))
                        eventRepository.delete(element);
                }
                Collections.sort(event1, new Comparator<Event>(){
                    public int compare(Event date1, Event date2) {
                        if (date1.eventDetails.getDate() == null || date2.eventDetails.getDate() == null)
                            return 0;

                        return date1.eventDetails.getDate().compareTo(date2.getEventDetails().getDate());
                    }});
            }
            catch (Exception e) {
                System.out.println("Exception: " + e);
            }

        model.addAttribute("events", event1);
        return "events/index";
    }

    @GetMapping("/create")
    public String displayCreateEventForm(@ModelAttribute @Valid Event event, Errors errors, HttpServletRequest request, Model model) {
        model.addAttribute("title", "Create Event");
        return "events/create";
    }

    @PostMapping("/create")
    public Object processCreateEventForm(@ModelAttribute @Valid Event event,
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


    @GetMapping("/cancel/{eventId}")
    public Object processDeleteEventsForm(@ModelAttribute @Valid Event event, Errors errors, HttpServletRequest request,
                                          Model model, @PathVariable int eventId) {
        eventRepository.deleteById(eventId);
        model.addAttribute("title", "All Events");
        User user = getUserFromSession(request.getSession());
        List<Event> event2 = (List<Event>) eventRepository.findByUserId(user.getId());

        try {
            event2 = (List<Event>) eventRepository.findByUserId(user.getId());

        }
        catch(Exception e)
        {

        }

        Collections.sort(event2, new Comparator<Event>(){
            public int compare(Event date1, Event date2){
                if(date1.eventDetails.getDate()== null || date2.eventDetails.getDate()== null)
                    return 0;
                return date1.eventDetails.getDate().compareTo(date2.getEventDetails().getDate());
            }
        });
        model.addAttribute("events", event2);
        return "/events/index";
    }

    @GetMapping("/edit/{eventId}")
    public String displayEditEventForm(Model model, @PathVariable int eventId){

        Optional eventdetail = eventRepository.findById(eventId);
        if (eventdetail.isPresent()) {
            Event individualdetail = (Event) eventdetail.get();
            model.addAttribute("item", individualdetail);
            return "/events/edit";
        } else {
            return "/events/index";
        }
    }

    @PostMapping("/edit/{eventId}")
    public String processEditEventForm(@PathVariable int eventId, Model model,
                                      @Valid @ModelAttribute Event item,
                                      Errors errors) {
        if (errors.hasErrors()) {
            return "/events/edit";
        }
        Optional<Event> eventdetails = eventRepository.findById(eventId);
        if(eventdetails.isPresent()) {
            Event dbItem = eventdetails.get();
            dbItem.setName(item.getName());
            dbItem.eventDetails.setName(item.eventDetails.getName());
            dbItem.eventDetails.setDate(item.eventDetails.getDate());
            dbItem.eventDetails.setStartTime(item.eventDetails.getStartTime());
            dbItem.eventDetails.setEndTime(item.eventDetails.getEndTime());
            dbItem.eventDetails.setLocation(item.eventDetails.getLocation());
            dbItem.eventDetails.setNotes(item.eventDetails.getNotes());
            eventRepository.save(dbItem);
        }
        return "redirect:/events";
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
