package net.soumya.JournalApp.services;

import net.soumya.JournalApp.entity.Users;
import net.soumya.JournalApp.repository.UserRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServices {

    @Autowired
    private UserRepo userRepo;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    // save
    public void addUser(Users users) {
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        users.setRole(List.of("USER"));
        userRepo.save(users);
    }

    // save new username
    public void saveNewUser(Users users) {
        userRepo.save(users);
    }

    // get all
    public List<Users> getAllUsers() {
        return userRepo.findAll();
    }

    // find by username
    public Users getByUserName(String userName) {
        return userRepo.findByUsername(userName);
    }

    // delete method
    public void deleteById(Object id) {
        userRepo.deleteById((ObjectId) id);
    }

    // update user
    public void updateUser(Users user){
        userRepo.save(user);
    }
}
