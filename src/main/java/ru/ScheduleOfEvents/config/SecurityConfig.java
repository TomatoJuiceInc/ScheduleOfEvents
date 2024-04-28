package ru.ScheduleOfEvents.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.stereotype.Controller;
import ru.ScheduleOfEvents.services.UserDetailsServiceImpl;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;


@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    private final UserDetailsServiceImpl userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/home", "/auth/**").permitAll()
                        .anyRequest().authenticated())
                .formLogin(login -> login
                        .loginPage("/auth/login")
                        .permitAll())
                .logout(LogoutConfigurer::permitAll);

        return http.build();

//        http
//                .authorizeHttpRequests(request -> request
//                        .requestMatchers("/admin").hasRole("ADMIN")
//                        .requestMatchers("auth/**", "/error").permitAll()
//                        .anyRequest().hasAnyRole("USER", "ADMIN"))
//                .formLogin(formLogin -> formLogin
//                        .loginPage("auth/signin")
//                        .loginProcessingUrl("/process_login")
//                        .defaultSuccessUrl("/hello", true))
////                        .failureUrl("/auth/signin?error"))
//                .logout(logout -> logout
//                        .logoutUrl("/logout")
//                        .logoutSuccessUrl("/auth/signin"));
//        return http.build();
//                .csrf(AbstractHttpConfigurer::disable)
//                .exceptionHandling(exceptions -> exceptions
//                        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
//                .formLogin(AbstractAuthenticationFilterConfigurer::permitAll)
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("auth/**").permitAll()
//                        .requestMatchers("secured/user/**").authenticated()
//                        .anyRequest().permitAll())
//                .build();


}

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}


