package ru.ScheduleOfEvents.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.ScheduleOfEvents.models.*;
import ru.ScheduleOfEvents.repositories.UserRepository;
import ru.ScheduleOfEvents.security.UserDetailsImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

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

        // обновление аватарки
//        File newPic = updatedUser.getAvatarFile();
//        System.out.println(newPic.toPath());
//        if (newPic != null) { // новый файл не null
//            if (getFileExtension(newPic).equals("jpg")) { // новый файл - jpg
//                File targetFile = null;
//                if (userToBeUpdated.getAvatarFileName().equals("user_pic.jpg")) { // старая ава - дефолт
//                    targetFile = new File("/profileViewStatic/images", "new_user_pic.jpg");
//                    userToBeUpdated.setAvatarFileName("new_user_pic.jpg");
//                } else {
//                    targetFile = new File("/profileViewStatic/images/new_user_pic.jpg");
//                }
//                Path targetPath = targetFile.toPath();
//                try {
//                    Files.copy(new FileInputStream(newPic), targetPath, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        }

        // обновление пароля
        if (!(updatedUser.getPassword() == null || updatedUser.getPassword().isEmpty())) {
            userToBeUpdated.setPassword(updatedUser.getPassword());
        }
    }

    // сортировка билетов
    public void sortTickets(long id) {
        User userToBeUpdated = findOne(id);
        userToBeUpdated.getTickets().stream()
                .sorted(Comparator.comparing(ticket -> ticket.getEvent().getDate()))
                .collect(Collectors.toList());
    }

    // добавление билета в пользователя
//    public void addTestTickets(long id) {
//        User userToBeUpdated = findOne(id);
//        List<Ticket> tickets = new ArrayList<>();
//        tickets.add(new Ticket("1", "1",
//                new Event("Any event",
//                        new Date(2024, 7, 12, 13, 5,5),
//                        new Hall("Актовый зал")),
//                new Price(200)));
//        tickets.add(new Ticket("1", "1",
//                new Event("Any event",
//                        new Date(2024, 8, 18, 13, 5,5),
//                        new Hall("Актовый зал")),
//                new Price(200)));
//        tickets.add(new Ticket("1", "1",
//                new Event("Any event",
//                        new Date(2024, 8, 12, 13, 05,05),
//                        new Hall("Актовый зал")),
//                new Price(200)));
//        tickets.add(new Ticket("1", "1",
//                new Event("Any event",
//                        new Date(2024, 7, 13, 13, 05,05),
//                        new Hall("Актовый зал")),
//                new Price(200)));
//        tickets.add(new Ticket("1", "1",
//                new Event("Any event",
//                        new Date(2024, 7, 28, 13, 05,05),
//                        new Hall("Актовый зал")),
//                new Price(200)));
//
//        userToBeUpdated.setTickets(tickets);
//    }

    @Transactional
    public void delete(long id) {
        userRepository.deleteById(id);
    }
}
