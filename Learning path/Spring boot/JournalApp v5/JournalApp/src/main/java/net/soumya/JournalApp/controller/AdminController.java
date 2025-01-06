package net.soumya.JournalApp.controller;

import net.soumya.JournalApp.entity.Users;
import net.soumya.JournalApp.services.UsersServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UsersServices usersServices;


    // get all users by admin
    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUsers() {
        List<Users> users = usersServices.getAllUsers();

        try {
            if (!users.isEmpty()) {
                return new ResponseEntity<>(users, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // add adding in database
    @PostMapping("/createadmin")
    public ResponseEntity<?> crateAdmin(@RequestBody Users user) {
        try {
            usersServices.saveNewAdmin(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
