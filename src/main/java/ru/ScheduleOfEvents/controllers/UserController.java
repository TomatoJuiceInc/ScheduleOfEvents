package ru.ScheduleOfEvents.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.ScheduleOfEvents.models.User;
import ru.ScheduleOfEvents.services.UserDetailsServiceImpl;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserDetailsServiceImpl userDetailsService;

    @Autowired
    public UserController(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

//    @GetMapping()
//    public String index(Model model) {
//        model.addAttribute("users", userDetailsService.findAll());
//        return "profileView/index";
//    }

    @GetMapping()
    public String show(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String name = userDetails.getUsername();
        User user = userDetailsService.findByUsername(name);

//        userDetailsService.addTestTickets(user.getId());
//        userDetailsService.sortTickets(user.getId());

        model.addAttribute("user", user);
        return "profileView/profileInfo";
    }

    @GetMapping("/edit")
    public String edit(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String name = userDetails.getUsername();
        User user = userDetailsService.findByUsername(name);
        model.addAttribute("user", user);
        return "profileView/edit";
    }

//    @GetMapping("/showEvents")
//    public String showEvents(Model model, @PathVariable("id") int id) {
//        model.addAttribute("user", userDetailsService.findOne(id));
//        return "profileView/showEvents";
//    }

    @PatchMapping()
    public String update(@ModelAttribute("user") User person) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String name = userDetails.getUsername();
        User user = userDetailsService.findByUsername(name);
        userDetailsService.update(user.getId(), person);
        return "redirect:/user";
    }
}
