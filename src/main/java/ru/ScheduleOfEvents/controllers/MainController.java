package ru.ScheduleOfEvents.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.ScheduleOfEvents.security.UserDetailsImpl;
import ru.ScheduleOfEvents.services.AdminService;
import ru.ScheduleOfEvents.services.UserDetailsServiceImpl;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final UserDetailsServiceImpl userDetailsServiceImpl;

    @GetMapping("/showUserInfo")
    public String showUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return "test";
    }
}
