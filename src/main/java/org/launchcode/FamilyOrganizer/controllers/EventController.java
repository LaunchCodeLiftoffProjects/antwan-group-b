package org.launchcode.FamilyOrganizer.controllers;

import org.launchcode.FamilyOrganizer.data.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import org.launchcode.FamilyOrganizer.models.EventDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.Date;

@Controller
@RequestMapping("events")
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    public EventController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @GetMapping("create")
    public String displayCreateEventForm(Model model) {
        model.addAttribute("title", "Create Event");
        model.addAttribute(new EventDetails());
        model.addAttribute("categories", eventRepository.findAll());//.values() is a built=in static method that return
        //an array of values defined inthe given enum, in the order in which they have been declare
        return "events/create";                                               }

    @PostMapping("create")
    public String processCreateEventForm(@ModelAttribute @Valid EventDetails newEvent, Errors errors, Model model) {
        if(errors.hasErrors()){
            model.addAttribute("title", "Create Event");
            return "events/create";
        }
//without the cascade line on @OneToOne, the below line save(newEvent) would generate an error because of some field being
// in the seperate EventDetails class.  In this case, trying to save newEvent causes problems because its eventDetails field
// cannot be null, but the value of this field = a new EventDetails object created on form submission, has not been saved yet
        eventRepository.save(newEvent);
        return "redirect:";
    }
}
