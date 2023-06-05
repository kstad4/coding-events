package org.launchcode.codingevents.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("events")
public class EventController {

    private static List<String> events = new ArrayList<>();

    @GetMapping
    public String displayAllEvents (Model model) {
//        model.addAttribute("events", events);
//        return "events/index";
        HashMap<String, String> events = new HashMap<>();
        events.put("WWDC", "Apple's annual event to honor and bring together the coders of the world.");
        events.put("Code with Pride", "Kansas City's top LGBTQ+ coding get together.");
        events.put("Texas Coding", "The Texas state-operated coding conference.");
        model.addAttribute("events", events);

        return "events/index";
    };

    //lives at /events/create
    @GetMapping("create")
    public String renderCreateEventForm() {
      return "events/create";
    };

    //Handler for information submitted using the form in /templates/events/create
    //The request parameter "eventName" is the same name found on the input tag in /templates/events/create
    @PostMapping("create")
    public String createEvent(@RequestParam String eventName) {
        events.add(eventName);
        return "redirect:/events";
    };
};
