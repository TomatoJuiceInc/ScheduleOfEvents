package ru.ScheduleOfEvents.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.ScheduleOfEvents.models.User;
import ru.ScheduleOfEvents.services.UserDetailsServiceImpl;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserDetailsServiceImpl userDetailsService;

    @GetMapping()
    public String show(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String name = userDetails.getUsername();
        User user = userDetailsService.findByUsername(name);

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

    @GetMapping("/showEvents")
    public String showEvents(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String name = userDetails.getUsername();
        User user = userDetailsService.findByUsername(name);
        model.addAttribute("user", user);
        return "profileView/showEvents";
    }

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
