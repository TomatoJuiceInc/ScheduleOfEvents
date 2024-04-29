package ru.ScheduleOfEvents.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ScheduleOfEvents.models.User;
import ru.ScheduleOfEvents.models.UserRole;
import ru.ScheduleOfEvents.repositories.UserRepository;

@Service
@RequiredArgsConstructor
public class RegistrationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(UserRole.USER.name());
        userRepository.save(user);
    }
}
