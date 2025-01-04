package net.soumya.JournalApp.controller;

import net.soumya.JournalApp.entity.Users;
import net.soumya.JournalApp.services.UsersServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UsersServices usersServices;

    // add new user in database
    @PostMapping("/createuser")
    public ResponseEntity<?> createUser(@RequestBody Users user) {
        try {
            usersServices.saveEntry(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
