package ru.ScheduleOfEvents.util;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.ScheduleOfEvents.models.User;
import ru.ScheduleOfEvents.services.UserDetailsServiceImpl;

@Component
@RequiredArgsConstructor
public class UserValidator implements Validator {
    private final UserDetailsServiceImpl userDetailsService;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        try {
            userDetailsService.loadUserByUsername(user.getUsername());
        } catch (UsernameNotFoundException ignored) {
            return;
        }

        errors.rejectValue("username", "", "Человек с таким именем пользователя уже существует");
    }
}
