package ru.ScheduleOfEvents.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.ScheduleOfEvents.models.User;
import ru.ScheduleOfEvents.services.RegistrationService;
import ru.ScheduleOfEvents.util.UserValidator;

@Controller
@RequiredArgsConstructor
@RequestMapping("/auth")
public class SecurityController {
    private final UserValidator userValidator;
    private final RegistrationService registrationService;

    @GetMapping("/home")
    public String sayHello() {
        return "/auth/home";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @GetMapping("/registration")
    public String registrationPage(Model model) {
        model.addAttribute("user", new User());
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String performRegistrationPage(@ModelAttribute("user") @Valid User user,
                                    BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            return "auth/registration";
        }

        registrationService.register(user);

        return "redirect:/auth/login?success";
    }
}
