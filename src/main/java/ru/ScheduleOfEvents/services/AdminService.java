package ru.ScheduleOfEvents.services;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.ScheduleOfEvents.models.User;
import ru.ScheduleOfEvents.models.UserRole;
import ru.ScheduleOfEvents.repositories.UserRepository;

@Service
//@RequiredArgsConstructor
@AllArgsConstructor
public class AdminService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

//    @PostConstruct
//    @Transactional
//    public void postConstruct() {
//        User admin = new User();
//        admin.setUsername("admin");
//        admin.setPassword(passwordEncoder.encode("admin"));
//        admin.setRole(UserRole.ADMIN.name());
//        userRepository.save(admin);
//    }
}
