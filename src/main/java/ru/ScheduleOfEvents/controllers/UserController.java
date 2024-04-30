package ru.ScheduleOfEvents.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.ScheduleOfEvents.models.Person;
import ru.ScheduleOfEvents.sevices.PeopleService;

@Controller
@RequestMapping("/users")
public class UserController {

    private final PeopleService peopleService;

    @Autowired
    public UserController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("users", peopleService.findAll());
        return "profileView/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", peopleService.findOne(id));
        return "profileView/profileInfo";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", peopleService.findOne(id));
        return "profileView/edit";
    }

    @GetMapping("/{id}/showEvents")
    public String showEvents(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", peopleService.findOne(id));
        return "profileView/showEvents";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") Person person, @PathVariable("id") int id) {
        peopleService.update(id, person);
        return "profileView/profileInfo";
    }
}
