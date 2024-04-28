package ru.ScheduleOfEvents.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.ScheduleOfEvents.models.User;
import ru.ScheduleOfEvents.security.UserDetailsImpl;
import ru.ScheduleOfEvents.services.AdminService;
import ru.ScheduleOfEvents.services.UserDetailsServiceImpl;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final AdminService adminService;
    private final UserDetailsServiceImpl userDetailsServiceImpl;

    @GetMapping("/home")
    public String sayHello() {
        return "auth/homepage";
    }

    @GetMapping("/showUserInfo")
    public String showUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        System.out.println(userDetails.getUser());

        return "hello";
    }

    @GetMapping("/admin")
    public String adminPage() {
        adminService.doAdminStuff();
        return "admin";
    }
}
