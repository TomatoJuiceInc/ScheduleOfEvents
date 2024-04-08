package ru.ScheduleOfEvents.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.ScheduleOfEvents.models.UserModel;
import ru.ScheduleOfEvents.sevices.UserService;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class SecurityController {
    private UserService service;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome!";
    }

    @PostMapping("/create")
    public String createUser(@RequestBody UserModel user) {
        service.createUser(user);
        return "User is saved";
    }
}
