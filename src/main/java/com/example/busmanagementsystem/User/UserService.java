package com.example.busmanagementsystem.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
//public class UserService implements CommandLineRunner {
public class UserService {

    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public void addNewUser(User user) {
        Optional<User> optionalUser =  userRepository.findUserByEmail(user.getEmail());
        if (optionalUser.isPresent()) {
            throw new IllegalStateException("Account Already Exists. Please Try Signing In.");
        } else {
            userRepository.save(user);
        }
    }

    public User findUserByEmail(String email) {
        Optional<User> optionalUser =  userRepository.findUserByEmail(email);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }
        return null;
    }

    public void deleteUserById(String email) {
        Optional<User> optionalUser =  userRepository.findUserByEmail(email);
        if (optionalUser.isPresent()) {
            userRepository.deleteById(optionalUser.get().getId());
        } else {
            throw new IllegalStateException("User with email "+email+" does not exist!");
        }
    }

//    @Override
//    public void run(String... args) {
//        // Delete all
//        this.userRepository.deleteAll();
//
//        // Crete users
//        User dan = new User("dan","dan@g.com",passwordEncoder.encode("dan123"),"ROLE_STUDENT");
//        User admin = new User("admin","admin@g.com",passwordEncoder.encode("admin123"),"ROLE_ADMIN");
//        User manager = new User("manager","manager@g.com",passwordEncoder.encode("manager123"),"ROLE_CONDUCTOR");
//
//        List<User> users = Arrays.asList(dan,admin,manager);
//
//        // Save to db
//        this.userRepository.saveAll(users);
//    }
}
