package net.soumya.JournalApp.controller;

import net.soumya.JournalApp.entity.Users;
import net.soumya.JournalApp.services.UsersServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserEntryController {

    @Autowired
    private UsersServices usersServices;

    // get all users
    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        List<Users> users = usersServices.getAllUsers();

        try {
            if (!users.isEmpty()) {
                return new ResponseEntity<>(users, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // get user by username
    @GetMapping("/username/{userName}")
    public ResponseEntity<?> getByUserName(@PathVariable String userName) {
        Users user = usersServices.getByUserName(userName);
        try {
            if (user != null) {
                return new ResponseEntity<>(user, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody Users user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        Users userInDB = usersServices.getByUserName(userName);
        userInDB.setUsername(user.getUsername());
        userInDB.setPassword(user.getPassword());
        usersServices.updateUser(userInDB);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
