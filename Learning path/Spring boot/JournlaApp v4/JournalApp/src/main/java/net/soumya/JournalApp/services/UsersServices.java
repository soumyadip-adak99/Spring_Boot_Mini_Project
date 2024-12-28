package net.soumya.JournalApp.services;

import net.soumya.JournalApp.entity.JournalEntry;
import net.soumya.JournalApp.entity.Users;
import net.soumya.JournalApp.repository.UserRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.resource.ResourceUrlProvider;

import java.util.List;
import java.util.Optional;

@Service
public class UsersServices {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ResourceUrlProvider resourceUrlProvider;

    // save
    public void addUser(Users users) {
        userRepo.save(users);
    }

    // get all
    public List<Users> getAllUsers() {
        return userRepo.findAll();
    }

    // find by username
    public Optional<Users> getByUserName(String userName) {
        return Optional.ofNullable(userRepo.findByUsername(userName));
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
