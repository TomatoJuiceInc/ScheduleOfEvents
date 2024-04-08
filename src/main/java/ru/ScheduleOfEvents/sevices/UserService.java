package ru.ScheduleOfEvents.sevices;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.ScheduleOfEvents.models.UserModel;
import ru.ScheduleOfEvents.repositories.UserRepository;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository repository;
    private PasswordEncoder encoder;

    public void createUser(UserModel user) {
        user.setPassword(encoder.encode(user.getPassword()));
        repository.save(user);
    }
}
