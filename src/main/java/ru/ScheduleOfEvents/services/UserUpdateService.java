package ru.ScheduleOfEvents.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.ScheduleOfEvents.models.User;
import ru.ScheduleOfEvents.repositories.UserRepository;

@RequiredArgsConstructor
@Service
public class UserUpdateService {
    private final UserRepository userRepository;
    private final UserDetailsServiceImpl userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void update(long id, User updatedUser) {
        User userToBeUpdated = userDetailsService.findOne(id);

        userToBeUpdated.setFamilyName(updatedUser.getFamilyName());
        userToBeUpdated.setName(updatedUser.getName());
        userToBeUpdated.setSurname(updatedUser.getSurname());
        userToBeUpdated.setEmail(updatedUser.getEmail());
        if (!(updatedUser.getPassword() == null || updatedUser.getPassword().isEmpty())) {
            userToBeUpdated.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        }
    }
}
