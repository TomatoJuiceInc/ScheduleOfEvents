package ru.ScheduleOfEvents.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.ScheduleOfEvents.models.UserModel;
import ru.ScheduleOfEvents.sevices.UserService;

@Controller
@RequestMapping("/auth")
@AllArgsConstructor
public class SecurityController {
    private UserService service;

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("user", new UserModel());
        return "auth/signup";
    }
    @PostMapping("/signup")
//    @PostMapping()
    // @RequestBody UserModel user
    public String createUser(@RequestParam("username") String username,
                             @RequestParam("email") String email,
                             @RequestParam("password") String password,
                             @RequestParam("passwordConfirm") String passwordConfirm,
                             BindingResult bindingResult,
                             Model model) {
        if (password.equals(passwordConfirm)) {
            bindingResult.rejectValue("confirmPassword", "error.userModel", "Пароли не совпадают");
        }

        if (bindingResult.hasErrors()) {
            return "auth/signup";
        } else {
            UserModel user = new UserModel();
            user.setUsername(username);
            user.setEmail(email);
            user.setPassword(password);
            service.createUser(user);
            return "redirect:/main";
        }
    }
}
