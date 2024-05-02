package ru.ScheduleOfEvents.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.ScheduleOfEvents.models.User;
import ru.ScheduleOfEvents.repositories.UserRepository;
import ru.ScheduleOfEvents.security.UserDetailsImpl;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        return user.map(UserDetailsImpl::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public User findOne(long id){
        return userRepository.findById(id).orElse(null);
    }
    public List<User> findAll(){
        return userRepository.findAll();
    }

    @Transactional
    public void save(User person){
        userRepository.save(person);
    }

    public User findByUsername(String username){
        return userRepository.findByUsername(username).orElse(null);
    }

    @Transactional
    public void update(long id, User updatedUser) {
        User userToBeUpdated = findOne(id);

        userToBeUpdated.setUsername(updatedUser.getUsername());
        userToBeUpdated.setEmail(updatedUser.getEmail());
        userToBeUpdated.setPassword(updatedUser.getPassword());
    }

    @Transactional
    public void delete(long id) {
        userRepository.deleteById(id);
    }
}
