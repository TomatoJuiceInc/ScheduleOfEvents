package ru.ScheduleOfEvents.services;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.ScheduleOfEvents.models.User;
import ru.ScheduleOfEvents.models.UserRole;

@Service
//@RequiredArgsConstructor
@AllArgsConstructor
public class AdminService {
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsServiceImpl userDetailsService;

    @PostConstruct
    @Transactional
    public void postConstruct() {
        if (userDetailsService.findByUsername("admin") == null) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setRole(UserRole.ADMIN.name());
            userDetailsService.save(admin);
        }
    }
}
