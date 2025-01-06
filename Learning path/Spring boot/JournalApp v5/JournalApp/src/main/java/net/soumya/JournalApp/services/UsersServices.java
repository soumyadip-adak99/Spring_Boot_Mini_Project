package net.soumya.JournalApp.services;

import net.soumya.JournalApp.entity.Users;
import net.soumya.JournalApp.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServices {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    public PasswordEncoder passwordEncoder;

    // Save a new admin user
    public void saveNewAdmin(Users user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(List.of("ROLE_ADMIN"));
        userRepo.save(user);
    }

    // Save a new user
    public void saveNewUser(Users user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(List.of("ROLE_USER"));
        userRepo.save(user);
    }

    //save user
    public void saveUser(Users user) {
        userRepo.save(user);
    }

    public List<Users> getAllUsers() {
        return userRepo.findAll();
    }

    public Users findByUserName(String userName) {
        return userRepo.findByUsername(userName);
    }

    public void updateUser(Users user) {
        userRepo.save(user);
    }
}
