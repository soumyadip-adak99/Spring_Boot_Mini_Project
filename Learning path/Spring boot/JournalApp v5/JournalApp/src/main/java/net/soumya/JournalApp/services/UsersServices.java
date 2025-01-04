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
    private  UserRepo userRepo;
    @Autowired
    public  PasswordEncoder passwordEncoder;

//    public UsersServices(UserRepo userRepo, PasswordEncoder passwordEncoder) {
//        this.userRepo = userRepo;
//        this.passwordEncoder = passwordEncoder;
//    }

    public void saveEntry(Users user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(List.of("ROLE_USER"));
        userRepo.save(user);
    }

    public List<Users> getAllUsers() {
        return userRepo.findAll();
    }

    public Users getByUserName(String userName) {
        return userRepo.findByUsername(userName);
    }

    public void updateUser(Users user) {
        userRepo.save(user);
    }
}
