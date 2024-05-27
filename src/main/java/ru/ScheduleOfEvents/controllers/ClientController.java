package ru.ScheduleOfEvents.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.ScheduleOfEvents.models.*;
import ru.ScheduleOfEvents.services.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

@Controller
@RequiredArgsConstructor
@RequestMapping("/vip-client/")
public class ClientController {
    private final VipClientApplicationService vipClientApplicationService;
    private final UserDetailsServiceImpl userDetailsService;

    @GetMapping("/register")
    public String submitForm(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String name = userDetails.getUsername();
        User user = userDetailsService.findByUsername(name);
        model.addAttribute("user", user);
        if (vipClientApplicationService.findByOwnerId(user.getId()) != null){
            return "client/success";
        }
        return "client/register";
    }

    @PostMapping("/send")
    public String register(User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String name = userDetails.getUsername();
        User user1 = userDetailsService.findByUsername(name);
        user1.setName(user.getName());
        user1.setFamilyName(user.getFamilyName());
        user1.setSurname(user.getSurname());
        user1.setEmail(user.getEmail());
        userDetailsService.save(user1);
        vipClientApplicationService.save(new VipApplication(user1));
        return "client/success";
    }



}
