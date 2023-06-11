package org.launchcode.codingevents.controllers;

import jakarta.validation.Valid;
import org.launchcode.codingevents.data.EventData;
import org.launchcode.codingevents.models.Event;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("events")
public class EventController {

    @GetMapping
    public String displayAllEvents (Model model) {
        model.addAttribute("events", EventData.getAll());
        return "events/index";
    };

    //lives at /events/create
    @GetMapping("create")
    public String displayCreateEventForm() {
      return "events/create";
    };

    //Handler for information submitted using the form in /templates/events/create
    //The request parameter "eventName" is the same name found on the input tag in /templates/events/create
    @PostMapping("create")
    public String processCreateEventForm(@ModelAttribute @Valid Event newEvent,
                                         Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("errorMsg", "Bad data!");
            return "events/create";
        }

        EventData.add(newEvent);
        return "redirect:/events";
    };

    @GetMapping("delete")
    public String displayDeleteEventForm (Model model) {
        model.addAttribute("title", "Delete Events");
        model.addAttribute("events", EventData.getAll());
        return "events/delete";
    };

    @PostMapping("delete")
    public String processDeleteEventForm (@RequestParam(required = false) int[] eventIds) {
        if (eventIds != null) {
            for (int id : eventIds) {
                EventData.remove(id);
            }
            ;
        };
        return "redirect:/events";
    };

    @GetMapping("edit/{eventId}")
    public String displayEditEventForm(Model model, @PathVariable int eventId) {
        Event eventToEdit = EventData.getById(eventId);
        model.addAttribute("event", eventToEdit);
        return "events/edit";
    };

    @PostMapping("edit")
    public String processEditForm(int eventId, String name, String description) {
        Event eventToEdit = EventData.getById(eventId);
        eventToEdit.setName(name);
        eventToEdit.setDescription(description);
        return "redirect:/events";
    };
};
