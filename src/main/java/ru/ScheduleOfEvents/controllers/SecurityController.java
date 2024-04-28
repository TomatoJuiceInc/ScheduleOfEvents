package ru.ScheduleOfEvents.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/login")
    public String signInPage() {
        return "auth/login";
    }

    @GetMapping("/registration")
    public String signUpPage(Model model) {
        model.addAttribute("user", new User());
        return "auth/registration";
    }

//    @PostMapping("/registration")
//    public String performSignUpPage(@ModelAttribute("user") @Valid User user,
//                                    BindingResult bindingResult) {
//        userValidator.validate(user, bindingResult);
//
//        if (bindingResult.hasErrors()) {
//            return "/registration";
//        }
//
//        registrationService.register(user);
//
//        return "redirect:/login?success";
//    }
}
