package ru.ScheduleOfEvents.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.ScheduleOfEvents.models.User;
import ru.ScheduleOfEvents.services.UserDetailsServiceImpl;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserDetailsServiceImpl userDetailsService;

    @Autowired
    public UserController(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("users", userDetailsService.findAll());
        return "profileView/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userDetailsService.findOne(id));
        return "profileView/profileInfo";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userDetailsService.findOne(id));
        return "profileView/edit";
    }

    @GetMapping("/{id}/showEvents")
    public String showEvents(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userDetailsService.findOne(id));
        return "profileView/showEvents";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User person, @PathVariable("id") int id) {
        userDetailsService.update(id, person);
        return "profileView/profileInfo";
    }
}
