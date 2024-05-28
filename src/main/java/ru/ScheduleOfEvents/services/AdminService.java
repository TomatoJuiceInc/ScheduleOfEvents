package ru.ScheduleOfEvents.services;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.ScheduleOfEvents.models.Role;
import ru.ScheduleOfEvents.models.User;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsServiceImpl userDetailsService;

    @PostConstruct
    @Transactional
    public void postConstructAdmin() {
        if (userDetailsService.findByUsername("admin") == null) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setRole(Role.ADMIN);
            userDetailsService.save(admin);
        }
    }

    @PostConstruct
    @Transactional
    public void postConstructVIP() {
        if (userDetailsService.findByUsername("vip") == null) {
            User vip = new User();
            vip.setUsername("vip");
            vip.setPassword(passwordEncoder.encode("vip"));
            vip.setRole(Role.VIP);
            userDetailsService.save(vip);
        }
    }
}
